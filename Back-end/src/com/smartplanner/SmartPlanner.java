package com.smartplanner;

import java.util.ArrayList;
import java.util.List;


public class SmartPlanner {
    ArrayList<Lesson> lessons;
    int daysInCycle;
    TimeDistanceManager distManag;
    int maxCommutesPerDay;
    int minTimeSpentAtOptimizedAtOnceInMinutes;
    OptimizedActivity optimizedActivity;

    public SmartPlanner(ArrayList<Lesson> lessons, int daysInCycle, TimeDistanceManager distManag,
                        int maxCommutesPerDay, OptimizedActivity optimizedActivity) {
        this.lessons = lessons;
        this.daysInCycle = daysInCycle;
        this.distManag = distManag;
        this.maxCommutesPerDay = maxCommutesPerDay;
        this.minTimeSpentAtOptimizedAtOnceInMinutes = optimizedActivity.getMinTimeSpentAtOptimizedAtOnceInMinutes(); //TODO:
        this.optimizedActivity = optimizedActivity;
    }

    //returns timetable with 0 score, and everything else set to null if data provided for calculations was not valid
    public TimetableWithDecisionPointsAndScore getOptimalPlan() {
        LessonPicker lessonPicker = new LessonPicker(lessons, daysInCycle);

        ArrayList<ArrayList<TimetableEntry>> potentialTimetables = new ArrayList<ArrayList<TimetableEntry>>();

        while(lessonPicker.isNext())
            potentialTimetables.add(lessonPicker.getNext());

        TimetableValidator validator = new TimetableValidator(distManag);


        int halfIndex = potentialTimetables.size()/2;

        List<ArrayList<TimetableEntry>> firstHalfOfPotentialTimetables = potentialTimetables.subList(0,halfIndex);
        List<ArrayList<TimetableEntry>>  secondHalfOfPotentialTimetables = potentialTimetables.subList(halfIndex, potentialTimetables.size());
        ArrayList<ArrayList<TimetableEntry>> firstHalfOfValidTimetables = new ArrayList<ArrayList<TimetableEntry>>();
        ArrayList<ArrayList<TimetableEntry>> secondHalfOfValidTimetables = new ArrayList<ArrayList<TimetableEntry>>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(ArrayList<TimetableEntry> timetable : firstHalfOfPotentialTimetables)
                    if(validator.isValid(timetable))
                        firstHalfOfValidTimetables.add(timetable);
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(ArrayList<TimetableEntry> timetable : secondHalfOfPotentialTimetables)
                    if(validator.isValid(timetable))
                        secondHalfOfValidTimetables.add(timetable);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<ArrayList<TimetableEntry>> validTimetables = new ArrayList<ArrayList<TimetableEntry>>(firstHalfOfValidTimetables);
        validTimetables.addAll(secondHalfOfValidTimetables);

        TimetableWithDecisionPointsAndScore bestTimetable = new TimetableWithDecisionPointsAndScore(0, null, null);
        if(validTimetables.size() == 0)
            return bestTimetable;

        OptimalityCalculator optimalityCalculator = new OptimalityCalculator(distManag, maxCommutesPerDay,
                minTimeSpentAtOptimizedAtOnceInMinutes, daysInCycle, optimizedActivity);


        for(ArrayList<TimetableEntry> validTimetable : validTimetables) {
            TimetableWithDecisionPointsAndScore currentTimetable = optimalityCalculator.calculate(validTimetable);
            if(currentTimetable.getMinutesSpentAtOptimizedActivity()>bestTimetable.getMinutesSpentAtOptimizedActivity())
                bestTimetable = currentTimetable;
        }

        return bestTimetable;
    }
}
