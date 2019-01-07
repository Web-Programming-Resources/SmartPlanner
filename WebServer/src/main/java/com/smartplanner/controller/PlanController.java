package com.smartplanner.controller;

import com.smartplanner.exception.ResourceNotFoundException;
import com.smartplanner.model.dto.SmartPlannerInputDto;
import com.smartplanner.model.dto.SmartPlannerOutputDto;
import com.smartplanner.model.entity.Plan;
import com.smartplanner.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;
    private ModelMapper modelMapper;

    @Autowired
    public PlanController(PlanService planService) {
        this(planService, new ModelMapper());
    }

    public PlanController(PlanService planService, ModelMapper modelMapper) {
        this.planService = planService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public Plan getPlanById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        if (!planService.findPlanById(id)) {
            throw new ResourceNotFoundException("Plan", "id", id);
        }

        return planService.getPlanById(id);
    }

    @PostMapping()
    public SmartPlannerOutputDto createPlan(@RequestBody SmartPlannerInputDto smartPlannerInputDto) {
        SmartPlannerOutputDto smartPlannerOutputDto =
                modelMapper.map(smartPlannerInputDto, SmartPlannerOutputDto.class);

        smartPlannerOutputDto.setLessons(planService.generateOptimalPlan(smartPlannerInputDto));

        return smartPlannerOutputDto;
    }
}
