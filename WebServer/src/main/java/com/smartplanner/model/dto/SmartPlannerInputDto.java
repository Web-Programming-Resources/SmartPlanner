package com.smartplanner.model.dto;

import com.smartplanner.model.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartPlannerInputDto extends SmartPlannerDto {

    private List<Lesson> lessons;

    private List<List<Integer>> timeDistanceInMinutes;
}
