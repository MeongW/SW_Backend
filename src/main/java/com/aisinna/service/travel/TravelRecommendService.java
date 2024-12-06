package com.aisinna.service.travel;

import com.aisinna.domain.TravelRecommend;
import com.aisinna.dto.UserTravelPreferenceDTO;
import com.aisinna.dto.openAI.TravelRecommendationsResponseDTO;
import com.aisinna.dto.openAI.TravelThemeRecommendationDTO;
import com.aisinna.repository.TravelRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelRecommendService {

    private final TravelRecommendRepository travelRecommendRepository;
    private final RestTemplate restTemplate;

    // 추천 데이터 ChatGPT로 생성
    public List<TravelThemeRecommendationDTO> generateTravelRecommend(Double mapX, Double mapY, List<UserTravelPreferenceDTO> preferences) {
        // 예시 데이터 (ChatGPT에서 생성된 데이터를 대체)

        String fastApiUrl = "http://api.smartcheers.site/ai/travel-recommendations"; // FastAPI 엔드포인트

        try {
            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("preferences", preferences);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            // FastAPI로 POST 요청
            ResponseEntity<TravelRecommendationsResponseDTO> response = restTemplate.postForEntity(
                    fastApiUrl, request, TravelRecommendationsResponseDTO.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getRecommends();
                // 처리 로직
            } else {
                throw new RuntimeException("FastAPI 응답 오류: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("FastAPI 서버와의 통신 오류: " + e.getMessage(), e);
        }
    }

    public TravelRecommend saveTravelRecommend(TravelThemeRecommendationDTO recommend) {
        return travelRecommendRepository.save(TravelRecommend.builder()
                .title(recommend.getTitle())
                .description(recommend.getDescription())
                .location(recommend.getLocation())
                .image(recommend.getImage())
                .duration(recommend.getDuration())
                .build());
    }
}