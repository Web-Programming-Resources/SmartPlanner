package com.smartplanner;

import java.sql.Time;

public class TimetableEntry {
    private int activityID;
    private Term pickedTerm;

    public TimetableEntry(int activityID, Term pickedTerm) {
        this.activityID = activityID;
        this.pickedTerm = pickedTerm;
    }

    public Term getTerm() {

        return pickedTerm;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof TimetableEntry))
            return false;
        if (other == this)
            return true;

        TimetableEntry otherTimetableEntry = (TimetableEntry) other;
        return otherTimetableEntry.pickedTerm.equals(this.pickedTerm);
    }

}
