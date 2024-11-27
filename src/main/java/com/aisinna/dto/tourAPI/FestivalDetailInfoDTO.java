package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FestivalDetailInfoDTO {
    @JsonProperty("contentid")
    private String contentId;

    @JsonProperty("contenttypeid")
    private String contentTypeId;

    @JsonProperty("serialnum")
    private String serialNum;

    @JsonProperty("infoname")
    private String infoName;

    @JsonProperty("infotext")
    private String infoText;

    @JsonProperty("fidgubun")
    private String fldGubun;
}