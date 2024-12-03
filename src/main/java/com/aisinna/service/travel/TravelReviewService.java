package com.aisinna.service.travel;

import com.aisinna.domain.TravelReview;
import com.aisinna.domain.TravelReviewRepository;
import com.aisinna.domain.UserInfo;
import com.aisinna.domain.UserTravel;
import com.aisinna.dto.TravelReviewDTO;
import com.aisinna.repository.UserTravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TravelReviewService {

    private final UserTravelRepository userTravelRepository;
    private final TravelReviewRepository travelReviewRepository;

    public TravelReview createReview(UserInfo userInfo, Long travelId, TravelReviewDTO reviewDTO) {

        UserTravel travel = userTravelRepository.findByIdAndUserInfo(travelId, userInfo)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found"));

        TravelReview review = TravelReview.builder()
                .userTravel(travel)
                .userInfo(userInfo)
                .emotion(reviewDTO.getEmotion())
                .rating(reviewDTO.getRating())
                .reviewText(reviewDTO.getReviewText())
                .build();

        return travelReviewRepository.save(review);
    }
}
