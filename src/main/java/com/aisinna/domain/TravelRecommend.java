package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TravelRecommend extends BaseEntity {

    private String title;
    private String description;
    private String location;
    private String image;
    private String duration;

    @OneToOne
    @JoinColumn(name = "travel_plan_id")
    @Setter
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "travelRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTravel> userTravels = new ArrayList<>();

    @OneToMany(mappedBy = "travelRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelLike> travelLikes = new ArrayList<>();
}
