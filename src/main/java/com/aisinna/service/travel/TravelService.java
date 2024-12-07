package com.aisinna.service.travel;

import com.aisinna.domain.*;
import com.aisinna.dto.TravelPlanDetailDTO;
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


    private TravelSpot createSpot(TravelPlanDetailDTO.TravelSpotDTO travelSpotDTO) {

        TravelSpot spot = TravelSpot.builder()
                .addr1(travelSpotDTO.getAddr1())
                .addr2(travelSpotDTO.getAddr2())
                .areacode(travelSpotDTO.getAreacode())
                .booktour(travelSpotDTO.getBooktour())
                .cat1(travelSpotDTO.getCat1())
                .cat2(travelSpotDTO.getCat2())
                .cat3(travelSpotDTO.getCat3())
                .contentid(travelSpotDTO.getContentid())
                .contenttypeid(travelSpotDTO.getContenttypeid())
                .createdtime(travelSpotDTO.getCreatedtime())
                .dist(travelSpotDTO.getDist())
                .firstimage(travelSpotDTO.getFirstimage())
                .firstimage2(travelSpotDTO.getFirstimage2())
                .cpyrhtDivCd(travelSpotDTO.getCpyrhtDivCd())
                .mapx(travelSpotDTO.getMapx())
                .mapy(travelSpotDTO.getMapy())
                .mlevel(travelSpotDTO.getMlevel())
                .modifiedtime(travelSpotDTO.getModifiedtime())
                .sigungucode(travelSpotDTO.getSigungucode())
                .tel(travelSpotDTO.getTel())
                .title(travelSpotDTO.getTitle())
                .build();

        return travelSpotRepository.save(spot);
    }
}
