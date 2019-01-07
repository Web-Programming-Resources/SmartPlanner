package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @Column(name = "plan_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "plan")
    private OptimizedActivity optimizedActivity;

    @Column(name = "plan_name", length = 100)
    private String name;

    @Column(name = "max_days_in_cycle")
    private int maxDaysInCycle;

    @Column(name = "max_commutes_per_day")
    private int maxCommutesPerDay;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @Column(name = "time_distance")
    @ElementCollection(targetClass = Integer.class)
    private List<List<Integer>> timeDistanceInMinutes;
}
