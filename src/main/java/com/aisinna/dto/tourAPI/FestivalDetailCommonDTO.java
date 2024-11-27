package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FestivalDetailCommonDTO {

    @JsonProperty("contentid")
    private String contentId;        // 콘텐츠 ID

    @JsonProperty("contenttypeid")
    private String contentTypeId;    // 콘텐츠 유형 ID

    @JsonProperty("title")
    private String title;            // 제목

    @JsonProperty("createdtime")
    private String createdTime;      // 생성 시간

    @JsonProperty("modifiedtime")
    private String modifiedTime;     // 수정 시간

    @JsonProperty("tel")
    private String tel;              // 전화번호

    @JsonProperty("telname")
    private String telName;          // 전화번호 소유자 이름

    @JsonProperty("homepage")
    private String homepage;         // 홈페이지 URL

    @JsonProperty("booktour")
    private String bookTour;         // 예약 가능 여부

    @JsonProperty("firstimage")
    private String firstImage;       // 첫 번째 이미지 URL

    @JsonProperty("firstimage2")
    private String firstImage2;      // 두 번째 이미지 URL

    @JsonProperty("cpyrhtDivCd")
    private String cpyrhtDivCd;      // 저작권 구분 코드

    @JsonProperty("areacode")
    private String areaCode;         // 지역 코드

    @JsonProperty("sigungucode")
    private String sigunguCode;      // 시군구 코드

    @JsonProperty("cat1")
    private String cat1;             // 대분류 카테고리

    @JsonProperty("cat2")
    private String cat2;             // 중분류 카테고리

    @JsonProperty("cat3")
    private String cat3;             // 소분류 카테고리

    @JsonProperty("addr1")
    private String addr1;            // 주소 1

    @JsonProperty("addr2")
    private String addr2;            // 주소 2

    @JsonProperty("zipcode")
    private String zipcode;          // 우편번호

    @JsonProperty("mapx")
    private String mapX;             // 지도 X 좌표

    @JsonProperty("mapy")
    private String mapY;             // 지도 Y 좌표

    @JsonProperty("mlevel")
    private String mlevel;           // 지도 레벨

    @JsonProperty("overview")
    private String overview;         // 개요 설명
}
