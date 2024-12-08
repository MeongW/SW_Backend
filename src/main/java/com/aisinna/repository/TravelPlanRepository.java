package com.aisinna.repository;

import com.aisinna.domain.TravelPlan;
import com.aisinna.domain.TravelRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

    Optional<TravelPlan> findByTravelRecommend(TravelRecommend travelRecommend);
}