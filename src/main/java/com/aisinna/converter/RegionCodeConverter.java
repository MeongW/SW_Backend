package com.aisinna.converter;

import com.aisinna.domain.enums.RegionCode;
import com.aisinna.dto.tourAPI.RegionCodeDTO;

public class RegionCodeConverter {
    public static RegionCodeDTO toDTO(RegionCode regionCode) {

        return RegionCodeDTO.builder()
                .code(regionCode.getCode())
                .name(regionCode.getName())
                .build();
    }
}
