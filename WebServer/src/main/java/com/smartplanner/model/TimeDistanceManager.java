package com.smartplanner.model;

import com.smartplanner.model.entity.Lesson;
import com.smartplanner.model.entity.OptimizedActivity;

import java.util.List;

public class TimeDistanceManager {
    // timeDistance[from ID][to ID]
    private List<List<Integer>> timeDistanceInMinutes;

    public TimeDistanceManager(List<List<Integer>> timeDistanceInMinutes) {
        this.timeDistanceInMinutes = timeDistanceInMinutes;
    }

    public int getTimeDistanceInMinutes(Lesson from, Lesson to) {
        return timeDistanceInMinutes.get(from.getId()).get(to.getId());
    }

    public int getTimeDistanceInMinutes(OptimizedActivity from, Lesson to) {
        return timeDistanceInMinutes.get(from.getId()).get(to.getId());
    }

    public int getTimeDistanceInMinutes(Lesson from, OptimizedActivity to) {
        return timeDistanceInMinutes.get(from.getId()).get(to.getId());
    }
}
