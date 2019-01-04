package com.smartplanner.service;

import com.smartplanner.model.entity.Plan;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {

    boolean findPlanById(int id);

    Plan getPlanById(int id);
}
