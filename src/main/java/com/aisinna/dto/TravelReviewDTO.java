package com.aisinna.dto;

import com.aisinna.domain.enums.Emotion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelReviewDTO {
    @Schema(description = "감정(BAD|GOOD|BEST)", example = "BEST")
    private Emotion emotion;
    @Schema(description = "별점", example = "4")
    private Float rating;
    @Schema(description = "리뷰", example = "분위기 좋은 카페들이 많은 여행이었어요!")
    private String reviewText;
}
