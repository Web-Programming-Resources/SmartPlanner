package com.smartplanner;

import java.time.LocalTime;
import java.util.ArrayList;

public class TimetableValidator {

    public boolean isValid(ArrayList<TimetableEntry> timeTable) {
        for(int i = 0; i < timeTable.size(); ++i) {
            for(int j = 0; j < timeTable.size(); ++j) {
                if(i==j)
                    continue;
                if(doesOverlap(timeTable.get(i), timeTable.get(j)))
                    return false;
            }
        }
        return true;
    }

    private boolean doesOverlap(TimetableEntry entry1, TimetableEntry entry2) {
        Term term1 = entry1.getTerm();
        Term term2 = entry2.getTerm();

        Term earlier;
        Term later;
        if (term1.getStartTime().isAfter(term2.getStartTime())) {
            later = term1;
            earlier = term2;
        } else {
            later = term2;
            earlier = term1;
        }

        LocalTime earlierEndingTime = earlier.getStartTime().plusMinutes(earlier.getDurationInMin());
        if (earlierEndingTime.isAfter(later.getStartTime()))
            return true;
        else
            return false;
    }

}
