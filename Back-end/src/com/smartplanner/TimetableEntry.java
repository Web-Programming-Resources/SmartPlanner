package com.smartplanner;

public class TimetableEntry {
    private int activityId;
    private Term pickedTerm;

    public TimetableEntry(int activityId, Term pickedTerm) {
        this.activityId = activityId;
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
