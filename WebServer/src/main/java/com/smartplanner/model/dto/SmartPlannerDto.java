package com.smartplanner.model.dto;

import com.smartplanner.model.entity.OptimizedActivity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartPlannerDto {

    private int daysInCycle;

    private int maxCommutesPerDay;

    private OptimizedActivity optimizedActivity;
}
