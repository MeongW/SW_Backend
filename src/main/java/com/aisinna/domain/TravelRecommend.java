package com.aisinna.domain;

import com.aisinna.domain.enums.RegionCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "travel_plan_id")
    private TravelPlan travelPlan;
}
