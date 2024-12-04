package com.aisinna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TravelPreferenceDTO {
    @Schema(name="category", description="선호 여행 질문 카테고리", example = "purpose")
    private String category;
    private List<EnumResponse> preferences;

    @Getter
    @AllArgsConstructor
    public static class EnumResponse {

        @Schema(description="선호 여행 타입 Name", example="NATURE_EXPLORATION")
        private String name;
        @Schema(description="선호 여행 타입 Value", example = "자연탐방")
        private String value;
    }
}
