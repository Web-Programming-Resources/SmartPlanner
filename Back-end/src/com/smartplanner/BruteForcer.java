package com.smartplanner;

import java.util.ArrayList;

public class BruteForcer {
    private ArrayList<Activity> activities;
    private int termIndexes[];
    private boolean hasFinished = false;
    private int maxDaysInCycle;

    public BruteForcer(ArrayList<Activity> activities, int maxDaysInCycle) {
        this.maxDaysInCycle = maxDaysInCycle;
        this.activities = activities;
        this.termIndexes = new int[activities.size()];

        for(int index : termIndexes)
            index = 0;
    }

    public boolean isNext() {
        return !hasFinished;
    }

    public ArrayList<TimetableEntry> getNext() {
        ArrayList<TimetableEntry> possibleTimeTable = new ArrayList<TimetableEntry>();

        for (int activityIndex = 0; activityIndex < activities.size(); ++activityIndex) {
            ArrayList<Term> currActTerms = activities.get(activityIndex).getTerms();
            Term firstTerm = currActTerms.get(termIndexes[activityIndex]);

            addRepeatsInCycle(possibleTimeTable, activityIndex, firstTerm);
        }
        shiftIndexesToNextSet();
        return possibleTimeTable;
    }

    private void addRepeatsInCycle(ArrayList<TimetableEntry> timetable, int activityIndex, Term firstTerm) {
        final int REPEAT_EVERY = 7;
        for(int i = 0; ; ++i) { //add repeats in cycle
            int nextRepeatDay = firstTerm.getCycleDayNumber() + i*REPEAT_EVERY;
            if(nextRepeatDay > maxDaysInCycle)
                break;
            Term nextTerm = new Term(firstTerm.getDurationInMin(), nextRepeatDay, firstTerm.getStartTime());
            timetable.add(new TimetableEntry(activities.get(activityIndex), nextTerm));
        }
    }

    private void shiftIndexesToNextSet() {
        ++termIndexes[0];

        for(int activityIndex = 0; activityIndex+1 < termIndexes.length; ++activityIndex)  {
            int qntOfTermsForCurrActivity = activities.get(activityIndex).getTerms().size();
            if(termIndexes[activityIndex] >= qntOfTermsForCurrActivity) {
                termIndexes[activityIndex] = 0;
                ++termIndexes[activityIndex+1];
            }
        }

        int lastActivityIndex = termIndexes.length-1;
        if(termIndexes[lastActivityIndex] >= activities.get(lastActivityIndex).getTerms().size())
            hasFinished = true;
    }
}
