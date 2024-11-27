package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserTravel extends BaseEntity {

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(mappedBy = "userTravelList") // TravelRecommend와 양방향 관계 설정
    private List<TravelRecommend> travelRecommendList = new ArrayList<>();

    @ManyToOne(optional = false) // UserInfo와 다대일 관계 설정
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "userTravel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelReview> travelReviews = new ArrayList<>();
}
