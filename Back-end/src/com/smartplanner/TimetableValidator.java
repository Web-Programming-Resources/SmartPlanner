package com.smartplanner;

import java.time.LocalTime;
import java.util.ArrayList;

public class TimetableValidator {

    public boolean isValid(ArrayList<TimetableEntry> timeTable) {
        for(int i = 0; i < timeTable.size(); ++i) {
            for(int j = 0; j < timeTable.size(); ++j) {
                if(i == j)
                    continue;
                if(doesOverlap(timeTable.get(i), timeTable.get(j)))
                    return false;
            }
        }
        return true;
    }

    private boolean doesOverlap(TimetableEntry lhs, TimetableEntry rhs) {
        Term lhsTerm = lhs.getTerm();
        Term rhsTerm = rhs.getTerm();

        Term earlier;
        Term later;
        if (lhsTerm.getStartTime().isAfter(rhsTerm.getStartTime())) {
            later = lhsTerm;
            earlier = rhsTerm;
        } else {
            later = rhsTerm;
            earlier = lhsTerm;
        }

        LocalTime earlierEndingTime = earlier.getStartTime().plusMinutes(earlier.getDurationInMin());
        if (earlierEndingTime.isAfter(later.getStartTime()))
            return true;
        else
            return false;
    }
}
