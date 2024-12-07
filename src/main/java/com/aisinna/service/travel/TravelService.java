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

//        List<TravelSpot> spots = generateSpotsForTravelPlan(travelPlan);
//        travelPlan.getTravelSpotList().addAll(spots);
//
//        return travelPlanRepository.save(travelPlan);

        return null;
    }


//    public void saveTravelSpots(OpenAPIResponseDTO response) {
//        // DTO 리스트를 엔티티 리스트로 변환
//        List<TravelSpot> travelSpots = response.getResponse().getBody().getItems().getItem().stream()
//                .map(item -> TravelSpot.builder()
//                        .contentid(item.getContentid())
//                        .addr1(item.getAddr1())
//                        .addr2(item.getAddr2())
//                        .areacode(item.getAreacode())
//                        .booktour(item.getBooktour())
//                        .cat1(item.getCat1())
//                        .cat2(item.getCat2())
//                        .cat3(item.getCat3())
//                        .contenttypeid(item.getContenttypeid())
//                        .createdtime(item.getCreatedtime())
//                        .firstimage(item.getFirstimage())
//                        .firstimage2(item.getFirstimage2())
//                        .cpyrhtDivCd(item.getCpyrhtDivCd())
//                        .mapx(item.getMapx())
//                        .mapy(item.getMapy())
//                        .mlevel(item.getMlevel())
//                        .modifiedtime(item.getModifiedtime())
//                        .sigungucode(item.getSigungucode())
//                        .tel(item.getTel())
//                        .title(item.getTitle())
//                        .build())
//                .collect(Collectors.toList());
//
//        // 데이터베이스에 저장
//        travelSpotRepository.saveAll(travelSpots);
//    }
}
