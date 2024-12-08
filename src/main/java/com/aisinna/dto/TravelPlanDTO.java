package com.aisinna.dto;

import com.aisinna.domain.TravelPlan;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelPlanDTO {
    private String title;
    private List<ItineraryDayDTO> itinerary;
    private LocalDate startDate;
    private LocalDate endDate;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItineraryDayDTO {
        private int day;
        private List<ActivityDTO> activities;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
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

    public static TravelPlanDTO fromEntity(TravelPlan travelPlan) {
        if (travelPlan == null) {
            return null;
        }

        // ItineraryDays -> DTO 변환
        List<TravelPlanDTO.ItineraryDayDTO> itineraryDays = travelPlan.getItineraryDays().stream()
                .map(day -> TravelPlanDTO.ItineraryDayDTO.builder()
                        .day(day.getDay())
                        .activities(day.getActivities().stream()
                                .map(activity -> TravelPlanDTO.ItineraryDayDTO.ActivityDTO.builder()
                                        .name(activity.getName())
                                        .location(activity.getLocation())
                                        .type(activity.getType())
                                        .travelTime(activity.getTravelTime())
                                        .startTime(activity.getStartTime())
                                        .endTime(activity.getEndTime())
                                        .contentId(activity.getTravelSpot() != null ? activity.getTravelSpot().getContentid() : null)
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return TravelPlanDTO.builder()
                .title(travelPlan.getTitle())
                .itinerary(itineraryDays)
                .build();
    }
}





