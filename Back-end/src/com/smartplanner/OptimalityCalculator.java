package com.smartplanner;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class OptimalityCalculator {
    TimeDistanceManager timeDistanceManager;
    int maxCommutesPerDay;
    LocalTime optimizedActivityOpensAt;
    LocalTime optimizedActivityClosesAt;
    int minTimeSpentOnOptimizedAtOnceInMinutes;
    int numberOfDaysInCycle;
    OptimizedActivity optimizedActivity;

    public OptimalityCalculator(TimeDistanceManager timeDistanceManager, int maxCommutesPerDay, int minTimeSpentOnOptimizedAtOnceInMinutes,
                                LocalTime optimizedActivityOpensAt, LocalTime optimizedActivityClosesAt, int numberOfDaysInCycle, OptimizedActivity optimizedActivity) {
        this.optimizedActivity = optimizedActivity;
        this.timeDistanceManager = timeDistanceManager;
        this.maxCommutesPerDay = maxCommutesPerDay;
        this.minTimeSpentOnOptimizedAtOnceInMinutes = minTimeSpentOnOptimizedAtOnceInMinutes;
        this.optimizedActivityOpensAt = optimizedActivityOpensAt;
        this.optimizedActivityClosesAt = optimizedActivityClosesAt;
        this.numberOfDaysInCycle = numberOfDaysInCycle;
    }

    public int calculate(ArrayList<TimetableEntry> timetable){

        ArrayList<ArrayList<Boolean>> optimalDecisionPoints = new ArrayList<ArrayList<Boolean>>(numberOfDaysInCycle); //TODO: return it somehow

        int timeSpentInWorkInCycle=0;
        for(int cycleDayNumber=0; cycleDayNumber<numberOfDaysInCycle; ++cycleDayNumber) {

            GoToOptimizedActivityDecider decider = new GoToOptimizedActivityDecider(timetable, cycleDayNumber);

            int maxForCurrentDay=0;
            ArrayList<Boolean> bestDecisionPoints=null;
            while(decider.isNext()) {
                ArrayList<Boolean> currDecisionPoints=decider.getNext();
                int currentVal = calculateRegardingProvidedDecisionPoints(timetable, currDecisionPoints, cycleDayNumber);

                if(currentVal > maxForCurrentDay) {
                    bestDecisionPoints=currDecisionPoints;
                    maxForCurrentDay = currentVal;
                }
            }

            timeSpentInWorkInCycle+=maxForCurrentDay;
            optimalDecisionPoints.add(bestDecisionPoints);


        }


        return timeSpentInWorkInCycle;
    }

    private int getNumberOfOnes(ArrayList<Boolean> decisionPoints) {
        int numberOfOnes=0;
        for(Boolean decision : decisionPoints)
            if(decision == true)
                ++numberOfOnes;

        return numberOfOnes;
    }

//TODO: shift optimizedActivityOpensAt, closesAt to Work class
    private int calculateRegardingProvidedDecisionPoints(ArrayList<TimetableEntry> timetable, ArrayList<Boolean> currDecisionPoints, int cycleDayNumber) {

        if(getNumberOfOnes(currDecisionPoints) > maxCommutesPerDay)
            return 0;

        ArrayList<TimetableEntry> specifiedDayTimetable = new ArrayList<TimetableEntry>();
        for(TimetableEntry entry : timetable)
            if (entry.getTerm().getCycleDayNumber() == cycleDayNumber)
                specifiedDayTimetable.add(entry);

        int timeSpentInWork = 0;
        if (currDecisionPoints.get(0) == true) {
            int timeBetweenOptimizedActivityOpenAndFirstActivityStart = calculateMinutesBetweenTwoTimePoints(optimizedActivityOpensAt, specifiedDayTimetable.get(0).getTerm().getStartTime())
                    - timeDistanceManager.getTimeDistanceInMin(specifiedDayTimetable.get(0).getActivity(), optimizedActivity);
            int minutesSpentInWorkForCurrDecisionPoint = timeBetweenOptimizedActivityOpenAndFirstActivityStart;
            if(minutesSpentInWorkForCurrDecisionPoint >= minTimeSpentOnOptimizedAtOnceInMinutes)
                timeSpentInWork+=minutesSpentInWorkForCurrDecisionPoint;
        }

        for(int i = 1; i < currDecisionPoints.size() - 1; ++i) {
            if(currDecisionPoints.get(i) == true) {

               int timeBetweenTwoActivities= calculateMinutesBetweenTwoTimePoints(specifiedDayTimetable.get(i).getTerm().getStartTime(),
                     specifiedDayTimetable.get(i-1).getTerm().getEndTime());
               int transportationTimeFromFirstActivityToOptimized = timeDistanceManager.getTimeDistanceInMin(specifiedDayTimetable.get(i-1).getActivity(), optimizedActivity);
               int transportationTimeFromOptimizedToSecondActivity = timeDistanceManager.getTimeDistanceInMin(optimizedActivity, specifiedDayTimetable.get(i).getActivity());
               int minutesSpentInWorkForCurrDecisionPoint = timeBetweenTwoActivities - transportationTimeFromFirstActivityToOptimized - transportationTimeFromOptimizedToSecondActivity;
               if(minutesSpentInWorkForCurrDecisionPoint>=minTimeSpentOnOptimizedAtOnceInMinutes)
                    timeSpentInWork+=minutesSpentInWorkForCurrDecisionPoint;
            }
        }

        if (currDecisionPoints.get(currDecisionPoints.size()-1) == true) {
            int timeBetweenLastActivityEndingAndOptimizedActivityClose=calculateMinutesBetweenTwoTimePoints(specifiedDayTimetable.get(specifiedDayTimetable.size()-1).getTerm().getEndTime(),
                    optimizedActivityClosesAt);
            int travelTimeFromLastActivityToOptimizedActivity = timeDistanceManager.getTimeDistanceInMin(specifiedDayTimetable.get(specifiedDayTimetable.size()-1).getActivity(), optimizedActivity);
            int minutesSpentInWorkForCurrDecisionPoint = timeBetweenLastActivityEndingAndOptimizedActivityClose - travelTimeFromLastActivityToOptimizedActivity;

            if(minutesSpentInWorkForCurrDecisionPoint >= minTimeSpentOnOptimizedAtOnceInMinutes)
                timeSpentInWork += minutesSpentInWorkForCurrDecisionPoint;
        }

        return timeSpentInWork;
    }

    private int calculateMinutesBetweenTwoTimePoints(LocalTime lhs, LocalTime rhs) {
        Duration duration = Duration.between(lhs, rhs);
        return (int)Math.abs(duration.toMinutes());
    }


}
