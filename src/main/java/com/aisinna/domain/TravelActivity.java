package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String type; // "spot", "meal", "hotel"
    private String travelTime;
    private String startTime;
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "travel_spot_id", nullable = true)
    private TravelSpot travelSpot;

    @ManyToOne
    @JoinColumn(name = "itinerary_day_id", nullable = false)
    private TravelItineraryDay itineraryDay;

    public void setItineraryDay(TravelItineraryDay itineraryDay) {
        this.itineraryDay = itineraryDay;
    }
}
