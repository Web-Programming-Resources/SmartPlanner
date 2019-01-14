package com.smartplanner.model;

import com.smartplanner.model.entity.OptimizedActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Finder of optimal timetable that allows to spent maximum possible time doing
 * optimized activity.
 */
public class SmartPlanner {
    private List<LessonWithPossibleTerms> lessons;
    private int daysInCycle;
    private TimeDistanceManager distanceManager;
    private int maxCommutesPerDay;
    private int minTimeSpentAtOptimizedAtOnceInMinutes;
    private OptimizedActivity optimizedActivity;

    /**
     * Creates SmartPlanner that finds the most optimal plan based on passed arguments
     *
     * @param lessons           list of all lessons that contains among others possible terms for each lesson.
     * @param daysInCycle       amount of days, after which the whole plan will repeat
     * @param distanceManager   object that contains commute matrix, which is data about travel time between each lesson and work
     * @param maxCommutesPerDay maximal amount of commutes to work per day (specified by a user)
     * @param optimizedActivity object containing data about optimized activity (for example work)
     */
    public SmartPlanner(List<LessonWithPossibleTerms> lessons, int daysInCycle, TimeDistanceManager distanceManager,
                        int maxCommutesPerDay, OptimizedActivity optimizedActivity) {
        this.lessons = lessons;
        this.daysInCycle = daysInCycle;
        this.distanceManager = distanceManager;
        this.maxCommutesPerDay = maxCommutesPerDay;
        this.minTimeSpentAtOptimizedAtOnceInMinutes = optimizedActivity.getMinTimeInMinutes(); //TODO:
        this.optimizedActivity = optimizedActivity;
    }

    /**
     * Returns the optimal plan that is calculated based on arguments passed in constructor
     *
     * @return if provided data is valid it returns specified terms for each lesson, amount of time spent and decision points(that tells us if we should go to work
     * after each lesson. If data for computations is not valid returns object with amount of minutes spent on optimized activity
     * equal to 0 and every other field set to null
     */
    public TimetableWithDecisionPointsAndScore getOptimalPlan() {
        LessonPicker lessonPicker = new LessonPicker((ArrayList<LessonWithPossibleTerms>) lessons, daysInCycle);

        ArrayList<ArrayList<TimetableEntry>> potentialTimetables = new ArrayList<ArrayList<TimetableEntry>>();

        while (lessonPicker.isNext())
            potentialTimetables.add(lessonPicker.getNext());

        TimetableValidator validator = new TimetableValidator(distanceManager);

        int halfIndex = potentialTimetables.size() / 2;

        List<ArrayList<TimetableEntry>> firstHalfOfPotentialTimetables = potentialTimetables.subList(0, halfIndex);
        List<ArrayList<TimetableEntry>> secondHalfOfPotentialTimetables = potentialTimetables.subList(halfIndex, potentialTimetables.size());
        ArrayList<ArrayList<TimetableEntry>> firstHalfOfValidTimetables = new ArrayList<ArrayList<TimetableEntry>>();
        ArrayList<ArrayList<TimetableEntry>> secondHalfOfValidTimetables = new ArrayList<ArrayList<TimetableEntry>>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<TimetableEntry> timetable : firstHalfOfPotentialTimetables)
                    if (validator.isValid(timetable))
                        firstHalfOfValidTimetables.add(timetable);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<TimetableEntry> timetable : secondHalfOfPotentialTimetables)
                    if (validator.isValid(timetable))
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
        if (validTimetables.size() != 0)
        {
            OptimalityCalculator optimalityCalculator = new OptimalityCalculator(distanceManager, maxCommutesPerDay,
                    minTimeSpentAtOptimizedAtOnceInMinutes, daysInCycle, optimizedActivity);

            for (ArrayList<TimetableEntry> validTimetable : validTimetables) {
                TimetableWithDecisionPointsAndScore currentTimetable = optimalityCalculator.calculate(validTimetable);
                if (currentTimetable.getMinutesSpentAtOptimizedActivity() > bestTimetable.getMinutesSpentAtOptimizedActivity())
                    bestTimetable = currentTimetable;
            }

        }

        return bestTimetable;
    }
}
