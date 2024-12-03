package com.aisinna.repository;

import com.aisinna.domain.TravelRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRecommendRepository extends JpaRepository<TravelRecommend, Long> {
}