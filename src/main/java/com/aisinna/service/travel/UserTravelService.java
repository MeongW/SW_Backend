package com.aisinna.service.travel;

import com.aisinna.domain.TravelPlan;
import com.aisinna.domain.UserInfo;
import com.aisinna.domain.UserTravel;
import com.aisinna.dto.TravelPlanDetailDTO;
import com.aisinna.dto.UserTravelSummaryDTO;
import com.aisinna.repository.TravelRecommendRepository;
import com.aisinna.repository.UserTravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserTravelService {

    private final UserTravelRepository userTravelRepository;
    private final TravelRecommendRepository travelRecommendRepository;

    // 사용자 여행 생성
    public UserTravel saveUserTravel(UserInfo userInfo, Long travelRecommendId, LocalDate startDate, LocalDate endDate) {

        UserTravel userTravel = UserTravel.builder()
                .userInfo(userInfo)
                .startDate(startDate)
                .endDate(endDate)
                .travelRecommend(travelRecommendRepository.findById(travelRecommendId).orElseThrow(() ->new IllegalArgumentException("Travel Recommend Not Found."))) // Placeholder for recommendation
                .build();

        return userTravelRepository.save(userTravel);
    }

    // 사용자의 모든 여행 썸네일 반환
    public List<UserTravelSummaryDTO> getAllMyTravel(UserInfo userInfo) {

        return userTravelRepository.findByUserInfo(userInfo).stream()
                .map(travel -> UserTravelSummaryDTO.builder()
                        .userTravelId(travel.getId())
                        .startDate(travel.getStartDate().toString())
                        .endDate(travel.getEndDate().toString())
                        .build())
                .collect(Collectors.toList());
    }

    // 특정 UserTravel에 맞춰 TravelPlan 일정 조정 및 반환
    public TravelPlanDetailDTO getMyTravel(UserInfo userInfo, Long userTravelId) {

        UserTravel userTravel = userTravelRepository.findByIdAndUserInfo(userTravelId, userInfo)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found"));

        TravelPlan travelPlan = userTravel.getTravelRecommend().getTravelPlan();

        LocalDate startDate = userTravel.getStartDate();
        List<TravelPlanDetailDTO.TravelSpotDTO> travelSpots = travelPlan.getTravelSpotList().stream()
                .map(spot -> TravelPlanDetailDTO.TravelSpotDTO.builder()
                        .title(spot.getTitle())
                        .address(spot.getAddr1())
                        .mapX(spot.getMapX())
                        .mapY(spot.getMapY())
                        .imageUrl(spot.getFirstImage())
                        .build())
                .toList();

        return TravelPlanDetailDTO.builder()
                .travelPlanId(travelPlan.getId())
                .title(travelPlan.getTitle())
                .adjustedStartDate(startDate.toString())
                .adjustedEndDate(userTravel.getEndDate().toString())
                .travelSpots(travelSpots)
                .build();
    }

    // 다가오는
    public UserTravel getOncomingTravel(UserInfo userInfo) {

        return userTravelRepository.findFirstByUserInfoAndStartDateAfterOrderByStartDateAsc(userInfo, LocalDate.now())
                .orElse(null);
    }

    public void deleteTravel(UserInfo userInfo, Long travelId) {

        UserTravel travel = userTravelRepository.findByIdAndUserInfo(travelId, userInfo)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found"));

        userTravelRepository.delete(travel);
    }
}
