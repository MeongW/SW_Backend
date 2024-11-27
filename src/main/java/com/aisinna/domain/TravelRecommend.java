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

    @ManyToMany
    @JoinTable(
            name = "user_travel_recommend",
            joinColumns = @JoinColumn(name = "travel_recommend_id"),
            inverseJoinColumns = @JoinColumn(name = "user_travel_id")
    ) // UserTravel과 다대다 관계 정의
    private List<UserTravel> userTravelList = new ArrayList<>();
}
