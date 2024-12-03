package com.aisinna.repository;

import com.aisinna.domain.UserTravel;
import com.aisinna.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserTravelRepository extends JpaRepository<UserTravel, Long> {
    List<UserTravel> findByUserInfoAndStartDateBetween(UserInfo userInfo, LocalDate startDate, LocalDate endDate);
    Optional<UserTravel> findByIdAndUserInfo(Long travelId, UserInfo userInfo);

    Optional<UserTravel> findByUserInfo(UserInfo userInfo);

    Optional<UserTravel> findFirstByUserInfoAndStartDateAfterOrderByStartDateAsc(UserInfo userInfo, LocalDate startDate);

}