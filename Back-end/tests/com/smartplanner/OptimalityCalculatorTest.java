package com.smartplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptimalityCalculatorTest {

    private TimeDistanceManager distManag;
    private OptimalityCalculator optimalityCalculator;
    private ArrayList<TimetableEntry> timetable;
    private OptimizedActivity optimizedActivity;

    @BeforeEach
    void beforeEach() {
        Activity.resetId(); //so that id doesn't crash if one run all tests at once
    }

    private void initDataForTest() {

          /* COMMUTE MATRIX (A, B - activities, W - optimized activity)
                to
              A  B  W
    from   A 10 10 10
           B 10 10 10
           W 10 10 10 */

        distManag = new TimeDistanceManager(
                new ArrayList<ArrayList<Integer>>(
                        Arrays.asList(
                                new ArrayList<Integer>(Arrays.asList(10, 10, 10)),
                                new ArrayList<Integer>(Arrays.asList(10, 10, 10)),
                                new ArrayList<Integer>(Arrays.asList(10, 10, 10))
                        )
                )
        );

        timetable = new ArrayList<TimetableEntry>(
                Arrays.asList(
                        new TimetableEntry(new Lesson("test", null, 7), new Term(60, 0, LocalTime.of(8, 00))),
                        new TimetableEntry(new Lesson("test", null, 7), new Term(60, 0, LocalTime.of(14, 00)))
                )
        );

        optimizedActivity = new OptimizedActivity("work", LocalTime.of(8, 00), LocalTime.of(18, 00));
        optimalityCalculator = new OptimalityCalculator(distManag, Integer.MAX_VALUE, 60, 1, optimizedActivity);
    }

    @Test
    void unlimitedCommutes_optimalityCalculatorCalculatesAmountOfMinutesSpentInOptimizedActivity() {
        initDataForTest();
        final int MINUTES_IN_HOUR = 60;

        Activity firstLesson = timetable.get(0).getActivity();
        int firstLessonDuration = timetable.get(0).getTerm().getDurationInMin();
        Activity secondLesson = timetable.get(1).getActivity();
        int secondLessonDuration = timetable.get(1).getTerm().getDurationInMin();

        int optActOpenedInMin = optimizedActivity.getClosesAt().getHour() * MINUTES_IN_HOUR + optimizedActivity.getClosesAt().getMinute()
                - (optimizedActivity.getOpensAt().getHour() * MINUTES_IN_HOUR + optimizedActivity.getOpensAt().getMinute());
        int minutesInTransportation = distManag.getTimeDistanceInMin(firstLesson, optimizedActivity)
                + distManag.getTimeDistanceInMin(optimizedActivity, secondLesson) + distManag.getTimeDistanceInMin(secondLesson, optimizedActivity);
        int minutesSpentOnActivities = firstLessonDuration + secondLessonDuration;

        int minSpentOnOptAct = optActOpenedInMin - minutesInTransportation - minutesSpentOnActivities;
        assertEquals(minSpentOnOptAct, optimalityCalculator.calculate(timetable).getMinutesSpentAtOptimizedActivity());
    }
}
