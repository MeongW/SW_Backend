package com.aisinna.service.travel;

import com.aisinna.domain.*;
import com.aisinna.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelService {

    private final TravelRecommendRepository travelRecommendRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final TravelSpotRepository travelSpotRepository;

    private final RestTemplate restTemplate;



    // 추천 조회 시 TravelPlan 생성
    public TravelPlan createTravelPlanFromRecommend(Long recommendId) {
        TravelRecommend recommend = travelRecommendRepository.findById(recommendId)
                .orElseThrow(() -> new IllegalArgumentException("Recommendation not found"));

        TravelPlan travelPlan = TravelPlan.builder()
                .title(recommend.getTitle())
                .shareID(UUID.randomUUID().toString())
                .build();

        List<TravelSpot> spots = generateSpotsForTravelPlan(travelPlan);
        travelPlan.getTravelSpotList().addAll(spots);

        return travelPlanRepository.save(travelPlan);
    }

    // 여행 장소 및 세부 정보 생성
    private List<TravelSpot> generateSpotsForTravelPlan(TravelPlan travelPlan) {
        List<TravelSpot> spots = new ArrayList<>();

        // 예시 장소 데이터 (ChatGPT에서 생성된 데이터를 대체)
        spots.add(createSpot(travelPlan, "Location 1", "서울시 중구", "", "http://image.com", "", "127.123", "37.123"));
        spots.add(createSpot(travelPlan, "Location 2", "서울시 은평구", "", "http://image2.com", "", "128.456", "36.456"));
        spots.add(createSpot(travelPlan, "Location 3", "서울시 강남구", "", "http://image3.com", "", "129.789", "35.789"));

        return spots;
    }

    private TravelSpot createSpot(TravelPlan travelPlan, String title, String addr1, String addr2, String imageUrl, String imageUrl2, String mapX, String mapY) {

        TravelSpot spot = TravelSpot.builder()
                .title(title)
                .addr1(addr1)
                .addr2(addr2)
                .firstImage(imageUrl)
                .firstImage2(imageUrl2)
                .mapX(mapX)
                .mapY(mapY)
                .travelPlan(travelPlan)
                .build();

        return travelSpotRepository.save(spot);
    }
}
