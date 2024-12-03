package com.aisinna.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TravelLike extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfo; // 좋아요를 누른 사용자

    @ManyToOne(optional = false)
    @JoinColumn(name = "travel_recommend_id", nullable = false)
    private TravelRecommend travelRecommend;
}