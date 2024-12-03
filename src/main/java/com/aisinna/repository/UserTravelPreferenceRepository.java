package com.aisinna.repository;

import com.aisinna.domain.UserTravelPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTravelPreferenceRepository extends JpaRepository<UserTravelPreference, Long> {

    Optional<UserTravelPreference> findByUserInfoId(Long userInfoId);
}