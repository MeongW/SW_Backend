package com.aisinna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelLikeResponseDTO {

    @Schema(description = "테마 ID", example = "1")
    private Long id;
    @Schema(description = "테마 제목", example = "여수의 해산물을 맛보는 미식 여정")
    private String title;
    @Schema(description = "여행지 지역", example = "전라남도 여수")
    private String location;
    @Schema(description = "이미지 Url", example = "http://tong.visitkorea.or.kr/cms/resource/88/2931088_image2_1.jpg")
    private String image;
    @Schema(description = "여행 기간", example = "2일")
    private String duration;
    @Schema(description = "테마 설명", example = "여수의 신선한 해산물과 이색 맛집을 탐방하세요. 각 식당의 특색 있는 메뉴와 지역 특산물로 미식을 즐길 수 있는 기회를 제공합니다.")
    private String description;
}
