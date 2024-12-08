package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelItineraryDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int day;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
    @Setter
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "itineraryDay", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // 필드 초기화
    private List<TravelActivity> activities = new ArrayList<>();

    public void addActivity(TravelActivity activity) {
        this.activities.add(activity);
        activity.setItineraryDay(this);
    }
}