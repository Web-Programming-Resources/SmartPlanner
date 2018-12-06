package com.smartplanner;

import java.time.LocalTime;

public class OptimizedActivity extends Activity {
    private LocalTime opensAt;
    private LocalTime closesAt;
    private int maxTimeSpentInActivityInMin;

    public OptimizedActivity(String name, LocalTime opensAt, LocalTime closesAt, int maxTimeSpentInActivityInMin) {
        super(name);
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.maxTimeSpentInActivityInMin = maxTimeSpentInActivityInMin;
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
}
