package com.smartplanner.model;

import com.smartplanner.model.entity.Lesson;
import com.smartplanner.model.entity.Term;

public class TimetableEntry {
    private Lesson lesson;
    private Term pickedTerm;

    public TimetableEntry(Lesson lesson, Term pickedTerm) {
        this.lesson = lesson;
        this.pickedTerm = pickedTerm;
    }

    public Term getTerm() {
        return pickedTerm;
    }

    public String getName() {
        return lesson.getName();
    }

    public Lesson getLesson() {
        return lesson;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof TimetableEntry))
            return false;
        if (other == this)
            return true;

        TimetableEntry otherTimetableEntry = (TimetableEntry) other;
        return otherTimetableEntry.pickedTerm.equals(this.pickedTerm);
    }
}
