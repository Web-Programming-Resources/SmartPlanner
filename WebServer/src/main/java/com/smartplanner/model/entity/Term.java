package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "term")
public class Term {

    @Id
    @Getter
    @Setter
    @Column(name = "term_id")
    private int id;

    @Getter
    @Setter
    @JoinColumn(name = "lesson_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Lesson lesson;

    @Getter
    @Setter
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;

    @Getter
    @Setter
    @Column(name = "cycle_day_number")
    private int cycleDayNumber;

    @Getter
    @Setter
    @Column(name = "start_time")
    private LocalTime startTime;
}
