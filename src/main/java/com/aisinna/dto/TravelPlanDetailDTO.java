package com.aisinna.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TravelPlanDetailDTO {
    private Long travelPlanId;
    private String title;
    private String adjustedStartDate;
    private String adjustedEndDate;
    private List<TravelSpotDTO> travelSpots;

    @Getter
    @Builder
    public static class TravelSpotDTO {
        private String title;
        private String address;
        private String mapX;
        private String mapY;
        private String imageUrl;
    }
}