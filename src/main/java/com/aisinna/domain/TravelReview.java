package com.aisinna.domain;

import com.aisinna.domain.enums.Emotion;
import jakarta.persistence.*;

@Entity
@Table(name="travel_review")
public class TravelReview extends BaseEntity {

    @Column(nullable=false, length=20)
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @Column(nullable=false)
    private Float rating;

    @Column(name="review_text",nullable=true, length=100)
    private String reviewText;


}
