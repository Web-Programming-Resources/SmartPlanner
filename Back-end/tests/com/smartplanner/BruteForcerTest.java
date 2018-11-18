package com.smartplanner;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BruteForcerTest{

    ArrayList<Activity> activities;
    BruteForcer bruteForcer;
    int allPossibilities;

    @BeforeEach
    void beforeEach() {

        activities = new ArrayList<Activity>();

        ArrayList<Term> termsAct1 = new ArrayList<Term>();
        termsAct1.add(new Term(60, 1));
        termsAct1.add(new Term(50, 2));
        termsAct1.add(new Term(40, 3));
        activities.add(new Activity("activity1", termsAct1));

        ArrayList<Term> termsAct2 = new ArrayList<Term>();
        termsAct2.add(new Term(30, 1));
        termsAct2.add(new Term(20, 4));
        termsAct2.add(new Term(10, 3));
        activities.add(new Activity("activity2", termsAct2));

        ArrayList<Term> termsAct3 = new ArrayList<Term>();
        termsAct3.add(new Term(40, 5));
        termsAct3.add(new Term(20, 4));
        termsAct3.add(new Term(10, 2));
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

}