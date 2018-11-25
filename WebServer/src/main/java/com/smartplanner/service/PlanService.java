package com.smartplanner.service;

import com.smartplanner.model.Plan;
import com.smartplanner.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    @Autowired
    PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public boolean findPlanById(int id) {
        return planRepository.existsById(id);
    }

    public Plan getPlanById(int id) {
        return planRepository.getOne(id);
    }
}
