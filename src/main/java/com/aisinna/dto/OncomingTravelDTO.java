package com.aisinna.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OncomingTravelDTO {
    private Long userTravelId;
    private String region;
    private String startDate;
    private String endDate;
    private String theme;
}