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

    List<UserTravel> findByUserInfo(UserInfo userInfo);

    Optional<UserTravel> findFirstByUserInfoAndEndDateAfterOrderByStartDateAsc(UserInfo userInfo, LocalDate startDate);

}