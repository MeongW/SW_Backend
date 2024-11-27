package com.aisinna.dto.tourAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RegionCodeDTO {

    private final String code;  // 지역 코드
    private final String name; // 지역 이름
}
