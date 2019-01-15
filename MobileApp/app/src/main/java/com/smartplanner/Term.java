package com.smartplanner;

public class Term {
    String startTime; //format HH:MM
    int duration; // in mins
    int cycleDayNumber; // which day of the cycle it is [0:daysOfCycle-1]

    public Term(String startTime, int duration, int cycleDayNumber) {
        this.startTime = startTime;
        this.duration = duration;
        this.cycleDayNumber = cycleDayNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCycleDayNumber() {
        return cycleDayNumber;
    }

    public void setCycleDayNumber(int cycleDayNumber) {
        this.cycleDayNumber = cycleDayNumber;
    }
}
