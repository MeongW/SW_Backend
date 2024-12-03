package com.aisinna.repository;

import com.aisinna.domain.TravelSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelSpotRepository extends JpaRepository<TravelSpot, Long> {
}