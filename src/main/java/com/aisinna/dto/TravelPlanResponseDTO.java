package com.aisinna.dto;

import lombok.Data;
import java.util.List;

@Data
public class TravelPlanResponseDTO {
    private TravelPlanDTO travelPlan;

    @Data
    public static class TravelPlanDTO {
        private String title;
        private ItineraryDTO itinerary;

        @Data
        public static class ItineraryDTO {
            private List<DayDTO> days;

            @Data
            public static class DayDTO {
                private int day;
                private List<ActivityDTO> activities;

                @Data
                public static class ActivityDTO {
                    private String name;
                    private String location;
                    private String type;
                    private String travelTime;
                    private String startTime;
                    private String endTime;
                    private String contentId; // TravelSpot의 ID
                }
            }
        }
    }
}