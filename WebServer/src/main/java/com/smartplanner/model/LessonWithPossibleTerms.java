package com.smartplanner.model;

import com.smartplanner.model.entity.Lesson;
import com.smartplanner.model.entity.Term;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonWithPossibleTerms extends Lesson {

    private List<Term> possibleTerms;
}
