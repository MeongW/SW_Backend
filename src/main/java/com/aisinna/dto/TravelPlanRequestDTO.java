package com.aisinna.dto;

import lombok.Data;

@Data
public class TravelPlanRequestDTO {
    private String title;
    private String location;
    private String image;
    private String duration;
    private String description;
    private int people; // 요청에서 받은 값
    private int cost;   // 요청에서 받은 값
}
