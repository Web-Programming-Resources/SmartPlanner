package com.smartplanner.model.dto;

import com.smartplanner.model.entity.OptimizedActivity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartPlannerDto {

    private String planName;

    private int daysInCycle;

    private int maxCommutesPerDay;

    private OptimizedActivity optimizedActivity;

    private List<List<Integer>> timeDistanceInMinutes;

}
