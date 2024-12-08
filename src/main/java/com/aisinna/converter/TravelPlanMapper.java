package com.aisinna.converter;

import com.aisinna.domain.TravelActivity;
import com.aisinna.domain.TravelItineraryDay;
import com.aisinna.domain.TravelPlan;
import com.aisinna.domain.TravelSpot;
import com.aisinna.dto.TravelPlanResponseDTO;
import com.aisinna.repository.TravelSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TravelPlanMapper {

    private final TravelSpotRepository travelSpotRepository;

    public TravelPlan toEntity(TravelPlanResponseDTO responseDTO) {
        validateResponseDTO(responseDTO);

        TravelPlanResponseDTO.TravelPlanDTO travelPlanDTO = responseDTO.getTravelPlan();

        TravelPlan travelPlan = TravelPlan.builder()
                .title(travelPlanDTO.getTitle())
                .build();

        travelPlanDTO.getItinerary().getDays().forEach(dayDTO -> {
            TravelItineraryDay itineraryDay = TravelItineraryDay.builder()
                    .day(dayDTO.getDay())
                    .build();

            dayDTO.getActivities().forEach(activityDTO -> {
                TravelSpot travelSpot = travelSpotRepository.findById(activityDTO.getContentId())
                        .orElseThrow(() -> new IllegalArgumentException("TravelSpot not found with id: " + activityDTO.getContentId()));

                TravelActivity activity = TravelActivity.builder()
                        .name(activityDTO.getName())
                        .location(activityDTO.getLocation())
                        .type(activityDTO.getType())
                        .travelTime(activityDTO.getTravelTime())
                        .startTime(activityDTO.getStartTime())
                        .endTime(activityDTO.getEndTime())
                        .travelSpot(travelSpot)
                        .build();

                itineraryDay.addActivity(activity);
            });

            travelPlan.addItineraryDay(itineraryDay);
        });

        return travelPlan;
    }

    private void validateResponseDTO(TravelPlanResponseDTO responseDTO) {
        if (responseDTO == null) {
            throw new IllegalArgumentException("ResponseDTO cannot be null.");
        }
        if (responseDTO.getTravelPlan() == null) {
            throw new IllegalArgumentException("ResponseDTO must contain a travel plan.");
        }
        if (responseDTO.getTravelPlan().getItinerary() == null ||
                responseDTO.getTravelPlan().getItinerary().getDays() == null) {
            throw new IllegalArgumentException("Itinerary or Days in TravelPlanDTO are missing.");
        }
    }
}

