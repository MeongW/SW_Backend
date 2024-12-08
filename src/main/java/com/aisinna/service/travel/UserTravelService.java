package com.aisinna.service.travel;

import com.aisinna.domain.TravelPlan;
import com.aisinna.domain.TravelRecommend;
import com.aisinna.domain.UserInfo;
import com.aisinna.domain.UserTravel;
import com.aisinna.dto.TravelPlanDTO;
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
    @Transactional
    public void saveUserTravel(UserInfo userInfo, Long travelRecommendId, LocalDate startDate) {

        // TravelRecommend 조회
        TravelRecommend travelRecommend = travelRecommendRepository.findById(travelRecommendId)
                .orElseThrow(() -> new IllegalArgumentException("Travel Recommend Not Found."));

        if (travelRecommend.getTravelPlan() == null) {
            throw new IllegalArgumentException("Travel Plan Not Found.");
        }

        // UserTravel 생성
        UserTravel userTravel = UserTravel.builder()
                .userInfo(userInfo)
                .startDate(startDate)
                .endDate(startDate.plusDays(travelRecommend.getTravelPlan().getItineraryDays().size()-1))
                .travelRecommend(travelRecommend)
                .build();

        userTravelRepository.save(userTravel);
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
    public TravelPlanDTO getMyTravel(UserInfo userInfo, Long userTravelId) {

        // UserTravel 조회
        UserTravel userTravel = userTravelRepository.findByIdAndUserInfo(userTravelId, userInfo)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found"));

        // TravelPlan 가져오기
        TravelPlan travelPlan = userTravel.getTravelRecommend().getTravelPlan();

        // TravelPlan을 DTO로 변환
        return convertToMyTravelPlanDTO(travelPlan, userTravel);
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

    // TravelPlan을 TravelPlanDTO로 변환
    private TravelPlanDTO convertToMyTravelPlanDTO(TravelPlan travelPlan, UserTravel userTravel) {

        TravelPlanDTO dto = TravelPlanDTO.fromEntity(travelPlan);

        dto.setStartDate(userTravel.getStartDate());
        dto.setEndDate(userTravel.getEndDate());
        return dto;
    }


}
