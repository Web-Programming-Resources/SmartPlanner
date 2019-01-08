package com.smartplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartplanner.model.entity.Lesson;
import com.smartplanner.model.entity.Term;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonWithPossibleTerms extends Lesson {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Term> possibleTerms;
}
