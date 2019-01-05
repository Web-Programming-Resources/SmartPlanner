package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @Column(name = "lesson_id")
    private int id;

    @ManyToOne(targetEntity = Plan.class)
    @JoinColumn(name = "plan_id")
    private int planId;

    @Column(name = "lesson_name", length = 100)
    private String name;

    @Column(name = "repeating_period")
    private int repeatingPeriod;

    @Column(name = "terms")
    @OneToMany(mappedBy = "lesson")
    private List<Term> terms;
}
