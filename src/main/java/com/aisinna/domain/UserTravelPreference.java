package com.aisinna.domain;

import com.aisinna.domain.enums.TravelPreference;
import com.aisinna.oauth2.domain.SocialUser;
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
public class UserTravelPreference extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private TravelPreference.Purpose purpose;

    @Enumerated(EnumType.STRING)
    private TravelPreference.Activity activity;

    @Enumerated(EnumType.STRING)
    private TravelPreference.Budget budget;

    @Enumerated(EnumType.STRING)
    private TravelPreference.Destination destination;

    @Enumerated(EnumType.STRING)
    private TravelPreference.Duration duration;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private SocialUser socialUser;
}
