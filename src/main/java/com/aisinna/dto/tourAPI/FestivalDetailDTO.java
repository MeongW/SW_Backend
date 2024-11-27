package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FestivalDetailDTO {

    private List<CommonDTO> commons;
    private List<ImageDTO> images;
    private List<InfoDTO> infos;
    private List<IntroDTO> intros;

    @Data
    public static class CommonDTO {

        private String contentId;        // 콘텐츠 ID
        private String contentTypeId;    // 콘텐츠 유형 ID
        private String title;            // 제목
        private String createdTime;      // 생성 시간
        private String modifiedTime;     // 수정 시간
        private String tel;              // 전화번호
        private String telName;          // 전화번호 소유자 이름
        private String homepage;         // 홈페이지 URL
        private String bookTour;         // 예약 가능 여부
        private String firstImage;       // 첫 번째 이미지 URL
        private String firstImage2;      // 두 번째 이미지 URL
        private String cpyrhtDivCd;      // 저작권 구분 코드
        private String areaCode;         // 지역 코드
        private String sigunguCode;      // 시군구 코드
        private String cat1;             // 대분류 카테고리
        private String cat2;             // 중분류 카테고리
        private String cat3;             // 소분류 카테고리
        private String addr1;            // 주소 1
        private String addr2;            // 주소 2
        private String zipcode;          // 우편번호
        private String mapX;             // 지도 X 좌표
        private String mapY;             // 지도 Y 좌표
        private String mlevel;           // 지도 레벨
        private String overview;         // 개요 설명
    }

    @Data
    public static class ImageDTO {
        private String contentId;
        private String originImgUrl;
        private String imgName;
        private String smallImageUrl;
        private String cpyrhtDivCd;
        private String serialNum;
    }

    @Data
    public static class InfoDTO {
        private String contentId;
        private String contentTypeId;
        private String serialNum;
        private String infoName;
        private String infoText;
        private String fldGubun;
    }

    @Data
    public static class IntroDTO {
        private String contentId;
        private String contentTypeId;
        private String sponsor1;
        private String sponsor1Tel;
        private String sponsor2;
        private String sponsor2Tel;
        private String eventEndDate;
        private String playTime;
        private String eventPlace;
        private String eventHomepage;
        private String ageLimit;
        private String bookingPlace;
        private String placeInfo;
        private String subEvent;
        private String program;
        private String eventStartDate;
        private String usetimeFestival;
        private String discountInfoFestival;
        private String spendTimeFestival;
        private String festivalGrade;
    }
}
