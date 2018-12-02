package com.smartplanner;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GoWorkBruteforcer {
    private ArrayList<TimetableEntry> timetable;
    private ArrayList<AtomicInteger> currentCombination;
    private boolean hasFinished;
    public GoWorkBruteforcer(ArrayList<TimetableEntry> timetable) {
        this.timetable = timetable;
        this.hasFinished=false;

        int numberOfDecisionPoints = timetable.size()+1;
        this.currentCombination = new ArrayList<AtomicInteger>(numberOfDecisionPoints);
        for(int i=0; i < numberOfDecisionPoints; ++i)
            currentCombination.add(new AtomicInteger(0));
    }

    public ArrayList<Boolean> getNext() {
        shiftIndexesToNextSet();
        return null;
    }

    public boolean isNext() {
        return !hasFinished;
    }

    private void shiftIndexesToNextSet() {
        currentCombination.get(0).incrementAndGet();

        for (int i = 0; i + 1 < currentCombination.size(); ++i) {
            if (currentCombination.get(i).get() > 1) {
                currentCombination.get(i).getAndSet(0);
                currentCombination.get(i + 1).getAndIncrement();
            }
        }

        int lastIndex = currentCombination.size()-1;
        if(currentCombination.get(lastIndex).get() > 1)
            hasFinished = true;
    }
}
