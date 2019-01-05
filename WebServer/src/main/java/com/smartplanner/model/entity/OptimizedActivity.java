package com.smartplanner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "optimized_activity")
public class OptimizedActivity {

    @Id
    @Column(name = "optimized_activity_id")
    private int id;

    @Column(name = "optimized_activity_name", length = 100)
    private String name;

    @Column(name = "optimized_activity_starts_at")
    private LocalTime startsAt;

    @Column(name = "optimized_activity_ends_at")
    private LocalTime endsAt;

    @Column(name = "optimized_activity_min_time")
    private int minTimeSpentAtOptimizedActivityAtOnceInMinutes;

    @Column(name = "optimized_activity_max_time")
    private int maxTimeSpentAtOptimizedActivityAtOnceInMinutes;

    @Column(name = "optimized_activity_is_opened_in_day")
    @ElementCollection(targetClass = Boolean.class)
    private List<Boolean> isOpenedInDay;

    public Boolean isOpenedInDay(int dayNumber) {
        return isOpenedInDay.get(dayNumber);
    }
}
