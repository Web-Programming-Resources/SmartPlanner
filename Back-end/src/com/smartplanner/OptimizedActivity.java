package com.smartplanner;

import java.time.LocalTime;
import java.util.ArrayList;

public class OptimizedActivity {
    private int id;
    private String name;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private int maxTimeSpentInActivityInMin;
    private int minTimeSpentAtOptimizedAtOnceInMinutes;
    private ArrayList<Boolean> isOpenedInDay;

    public OptimizedActivity(int id, String name, LocalTime opensAt, LocalTime closesAt, int minTimeSpentAtOptimizedAtOnceInMinutes,
                             int maxTimeSpentInActivityInMin, ArrayList<Boolean> isOpenedInDay) {
        this.id = id;
        this.name = name;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.minTimeSpentAtOptimizedAtOnceInMinutes = minTimeSpentAtOptimizedAtOnceInMinutes;
        this.maxTimeSpentInActivityInMin = maxTimeSpentInActivityInMin;
        this.isOpenedInDay=isOpenedInDay;
    }

    public int getMinTimeSpentAtOptimizedAtOnceInMinutes() {
        return minTimeSpentAtOptimizedAtOnceInMinutes;
    }

    public int getMaxTimeSpentInActivityInMin() {
        return maxTimeSpentInActivityInMin;
    }

    public LocalTime getOpensAt() {
        return opensAt;
    }

    public LocalTime getClosesAt() {
        return closesAt;
    }

    public Boolean isOpenedInDay(int dayNumber) {
        return isOpenedInDay.get(dayNumber);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
