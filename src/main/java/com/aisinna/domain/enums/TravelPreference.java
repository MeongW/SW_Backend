package com.aisinna.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TravelPreference {

    @Getter
    @AllArgsConstructor
    public enum TravelPreferenceType implements TravelPreferenceTypeProvider {
        PURPOSE("목적"),
        DESTINATION("목적지"),
        ACTIVITY("활동"),
        BUDGET("예산"),
        DURATION("기간"),
        ;

        private final String value;

    }

    @Getter
    @AllArgsConstructor
    public enum TravelPreferenceValue implements TravelPreferenceValueProvider {

        // 여행 목적
        NATURE_EXPLORATION(TravelPreferenceType.PURPOSE, "자연탐방"),
        BUSINESS_TRIP(TravelPreferenceType.PURPOSE,"출장"),
        VACATION(TravelPreferenceType.PURPOSE,"휴양"),
        SHOPPING(TravelPreferenceType.PURPOSE,"쇼핑"),
        RELAXATION(TravelPreferenceType.PURPOSE,"휴식"),
        CULTURE_EXPLORATION(TravelPreferenceType.PURPOSE,"문화탐방"),
        FOOD_TRIP(TravelPreferenceType.PURPOSE,"음식 여행"),
        ADVENTURE(TravelPreferenceType.PURPOSE,"모험"),

        // 여행지 유형
        BEACH(TravelPreferenceType.DESTINATION, "해변"),
        CITY(TravelPreferenceType.DESTINATION, "도시"),
        SMALL_TOWN(TravelPreferenceType.DESTINATION, "작은 마을"),
        ISLAND(TravelPreferenceType.DESTINATION, "섬"),

        // 선호 활동
        FOOD_TOUR(TravelPreferenceType.ACTIVITY, "맛집 탐방"),
        HIKING(TravelPreferenceType.ACTIVITY, "하이킹"),
        SHOPPING2(TravelPreferenceType.ACTIVITY, "쇼핑"),
        ART_GALLERIES(TravelPreferenceType.ACTIVITY, "미술관"),
        WATER_SPORTS(TravelPreferenceType.ACTIVITY, "수상 스포츠"),
        LEISURE_SPORTS(TravelPreferenceType.ACTIVITY, "레저 스포츠"),
        READING(TravelPreferenceType.ACTIVITY, "독서"),
        MUSEUMS(TravelPreferenceType.ACTIVITY, "박물관"),

        // 예산
        LOW_BUDGET(TravelPreferenceType.BUDGET, "저예산"),
        MEDIUM_BUDGET(TravelPreferenceType.BUDGET, "중간 예산"),
        HIGH_BUDGET(TravelPreferenceType.BUDGET, "고예산"),

        // 여행 기간
        WEEKEND(TravelPreferenceType.DURATION, "주말"),
        UNDER_WEEK(TravelPreferenceType.DURATION, "3~5일"),
        OVER_WEEK(TravelPreferenceType.DURATION, "1주 이상");

        private final TravelPreferenceType type;
        private final String value;
    }
}
