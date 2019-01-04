package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "optimized_activity")
public class OptimizedActivity {

    @Id
    @Getter
    @Setter
    @Column(name = "optimized_activity_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "optimized_activity_name", length = 100)
    private String name;

    @Getter
    @Setter
    @Column(name = "optimized_activity_opens_at")
    private LocalTime opensAt;

    @Getter
    @Setter
    @Column(name = "optimized_activity_closes_at")
    private LocalTime closesAt;

    @Getter
    @Setter
    @Column(name = "optimized_activity_min_time")
    private int minTimeSpentAtOptimizedActivityAtOnceInMinutes;

    @Getter
    @Setter
    @Column(name = "optimized_activity_max_time")
    private int maxTimeSpentAtOptimizedActivityAtOnceInMinutes;

    @Getter
    @Setter
    @Column(name = "optimized_activity_is_opened_in_day")
    @ElementCollection(targetClass = Boolean.class)
    private List<Boolean> isOpenedInDay;
}
