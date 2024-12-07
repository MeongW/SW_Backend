package com.aisinna.repository;

import com.aisinna.domain.TravelRecommend;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRecommendRepository extends JpaRepository<TravelRecommend, Long> {

    @Query("SELECT tr FROM TravelRecommend tr ORDER BY RAND()")
    List<TravelRecommend> findRandom2TravelRecommends(Pageable pageable);
}