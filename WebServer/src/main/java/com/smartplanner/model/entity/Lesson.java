package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @Getter
    @Setter
    @Column(name = "lesson_id")
    private int id;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Plan.class)
    @JoinColumn(name = "plan_id")
    private int planId;

    @Getter
    @Setter
    @Column(name = "lesson_name", length = 100)
    private String name;

    @Getter
    @Setter
    @Column(name = "repeating_period")
    private int repeatingPeriod;

    @Getter
    @Setter
    @Column(name = "terms")
    @OneToMany(mappedBy="lesson")
    private List<Term> terms;
}
