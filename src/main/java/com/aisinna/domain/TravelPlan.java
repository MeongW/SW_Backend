package com.aisinna.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TravelPlan extends BaseEntity {

    private String title;

    private String shareID;

    @ManyToMany
    private List<TravelSpot> travelSpotList = new ArrayList<>();

    @OneToOne(mappedBy = "travelPlan")
    private TravelRecommend travelRecommend;

    @PrePersist
    private void generateShareID() {
        if (this.shareID == null) {
            this.shareID = UUID.randomUUID().toString();
        }
    }
}
