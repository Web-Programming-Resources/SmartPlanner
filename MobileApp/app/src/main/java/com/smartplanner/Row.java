package com.smartplanner;

public class Row {
   private String startingTime;
   private String endingTime;
   private String name;

    public Row(String startingTime, String endingTime, String name) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.name = name;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public String getName() {
        return name;
    }
}
