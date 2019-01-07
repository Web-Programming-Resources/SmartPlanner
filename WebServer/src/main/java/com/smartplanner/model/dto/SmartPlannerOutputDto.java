package com.smartplanner.model.dto;

import com.smartplanner.model.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartPlannerOutputDto extends SmartPlannerDto {

    private List<Lesson> lessons;
}
