package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FestivalResponseDTO {
        @Schema(name = "콘텐츠 ID", example = "2785797")
        private String contentId;
        @Schema(name = "축제 제목", example = "겨울, 청계천의 빛")
        private String title;
        @Schema(name = "주소", example = "서울특별시 중구 태평로1가 1")
        private String addr1;
        @Schema(name = "이미지1", example = "http://tong.visitkorea.or.kr/cms/resource/32/3045532_image2_1.jpg")
        private String firstImage;
        @Schema(name = "이미지2", example = "http://tong.visitkorea.or.kr/cms/resource/32/3045532_image3_1.jpg")
        private String firstImage2;
        @Schema(name = "축제 시작 날짜", example = "20241213")
        private String eventStartDate;
        @Schema(name = "축제 종료 날짜", example = "20241231")
        private String eventEndDate;
    }

    public static FestivalResponseDTO toResponse(FestivalDTO festivalDTO) {
        return FestivalResponseDTO.builder()
                .contentId(festivalDTO.contentId)
                .title(festivalDTO.title)
                .addr1(festivalDTO.addr1)
                .firstImage(festivalDTO.firstImage)
                .firstImage2(festivalDTO.firstImage2)
                .eventStartDate(festivalDTO.eventStartDate)
                .eventEndDate(festivalDTO.eventEndDate)
                .build();
    }
}