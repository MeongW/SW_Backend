package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FestivalDetailIntroDTO {
    @JsonProperty("contentid")
    private String contentId;

    @JsonProperty("contenttypeid")
    private String contentTypeId;

    @JsonProperty("sponsor1")
    private String sponsor1;

    @JsonProperty("sponsor1tel")
    private String sponsor1Tel;

    @JsonProperty("sponsor2")
    private String sponsor2;

    @JsonProperty("sponsor2tel")
    private String sponsor2Tel;

    @JsonProperty("eventenddate")
    private String eventEndDate;

    @JsonProperty("playtime")
    private String playTime;

    @JsonProperty("eventplace")
    private String eventPlace;

    @JsonProperty("eventhomepage")
    private String eventHomepage;

    @JsonProperty("agelimit")
    private String ageLimit;

    @JsonProperty("bookingplace")
    private String bookingPlace;

    @JsonProperty("placeinfo")
    private String placeInfo;

    @JsonProperty("subevent")
    private String subEvent;

    @JsonProperty("program")
    private String program;

    @JsonProperty("eventstartdate")
    private String eventStartDate;

    @JsonProperty("usetimefestival")
    private String usetimeFestival;

    @JsonProperty("discountinfofestival")
    private String discountInfoFestival;

    @JsonProperty("spendtimefestival")
    private String spendTimeFestival;

    @JsonProperty("festivalgrade")
    private String festivalGrade;
}