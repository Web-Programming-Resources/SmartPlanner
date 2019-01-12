package com.smartplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartplanner.model.entity.Lesson;
import com.smartplanner.model.entity.Term;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Binder for a Lesson object with an array of possible terms for the lesson
 */
@Getter
@Setter
public class LessonWithPossibleTerms extends Lesson {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Term> possibleTerms;
}
