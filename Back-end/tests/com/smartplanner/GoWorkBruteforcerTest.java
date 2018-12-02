package com.smartplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoWorkBruteforcerTest {

    ArrayList<TimetableEntry> timetable;
    ArrayList<ArrayList<Boolean>> allPossibilities;
    GoWorkBruteforcer bruteforcer;
    int numberOfAllPossibilities;

    @BeforeEach
    void beforeEach() {
        timetable = new ArrayList<TimetableEntry>();
        timetable.add(new TimetableEntry(new Activity("test"), new Term(60, 1, LocalTime.of(12,00))));
        timetable.add(new TimetableEntry(new Activity("test"), new Term(60, 1, LocalTime.of(15,00))));

        bruteforcer=new GoWorkBruteforcer(timetable);
        numberOfAllPossibilities=(int)Math.pow(2, timetable.size()+1); // we have n+1 decision points (before first activity, between activities and after the last one)

    }

    @Test
    void isNextReturnsTrueIfThereIsNextCombinationToGet() {
        for(int i=0; i<numberOfAllPossibilities; ++i, bruteforcer.getNext())
            assertTrue(bruteforcer.isNext());

        assertFalse(bruteforcer.isNext());
    }
}

//TODO: test for timetable with TimetableEntries with different numberOfWeekDay
