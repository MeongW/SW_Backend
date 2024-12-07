package com.aisinna.service.travel;

import com.aisinna.domain.TravelLike;
import com.aisinna.domain.TravelRecommend;
import com.aisinna.domain.UserInfo;
import com.aisinna.dto.openAI.TravelThemeRecommendationDTO;
import com.aisinna.repository.TravelLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelLikeService {

    private final TravelLikeRepository travelLikeRepository;
    private final TravelRecommendService travelRecommendService;

    // TravelRecommend 좋아요 저장
    public TravelLike likeTravelRecommend(UserInfo userInfo, TravelThemeRecommendationDTO recommendDto) {

        TravelRecommend travelRecommend = travelRecommendService.saveTravelRecommend(recommendDto);

        TravelLike like = TravelLike.builder()
                .userInfo(userInfo)
                .travelRecommend(travelRecommend)
                .build();
        return travelLikeRepository.save(like);
    }

    // 사용자가 좋아요한 TravelRecommend 조회
    public List<TravelThemeRecommendationDTO> getLikedTravelRecommends(UserInfo userInfo) {
        List<TravelLike> travelLikes = travelLikeRepository.findByUserInfo(userInfo);
        List<TravelThemeRecommendationDTO> recommendDtoList = new ArrayList<>();
        for (TravelLike travelLike: travelLikes) {
            TravelRecommend recommend = travelLike.getTravelRecommend();
            recommendDtoList.add(TravelThemeRecommendationDTO.builder()
                    .title(recommend.getTitle())
                    .description(recommend.getDescription())
                    .location(recommend.getLocation())
                    .image(recommend.getImage())
                    .duration(recommend.getDuration())
                    .build());
        }

        return recommendDtoList;
    }

    public List<TravelThemeRecommendationDTO> getTopLikedTravelRecommends() {
        List<TravelRecommend> travelRecommends = travelRecommendService.getRandom2TravelRecommends();
        List<TravelThemeRecommendationDTO> recommendDtoList = new ArrayList<>();
        for (TravelRecommend recommend: travelRecommends) {
            recommendDtoList.add(TravelThemeRecommendationDTO.builder()
                    .title(recommend.getTitle())
                    .description(recommend.getDescription())
                    .location(recommend.getLocation())
                    .image(recommend.getImage())
                    .duration(recommend.getDuration())
                    .build());
        }

        return recommendDtoList;
    }
}
