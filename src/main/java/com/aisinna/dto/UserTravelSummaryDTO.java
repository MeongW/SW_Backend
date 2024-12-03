package com.aisinna.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTravelSummaryDTO {
    private Long userTravelId;
    private String startDate;
    private String endDate;
    private String region;
    private String theme;
}