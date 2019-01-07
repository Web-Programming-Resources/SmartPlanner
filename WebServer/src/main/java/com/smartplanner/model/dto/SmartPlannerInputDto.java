package com.smartplanner.model.dto;

import com.smartplanner.model.LessonWithPossibleTerms;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartPlannerInputDto extends SmartPlannerDto {

    private List<LessonWithPossibleTerms> lessons;
}
