package com.aisinna.dto.tourAPI;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RegionCodeDTO {

    @Schema(description = "지역 코드", example = "0")
    private final String code;  // 지역 코드
    @Schema(description = "지역 이름", example = "전국")
    private final String name; // 지역 이름
}
