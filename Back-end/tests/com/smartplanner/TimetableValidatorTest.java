package com.smartplanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;


public class TimetableValidatorTest {
    @Test
    void validatorReturnsFalseIfThereAreEntriesThatOverlapsEeachOther() {
        ArrayList<TimetableEntry> invalidTimetable=new ArrayList<TimetableEntry>(
                Arrays.asList(
                        new TimetableEntry(2, new Term(60, 5, LocalTime.of(14,00))),
                        new TimetableEntry(3, new Term(60, 5, LocalTime.of(14,30)))
                )
        );
        
        TimetableValidator validator = new TimetableValidator();
        assertFalse(validator.isValid(invalidTimetable));
    }
}
