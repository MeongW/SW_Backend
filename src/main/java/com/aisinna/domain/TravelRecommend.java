package com.aisinna.domain;

import com.aisinna.domain.enums.RegionCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String theme;
    @Enumerated(EnumType.STRING)
    private RegionCode regionCode;

    @OneToOne(cascade = CascadeType.ALL) // TravelPlan과 일대일 관계 설정
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "travelRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTravel> userTravels = new ArrayList<>();

    @OneToMany(mappedBy = "travelRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelLike> travelLikes = new ArrayList<>();
}
