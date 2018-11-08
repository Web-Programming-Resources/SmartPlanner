package com.smartplanner;

import java.util.ArrayList;

public class BruteForcer
{
    private ArrayList<Activity> activities;
    private int indexes[];
    private boolean hasFinished=false;

    public BruteForcer(ArrayList<Activity> activities)
    {
        this.activities=activities;
        this.indexes = new int[activities.size()];

        for(int index : indexes)
            index=0;
    }

    boolean isNext()
    {
        return !hasFinished;
    }

    ArrayList<TimetableEntry> getNext()
    {
        ArrayList<TimetableEntry> possibleTimeTable = new ArrayList<TimetableEntry>();

        for (int act = 0; act < activities.size(); ++act) {
            ArrayList<Term> terms = activities.get(act).getTerms();
            Term term = terms.get(indexes[act]);
            possibleTimeTable.add(new TimetableEntry(activities.get(act).getID(), term));
        }

        nextSet();

        return possibleTimeTable;
    }

    private void nextSet() {




        //TODO: after reaching max it should set flag hasFinished to true
    }
}
