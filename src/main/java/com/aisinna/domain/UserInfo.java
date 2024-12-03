package com.aisinna.domain;

import com.aisinna.oauth2.domain.SocialUser;
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


    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true) // UserTravel과 양방향 관계 설정
    private List<UserTravel> userTravelList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelReview> travelReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTravelPreference> userTravelPreferenceList = new ArrayList<>();

    @OneToOne(mappedBy = "userInfo", optional = false)
    private SocialUser socialUser;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelLike> travelLikeList = new ArrayList<>();

    public void addPreferenceItem(UserTravelPreference userTravelPreference) {
        userTravelPreferenceList.add(userTravelPreference);
        userTravelPreference.setUserInfo(this);
    }
}
