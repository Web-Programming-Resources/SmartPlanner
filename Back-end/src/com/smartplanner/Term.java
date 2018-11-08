package com.smartplanner;

import java.time.LocalTime;

public class Term {
    private int lengthInMin;
    private int numberOfCycleDay; //date in cycle
    private LocalTime startTime;

    public Term(int lengthInMin, int numberOfWeekDay) {
        this.lengthInMin = lengthInMin;
        this.numberOfCycleDay = numberOfWeekDay;
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
}
