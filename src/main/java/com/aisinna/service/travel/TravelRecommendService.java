package com.aisinna.service.travel;

import com.aisinna.domain.TravelRecommend;
import com.aisinna.domain.enums.RegionCode;
import com.aisinna.repository.TravelRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelRecommendService {

    private final TravelRecommendRepository travelRecommendRepository;

    // AI 추천 여행지 생성 (예시 데이터 생성)
    public List<TravelRecommend> generateAIRecommendations(String theme) {
        List<TravelRecommend> recommendations = new ArrayList<>();

        // Example: Create dummy recommendations based on theme
        recommendations.add(TravelRecommend.builder()
                .title(theme + " in Seoul")
                .description("Explore Seoul with a focus on " + theme)
                .theme(theme)
                .regionCode(RegionCode.SEOUL)
                .build());

        recommendations.add(TravelRecommend.builder()
                .title(theme + " in Busan")
                .description("Enjoy Busan's attractions with a " + theme + " theme")
                .theme(theme)
                .regionCode(RegionCode.BUSAN)
                .build());

        return travelRecommendRepository.saveAll(recommendations);
    }
}