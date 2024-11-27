package com.aisinna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FestivalDTO {
    @JsonProperty("addr1")
    private String addr1;

    @JsonProperty("addr2")
    private String addr2;

    @JsonProperty("contentid")
    private String contentId;

    @JsonProperty("contenttypeid")
    private String contentTypeId;

    @JsonProperty("eventstartdate")
    private String eventStartDate;

    @JsonProperty("eventenddate")
    private String eventEndDate;

    @JsonProperty("firstimage")
    private String firstImage;

    @JsonProperty("firstimage2")
    private String firstImage2;

    @JsonProperty("mapx")
    private String mapX;

    @JsonProperty("mapy")
    private String mapY;

    @JsonProperty("tel")
    private String tel;

    @JsonProperty("title")
    private String title;
}