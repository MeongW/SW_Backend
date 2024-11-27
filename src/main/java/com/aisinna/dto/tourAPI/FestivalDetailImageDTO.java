package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FestivalDetailImageDTO {

    @JsonProperty("contentid")
    private String contentId;

    @JsonProperty("originimgurl")
    private String originImgUrl;

    @JsonProperty("imgname")
    private String imgName;

    @JsonProperty("smallimageurl")
    private String smallImageUrl;

    @JsonProperty("cpyrhtDivCd")
    private String cpyrhtDivCd;

    @JsonProperty("serialnum")
    private String serialNum;
}
