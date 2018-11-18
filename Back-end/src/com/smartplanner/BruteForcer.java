package com.smartplanner;

import java.util.ArrayList;


//TODO: make cleanup
public class BruteForcer
{
    private ArrayList<Activity> activities;
    private int termIndexes[];
    private boolean hasFinished=false;
    private int maxDaysInCycle;

    public BruteForcer(ArrayList<Activity> activities, int maxDaysInCycle)
    {
        this.maxDaysInCycle=maxDaysInCycle;
        this.activities=activities;
        this.termIndexes = new int[activities.size()];

        for(int index : termIndexes)
            index=0;
    }

    public boolean isNext()
    {
        return !hasFinished;
    }

    public ArrayList<TimetableEntry> getNext()
    {
        ArrayList<TimetableEntry> possibleTimeTable = new ArrayList<TimetableEntry>();

        for (int actIndx = 0; actIndx < activities.size(); ++actIndx)
        {
            ArrayList<Term> currActTerms = activities.get(actIndx).getTerms();


            Term firstTerm = currActTerms.get(termIndexes[actIndx]);


            final int REPEAT_EVERY=7;
            for(int i =0; ; ++i)//add repeats in cycle
            {
                int nextRepeatDay=firstTerm.getNumberOfCycleDay() + i*REPEAT_EVERY;
                if(nextRepeatDay>maxDaysInCycle)
                    break;

                Term nextTerm=new Term(firstTerm.getLengthInMin(), nextRepeatDay);
                possibleTimeTable.add(new TimetableEntry(activities.get(actIndx).getID(), nextTerm));
            }
        }

        nextSet();

        return possibleTimeTable;
    }

    private void nextSet() {
        ++termIndexes[0];

        for(int activityIndex=0; activityIndex+1<termIndexes.length; ++activityIndex)
        {
            if(termIndexes[activityIndex]>=activities.get(activityIndex).getTerms().size())
            {
                termIndexes[activityIndex]=0;
                ++termIndexes[activityIndex+1];
            }
        }

        int lastActivityIndex=termIndexes.length-1;
        if(termIndexes[lastActivityIndex]>=activities.get(lastActivityIndex).getTerms().size())
            hasFinished=true;
    }
}
