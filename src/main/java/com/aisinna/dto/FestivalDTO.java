package com.aisinna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FestivalDTO {
    @JsonProperty("addr1")
    private String addr1;

    @JsonProperty("addr2")
    private String addr2;

    @JsonProperty("booktour")
    private String booktour;

    @JsonProperty("cat1")
    private String cat1;

    @JsonProperty("cat2")
    private String cat2;

    @JsonProperty("cat3")
    private String cat3;

    @JsonProperty("contentid")
    private String contentId;

    @JsonProperty("contenttypeid")
    private String contentTypeId;

    @JsonProperty("createdtime")
    private String createdTime;

    @JsonProperty("eventstartdate")
    private String eventStartDate;

    @JsonProperty("eventenddate")
    private String eventEndDate;

    @JsonProperty("firstimage")
    private String firstImage;

    @JsonProperty("firstimage2")
    private String firstImage2;

    @JsonProperty("cpyrhtDivCd")
    private String cpyrhtDivCd;

    @JsonProperty("mapx")
    private String mapX;

    @JsonProperty("mapy")
    private String mapY;

    @JsonProperty("mlevel")
    private String mlevel;

    @JsonProperty("modifiedtime")
    private String modifiedTime;

    @JsonProperty("areacode")
    private String areacode;

    @JsonProperty("sigungucode")
    private String sigungucode;

    @JsonProperty("tel")
    private String tel;

    @JsonProperty("title")
    private String title;
}