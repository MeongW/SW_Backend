package com.aisinna.domain;

import com.aisinna.domain.enums.TravelPreference;
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
public class UserTravelPreference extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TravelPreference.TravelPreferenceType preferenceType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TravelPreference.TravelPreferenceValue preferenceValue;

    private int priority; // 우선순위

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
