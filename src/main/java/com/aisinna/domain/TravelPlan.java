package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TravelPlan extends BaseEntity {

    private String title;

    private String shareID;

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // 필드 초기화
    private List<TravelItineraryDay> itineraryDays = new ArrayList<>();

    @Setter
    @OneToOne(mappedBy = "travelPlan")
    @JoinColumn(name = "travel_recommend_id")
    private TravelRecommend travelRecommend;

    @PrePersist
    private void generateShareID() {
        if (this.shareID == null) {
            this.shareID = UUID.randomUUID().toString();
        }
    }

    public void addItineraryDay(TravelItineraryDay day) {
        this.itineraryDays.add(day);
        day.setTravelPlan(this);
    }
}
