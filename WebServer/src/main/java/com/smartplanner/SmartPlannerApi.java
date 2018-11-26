package com.smartplanner;

import com.smartplanner.repository.PlanRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.smartplanner.service"})
@EntityScan("com.smartplanner.model")
@EnableJpaRepositories(basePackageClasses = PlanRepository.class)
public class SmartPlannerApi {

	public static void main(String[] args) {
		SpringApplication.run(SmartPlannerApi.class, args);
	}
}
