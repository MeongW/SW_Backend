package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserTravel extends BaseEntity {

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "userTravel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelReview> travelReviews;
}
