package com.aisinna.repository;

import com.aisinna.domain.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
}