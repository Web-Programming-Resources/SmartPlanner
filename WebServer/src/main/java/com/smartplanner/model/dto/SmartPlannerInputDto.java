package com.smartplanner.model.dto;

import com.smartplanner.model.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SmartPlannerInputDto {

    @Getter
    @Setter
    private int daysInCycle;

    @Getter
    @Setter
    private int maxCommutesPerDay;

    @Getter
    @Setter
    private String optimizedActivityStartsAt;

    @Getter
    @Setter
    private String optimizedActivityEndsAt;

    @Getter
    @Setter
    private int minTimeSpentAtOptimizedActivityAtOnceInMinutes;

    @Getter
    @Setter
    private int maxTimeSpentAtOptimizedActivityAtOnceInMinutes;

    @Getter
    @Setter
    private List<Boolean> isOpenedInDay;

    @Getter
    @Setter
    private List<Lesson> lessons;

    @Getter
    @Setter
    private List<List<Integer>> commuteMatrix;
}
