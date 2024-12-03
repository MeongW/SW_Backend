package com.aisinna.dto;

import com.aisinna.domain.enums.Emotion;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelReviewDTO {
    private Emotion emotion;
    private Float rating;
    private String reviewText;
}
