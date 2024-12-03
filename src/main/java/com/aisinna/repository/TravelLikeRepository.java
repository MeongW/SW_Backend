package com.aisinna.repository;

import com.aisinna.domain.TravelLike;
import com.aisinna.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelLikeRepository extends JpaRepository<TravelLike, Long> {
    List<TravelLike> findByUserInfo(UserInfo userInfo);
}
