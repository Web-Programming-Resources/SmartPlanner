package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @Getter
    @Setter
    @Column(name = "plan_id")
    private int id;

    @Getter
    @Setter
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private int userId;

    @Getter
    @Setter
    @Column(name = "plan_name", length = 100)
    private String name;

    @Getter
    @Setter
    @Column(name = "max_days_in_cycle")
    private int maxDaysInCycle;

    @Getter
    @Setter
    @Column(name = "max_commutes_per_day")
    private int maxCommutesPerDay;

    @Getter
    @Setter
    @Column(name = "optimized_activity_opens_at")
    private String optimizedActivityOpensAt;

    @Getter
    @Setter
    @Column(name = "optimized_activity_closes_at")
    private String optimizedActivityClosesAt;

    @Getter
    @Setter
    @Column(name = "commute_matrix")
    @ElementCollection(targetClass = Integer.class)
    private List<List<Integer>> commuteMatrix;
}
