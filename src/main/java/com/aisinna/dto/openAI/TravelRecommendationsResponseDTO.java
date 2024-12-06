package com.aisinna.dto.openAI;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TravelRecommendationsResponseDTO {

    @Schema(description = "추천 리스트")
    private List<TravelThemeRecommendationDTO> recommends;
}
