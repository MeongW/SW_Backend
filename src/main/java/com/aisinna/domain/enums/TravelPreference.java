package com.aisinna.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TravelPreference {

    // 여행 목적
    @Getter
    @AllArgsConstructor
    public enum Purpose {
        NATURE_EXPLORATION("자연탐방"),
        BUSINESS_TRIP("출장"),
        VACATION("휴양"),
        SHOPPING("쇼핑"),
        RELAXATION("휴식"),
        CULTURE_EXPLORATION("문화탐방"),
        FOOD_TRIP("음식 여행"),
        ADVENTURE("모험");

        private final String value;
    }

    // 여행지 유형
    @Getter
    @AllArgsConstructor
    public enum Destination {
        BEACH("해변"),
        CITY("도시"),
        SMALL_TOWN("작은 마을"),
        ISLAND("섬");

        private final String value;
    }

    // 선호 활동
    @Getter
    @AllArgsConstructor
    public enum Activity {
        FOOD_TOUR("맛집 탐방"),
        HIKING("하이킹"),
        SHOPPING("쇼핑"),
        ART_GALLERIES("미술관"),
        WATER_SPORTS("수상 스포츠"),
        LEISURE_SPORTS("레저 스포츠"),
        READING("독서"),
        MUSEUMS("박물관");

        private final String value;
    }

    // 비용
    @Getter
    @AllArgsConstructor
    public enum Budget {
        LOW_BUDGET("저예산"),
        MEDIUM_BUDGET("중간 예산"),
        HIGH_BUDGET("고예산");

        private final String value;
    }

    // 여행 기간
    @Getter
    @AllArgsConstructor
    public enum Duration {
        WEEKEND("주말"),
        UNDER_WEEK("3~5일"),
        OVER_WEEK("1주 이상");

        private final String value;
    }
}
