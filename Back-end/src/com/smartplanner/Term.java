package com.smartplanner;

import java.time.LocalTime;

public class Term {
    private int lengthInMin;
    private int numberOfCycleDay; //date in cycle
    private LocalTime startTime;

    public Term(int lengthInMin, int numberOfWeekDay, LocalTime startTime) {
        this.lengthInMin = lengthInMin;
        this.numberOfCycleDay = numberOfWeekDay;
        this.startTime = startTime;
}

    public int getLengthInMin() {
        return lengthInMin;
    }

    public int getNumberOfCycleDay() {
        return numberOfCycleDay;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    @Override
    public boolean equals(Object other){

        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Term))return false;
        Term otherTimetableEntry = (Term) other;
        return otherTimetableEntry.lengthInMin==this.lengthInMin
                && otherTimetableEntry.numberOfCycleDay == this.numberOfCycleDay
                && otherTimetableEntry.startTime.equals(this.startTime);
    }
}
