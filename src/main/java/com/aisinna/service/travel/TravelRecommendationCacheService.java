package com.aisinna.service.travel;

import com.aisinna.dto.openAI.TravelThemeRecommendationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TravelRecommendationCacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CACHE_KEY_PREFIX = "travel-recommend:";

    /**
     * Redis에서 추천 데이터를 가져옵니다.
     */
    public List<TravelThemeRecommendationDTO> getRecommendations(String userId) {
        String cacheKey = CACHE_KEY_PREFIX + userId;
        Object cachedData = redisTemplate.opsForValue().get(cacheKey);

        if (cachedData != null) {
            try {
                // 캐시된 데이터를 List로 변환
                return (List<TravelThemeRecommendationDTO>) cachedData;
            } catch (ClassCastException e) {
                throw new RuntimeException("Redis 캐시 데이터 변환 오류", e);
            }
        }
        return null;
    }

    /**
     * 추천 데이터를 Redis에 저장합니다.
     */
    public void saveRecommendations(String userId, List<TravelThemeRecommendationDTO> recommendations) {
        if (recommendations == null || recommendations.isEmpty()) {
            return; // 빈 데이터는 저장하지 않음
        }

        String cacheKey = CACHE_KEY_PREFIX + userId;

        // 다음 00시까지의 남은 시간 계산
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.plusDays(1).with(LocalTime.MIN);
        long secondsUntilMidnight = Duration.between(now, midnight).getSeconds();

        // Redis에 데이터 저장
        redisTemplate.opsForValue().set(cacheKey, recommendations, secondsUntilMidnight, TimeUnit.SECONDS);
    }
}
