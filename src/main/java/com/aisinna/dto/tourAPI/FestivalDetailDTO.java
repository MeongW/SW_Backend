package com.aisinna.dto.tourAPI;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FestivalDetailResponseDTO {
        @Schema(description="축제 제목", example = "겨울, 청계천의 빛")
        private String title;
        @Schema(description="축제 장소", example = "서울특별시 중구 청계천 청계광장 일대")
        private String eventPlace;
        @Schema(description="축제 주소", example = "서울특별시 중구 태평로1가 1")
        private String addr1;
        @Schema(description="축제 설명", example = "올해로 10회째를 맞는 ‘2024 겨울, 청계천의 빛’이 오는 12월13일부터 31일까지 서울 청계광장을 중심으로 펼쳐진다. 로마네스크 양식으로 연출한 대형 트리, 반짝이는 크리스마스 하우스와 곰인형 조형물, 트리를 장식한 크리스마스 요정들과 꼬마기차등  ‘자라나는 세대를 위한 꿈·희망·미래’라는 주제와 함께 동화적이고 사랑스러운 분위기를 빛으로 담았다.")
        private String overview;
        @Schema(description="축제 이미지", examples = {
                "http://tong.visitkorea.or.kr/cms/resource/69/3433069_image2_1.jpg",
                "http://tong.visitkorea.or.kr/cms/resource/70/3433070_image2_1.jpg",
                "http://tong.visitkorea.or.kr/cms/resource/71/3433071_image2_1.jpg"
        })
        private List<String> originImgUrl;
        @Schema(description="축제 진행 시간", example = "18:00~22:00")
        private String playTime;
        @Schema(description="축제 비용", example = "무료")
        private String usetimeFestival;
    }

    public static FestivalDetailResponseDTO toResponse(FestivalDetailDTO festivalDetailDTO) {

        CommonDTO common = festivalDetailDTO.getCommons().isEmpty() ? null : festivalDetailDTO.getCommons().get(0);
        IntroDTO intro = festivalDetailDTO.getIntros().isEmpty() ? null : festivalDetailDTO.getIntros().get(0);
        List<ImageDTO> images = festivalDetailDTO.getImages();

        HashSet<String> originImgUrlSet = new HashSet<>();
        originImgUrlSet.add(common.firstImage);
        originImgUrlSet.add(common.firstImage2);

        for (ImageDTO image: images) {

            originImgUrlSet.add(image.originImgUrl);
        }

        List<String> originImgUrlList = new ArrayList<>(originImgUrlSet);


        return FestivalDetailResponseDTO.builder()
                .title(common.title)    // common
                .eventPlace(intro.eventPlace)    // intro
                .addr1(common.addr1)    // common
                .overview(common.overview) // common
                .originImgUrl(originImgUrlList) // image
                .playTime(intro.playTime) // intro
                .usetimeFestival(intro.usetimeFestival) // intro
                .build();
    }
}
