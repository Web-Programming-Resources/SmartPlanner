package com.smartplanner.service.implementation;

import com.smartplanner.model.SmartPlanner;
import com.smartplanner.model.TimeDistanceManager;
import com.smartplanner.model.TimetableEntry;
import com.smartplanner.model.TimetableWithDecisionPointsAndScore;
import com.smartplanner.model.dto.SmartPlannerInputDto;
import com.smartplanner.model.entity.Lesson;
import com.smartplanner.model.entity.Plan;
import com.smartplanner.repository.PlanRepository;
import com.smartplanner.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository) {
        this(planRepository, new ModelMapper());
    }

    public PlanServiceImpl(PlanRepository planRepository, ModelMapper modelMapper) {
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }

    public boolean findPlanById(int id) {
        return planRepository.existsById(id);
    }

    public Plan getPlanById(int id) {
        return planRepository.getOne(id);
    }

    @Override
    public List<Lesson> generateOptimalPlan(SmartPlannerInputDto smartPlannerInputDto) {
        SmartPlanner smartPlanner = new SmartPlanner(
                smartPlannerInputDto.getLessons(),
                smartPlannerInputDto.getDaysInCycle(),
                new TimeDistanceManager(smartPlannerInputDto.getTimeDistanceInMinutes()),
                smartPlannerInputDto.getMaxCommutesPerDay(),
                smartPlannerInputDto.getOptimizedActivity()
        );

        TimetableWithDecisionPointsAndScore timetable = smartPlanner.getOptimalPlan();

        List<TimetableEntry> timetableEntries = timetable.getOptimalTimetable();
        List<Lesson> lessons = new ArrayList<>();

        timetableEntries.forEach(x -> lessons.add(modelMapper.map(x.getLesson(), Lesson.class)));

        return lessons;
    }
}
