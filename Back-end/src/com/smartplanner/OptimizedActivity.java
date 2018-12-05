package com.smartplanner;

import java.time.LocalTime;

public class OptimizedActivity extends Activity {
    private LocalTime opensAt;
    private LocalTime closesAt;

    public OptimizedActivity(String name, LocalTime opensAt, LocalTime closesAt) {
        super(name);
        this.opensAt = opensAt;
        this.closesAt = closesAt;
    }

    public LocalTime getOpensAt() {
        return opensAt;
    }

    public LocalTime getClosesAt() {
        return closesAt;
    }
}
