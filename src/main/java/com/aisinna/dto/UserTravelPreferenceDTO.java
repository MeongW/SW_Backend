package com.aisinna.dto;

import com.aisinna.domain.enums.TravelPreference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTravelPreferenceDTO {
    @Schema(description="선호 여행 질문 타입", example="PURPOSE")
    @NotNull(message = "필수 파라미터입니다.")
    private TravelPreference.TravelPreferenceType preferenceType;

    @Schema(description="선호 여행 답변", example = "NATURE_EXPLORATION")
    @NotNull(message = "필수 파라미터입니다.")
    private TravelPreference.TravelPreferenceValue preferenceValue;

    @Schema(description="우선순위", example = "0")
    @Min(value = 0)
    private int priority;
}
