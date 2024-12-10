package com.aisinna.service.travel;

import com.aisinna.domain.TravelPlan;
import com.aisinna.domain.UserInfo;
import com.aisinna.domain.UserTravel;
import com.aisinna.dto.TravelPlanDTO;
import com.aisinna.dto.UserTravelSummaryDTO;
import com.aisinna.dto.openAI.TravelThemeRecommendationDTO;
import com.aisinna.repository.TravelPlanRepository;
import com.aisinna.repository.UserTravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserTravelService {

    private final UserTravelRepository userTravelRepository;
    private final TravelPlanRepository travelPlanRepository;

    private final TravelRecommendService travelRecommendService;
    private final TravelService travelService;

    // 사용자 여행 생성
    @Transactional
    public Long saveUserTravel(UserInfo userInfo, Long travelPlanId, LocalDate startDate, Integer peopleCount) {


        TravelPlan travelPlan = travelPlanRepository.findById(travelPlanId)
                .orElseThrow(() -> new IllegalArgumentException("Travel Plan Not Found."));

        UserTravel userTravel = UserTravel.builder()
                .userInfo(userInfo)
                .peopleCount(peopleCount)
                .startDate(startDate)
                .endDate(startDate.plusDays(travelPlan.getItineraryDays().size()-1))
                .travelPlan(travelPlan)
                .build();

        return userTravelRepository.save(userTravel).getId();
    }


    // 사용자의 모든 여행 썸네일 반환
    public List<UserTravelSummaryDTO> getAllMyTravel(UserInfo userInfo) {

        return userTravelRepository.findByUserInfo(userInfo).stream()
                .map(travel -> UserTravelSummaryDTO.builder()
                        .thumbnailImage(travel.getTravelPlan().getTravelRecommend().getImage())
                        .userTravelId(travel.getId())
                        .startDate(travel.getStartDate().toString())
                        .endDate(travel.getEndDate().toString())
                        .build())
                .collect(Collectors.toList());
    }

    // 특정 UserTravel에 맞춰 TravelPlan 일정 조정 및 반환
    public TravelPlanDTO getMyTravel(UserInfo userInfo, Long userTravelId) {

        // UserTravel 조회
        UserTravel userTravel = userTravelRepository.findByIdAndUserInfo(userTravelId, userInfo)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found"));

        // TravelPlan 가져오기
        TravelPlan travelPlan = userTravel.getTravelPlan();

        // TravelPlan을 DTO로 변환
        return convertToMyTravelPlanDTO(travelPlan, userTravel);
    }

    // 다가오는
    public UserTravel getOncomingTravel(UserInfo userInfo) {

        return userTravelRepository.findFirstByUserInfoAndEndDateAfterOrderByStartDateAsc(userInfo, LocalDate.now())
                .orElse(null);
    }

    public void deleteTravel(UserInfo userInfo, Long travelId) {

        UserTravel travel = userTravelRepository.findByIdAndUserInfo(travelId, userInfo)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found"));

        userTravelRepository.delete(travel);
    }

    // TravelPlan을 TravelPlanDTO로 변환
    private TravelPlanDTO convertToMyTravelPlanDTO(TravelPlan travelPlan, UserTravel userTravel) {

        TravelPlanDTO dto = TravelPlanDTO.fromEntity(travelPlan);

        dto.setStartDate(userTravel.getStartDate());
        dto.setEndDate(userTravel.getEndDate());
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long createUserTravelPlan(UserInfo userInfo, TravelThemeRecommendationDTO recommendDTO, int people, int cost, LocalDate startDate) {
        try {
            Long recommendId = travelRecommendService.saveTravelRecommend(recommendDTO).getId();
            TravelPlan travelPlan = travelService.createTravelPlanFromRecommend(recommendId, people, cost);

            return this.saveUserTravel(userInfo, travelPlan.getId(), startDate, people);
        } catch (Exception e) {
            log.error("Exception occurred during transaction: {}", e.getMessage(), e);
            throw e;
        }
    }
}
