package com.aisinna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TravelPreferenceDTO {
    private String category;
    private List<EnumResponse> preferences;

    @Getter
    @AllArgsConstructor
    public static class EnumResponse {
        private String name;
        private String value;
    }
}
