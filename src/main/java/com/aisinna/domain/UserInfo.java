package com.aisinna.domain;

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
public class UserInfo extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL) // UserTravelPreference와 일대일 관계
    @JoinColumn(name = "user_travel_preference_id", nullable = false)
    private UserTravelPreference userTravelPreference;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true) // UserTravel과 양방향 관계 설정
    private List<UserTravel> userTravelList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelReview> travelReviews = new ArrayList<>();
}
