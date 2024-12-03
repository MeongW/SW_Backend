package com.aisinna.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTravelPreferenceDTO {
    private String preferenceType;
    private String preferenceValue;
    private int priority;
}
