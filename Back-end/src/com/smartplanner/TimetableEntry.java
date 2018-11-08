package com.smartplanner;

public class TimetableEntry {
    private int ActivityID;
    private Term pickedTerm;

    public TimetableEntry(int activityID, Term pickedTerm) {
        ActivityID = activityID;
        this.pickedTerm = pickedTerm;
    }
}
