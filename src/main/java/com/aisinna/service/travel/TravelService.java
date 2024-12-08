package com.aisinna.service.travel;

import com.aisinna.converter.TravelPlanMapper;
import com.aisinna.domain.*;
import com.aisinna.dto.TravelPlanResponseDTO;
import com.aisinna.dto.tourAPI.OpenAPIResponseDTO;
import com.aisinna.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelService {

    private final TravelRecommendRepository travelRecommendRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final TravelSpotRepository travelSpotRepository;
    private final TravelPlanMapper travelPlanMapper;

    private final RestTemplate restTemplate;



    // 추천 조회 시 TravelPlan 생성
    @Retryable(
            maxAttempts = 3,                    // 최대 시도 횟수
            backoff = @Backoff(delay = 1000)    // 재시도 간격 (밀리초)
    )
    @Transactional
    public TravelPlan createTravelPlanFromRecommend(Long recommendId, int people, int cost) {
        TravelRecommend travelRecommend = travelRecommendRepository.findById(recommendId)
                .orElseThrow(() -> new IllegalArgumentException("Recommendation not found"));

        RestTemplate restTemplate = new RestTemplate();
        String fastApiUrl = "http://152.67.209.153:8000/ai/travel-plan";

        Map<String, Object> requestPayload = Map.of(
                "title", travelRecommend.getTitle(),
                "location", travelRecommend.getLocation(),
                "image", travelRecommend.getImage(),
                "duration", travelRecommend.getDuration(),
                "description", travelRecommend.getDescription(),
                "people", people,
                "cost", cost
        );

        // 3. FastAPI 호출
        ResponseEntity<TravelPlanResponseDTO> response = restTemplate.postForEntity(
                fastApiUrl,
                requestPayload,
                TravelPlanResponseDTO.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("FastAPI 요청 실패");
        }

        TravelPlanResponseDTO responseDTO = response.getBody();

        // 4. TravelPlan 저장
        TravelPlan travelPlan = travelPlanMapper.toEntity(responseDTO);


        travelPlanRepository.findByTravelRecommend(travelRecommend)
                .ifPresent(travelPlanRepository::delete);


        travelPlan = travelPlanRepository.save(travelPlan);

        travelRecommend.setTravelPlan(travelPlan);
        travelRecommendRepository.save(travelRecommend);

        return travelPlan;
    }

    @Transactional
    public void saveTravelSpots(OpenAPIResponseDTO openAPIResponseDTO) {
        // DTO 리스트를 엔티티 리스트로 변환
        List<TravelSpot> travelSpots = openAPIResponseDTO.getResponse().getBody().getItems().getItem().stream()
                .map(item -> TravelSpot.builder()
                        .contentid(item.getContentid())
                        .addr1(item.getAddr1())
                        .addr2(item.getAddr2())
                        .areacode(item.getAreacode())
                        .booktour(item.getBooktour())
                        .cat1(item.getCat1())
                        .cat2(item.getCat2())
                        .cat3(item.getCat3())
                        .contenttypeid(item.getContenttypeid())
                        .createdtime(item.getCreatedtime())
                        .firstimage(item.getFirstimage())
                        .firstimage2(item.getFirstimage2())
                        .cpyrhtDivCd(item.getCpyrhtDivCd())
                        .mapx(item.getMapx())
                        .mapy(item.getMapy())
                        .mlevel(item.getMlevel())
                        .modifiedtime(item.getModifiedtime())
                        .sigungucode(item.getSigungucode())
                        .tel(item.getTel())
                        .title(item.getTitle())
                        .build())
                .collect(Collectors.toList());

        // 데이터베이스에 저장
        travelSpotRepository.saveAll(travelSpots);
    }
}
