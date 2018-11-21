package com.smartplanner;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

class BruteForcerTest{

    ArrayList<Activity> activities;
    BruteForcer bruteForcer;
    int allPossibilities;

    @BeforeEach
    void beforeEach() {

        activities = new ArrayList<Activity>();

        ArrayList<Term> termsAct1 = new ArrayList<Term>();
        termsAct1.add(new Term(10, 1, LocalTime.of(12, 30)));
        termsAct1.add(new Term(20, 2, LocalTime.of(12, 30)));
        activities.add(new Activity("activity1", termsAct1));

        ArrayList<Term> termsAct2 = new ArrayList<Term>();
        termsAct2.add(new Term(30, 3, LocalTime.of(12, 30)));
        termsAct2.add(new Term(40, 4, LocalTime.of(12, 30)));
        activities.add(new Activity("activity2", termsAct2));

        ArrayList<Term> termsAct3 = new ArrayList<Term>();
        termsAct3.add(new Term(50, 5, LocalTime.of(12, 30)));
        termsAct3.add(new Term(60, 6, LocalTime.of(12, 30)));
        activities.add(new Activity("activity3", termsAct3));

        bruteForcer = new BruteForcer(activities, 7);
        allPossibilities=termsAct1.size()*termsAct2.size()*termsAct3.size();
    }

    @Test
    void isNextReturnsTrueIfThereIsNextCombinationToGet() {

        for(int i=0; i<allPossibilities; ++i)
        {
            assertTrue(bruteForcer.isNext());
            bruteForcer.getNext();
        }

        assertFalse(bruteForcer.isNext());
    }

    @Test
    void getNextReturnsAllPossibletimeTables() {

        ArrayList<ArrayList<TimetableEntry>> allPossibleTimeTablesShouldBe = new ArrayList<>();

        ArrayList<TimetableEntry> timeTable1 = new ArrayList<>();
        timeTable1.add(new TimetableEntry(0, new Term(10, 1, LocalTime.of(12, 30))));
        timeTable1.add(new TimetableEntry(0, new Term(30, 3, LocalTime.of(12, 30))));
        timeTable1.add(new TimetableEntry(0, new Term(50, 5, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable1);

        ArrayList<TimetableEntry> timeTable2 = new ArrayList<>();
        timeTable2.add(new TimetableEntry(0, new Term(10, 1, LocalTime.of(12, 30))));
        timeTable2.add(new TimetableEntry(0, new Term(40, 4, LocalTime.of(12, 30))));
        timeTable2.add(new TimetableEntry(0, new Term(60, 6, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable2);

        ArrayList<TimetableEntry> timeTable3 = new ArrayList<>();
        timeTable3.add(new TimetableEntry(0, new Term(10, 1, LocalTime.of(12, 30))));
        timeTable3.add(new TimetableEntry(0, new Term(40, 4, LocalTime.of(12, 30))));
        timeTable3.add(new TimetableEntry(0, new Term(50, 5, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable3);

        ArrayList<TimetableEntry> timeTable4 = new ArrayList<>();
        timeTable4.add(new TimetableEntry(0, new Term(10, 1, LocalTime.of(12, 30))));
        timeTable4.add(new TimetableEntry(0, new Term(30, 3, LocalTime.of(12, 30))));
        timeTable4.add(new TimetableEntry(0, new Term(60, 6, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable4);

        ArrayList<TimetableEntry> timeTable5 = new ArrayList<>();
        timeTable5.add(new TimetableEntry(0, new Term(20, 2, LocalTime.of(12, 30))));
        timeTable5.add(new TimetableEntry(0, new Term(30, 3, LocalTime.of(12, 30))));
        timeTable5.add(new TimetableEntry(0, new Term(50, 5, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable5);

        ArrayList<TimetableEntry> timeTable6 = new ArrayList<>();
        timeTable6.add(new TimetableEntry(0, new Term(20, 2, LocalTime.of(12, 30))));
        timeTable6.add(new TimetableEntry(0, new Term(40, 4, LocalTime.of(12, 30))));
        timeTable6.add(new TimetableEntry(0, new Term(50, 5, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable6);

        ArrayList<TimetableEntry> timeTable7 = new ArrayList<>();
        timeTable7.add(new TimetableEntry(0, new Term(20, 2, LocalTime.of(12, 30))));
        timeTable7.add(new TimetableEntry(0, new Term(30, 3, LocalTime.of(12, 30))));
        timeTable7.add(new TimetableEntry(0, new Term(60, 6, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable7);

        ArrayList<TimetableEntry> timeTable8 = new ArrayList<>();
        timeTable8.add(new TimetableEntry(0, new Term(20, 2, LocalTime.of(12, 30))));
        timeTable8.add(new TimetableEntry(0, new Term(40, 4, LocalTime.of(12, 30))));
        timeTable8.add(new TimetableEntry(0, new Term(60, 6, LocalTime.of(12, 30))));
        allPossibleTimeTablesShouldBe.add(timeTable8);

        ArrayList<ArrayList<TimetableEntry>> allActualPossibleTimeTables = new ArrayList<>();
        for(int i=0; i<allPossibilities; ++i)
            allActualPossibleTimeTables.add(bruteForcer.getNext());

        for(int i=0; i<allPossibleTimeTablesShouldBe.size(); ++i) {
            ArrayList<TimetableEntry> singleTimeTableShouldBe = allPossibleTimeTablesShouldBe.get(i);
            assertTrue(hasFoundTimetableInside(singleTimeTableShouldBe, allActualPossibleTimeTables));
        }
    }


    boolean hasFoundTimetableInside(ArrayList<TimetableEntry> timeTable, ArrayList<ArrayList<TimetableEntry>> searchInside){
        for(int j=0; j<searchInside.size(); ++j) {
            if (isTimeTableEqual(timeTable, searchInside.get(j))) {
                return true;
            }
        }
        return false;
    }

    boolean isTimeTableEqual( ArrayList<TimetableEntry> lhs,  ArrayList<TimetableEntry> rhs)    {
        if(lhs.size()!=rhs.size())
            return false;

        for(TimetableEntry singleLhsEntry : lhs)
        {
            boolean hasFound=false;
            for(TimetableEntry singleRhsEntry: rhs)
            {
                if(singleLhsEntry.equals(singleRhsEntry))
                {
                    hasFound=true;
                    break;
                }
            }
            if(hasFound==false)
                return false;
        }
        return true;
    }
}