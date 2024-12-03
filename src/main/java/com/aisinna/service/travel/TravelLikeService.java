package com.aisinna.service.travel;

import com.aisinna.domain.TravelLike;
import com.aisinna.domain.TravelRecommend;
import com.aisinna.domain.UserInfo;
import com.aisinna.repository.TravelLikeRepository;
import com.aisinna.repository.TravelRecommendRepository;
import com.aisinna.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelLikeService {

    private final TravelLikeRepository travelLikeRepository;
    private final TravelRecommendRepository travelRecommendRepository;

    // TravelRecommend 좋아요 저장
    public TravelLike likeTravelRecommend(UserInfo userInfo, Long recommendId) {
        TravelRecommend travelRecommend = travelRecommendRepository.findById(recommendId)
                .orElseThrow(() -> new IllegalArgumentException("Travel recommendation not found"));

        TravelLike like = TravelLike.builder()
                .userInfo(userInfo)
                .travelRecommend(travelRecommend)
                .build();
        return travelLikeRepository.save(like);
    }

    // 사용자가 좋아요한 TravelRecommend 조회
    public List<TravelLike> getLikedTravelRecommends(UserInfo userInfo) {
        return travelLikeRepository.findByUserInfo(userInfo);
    }
}
