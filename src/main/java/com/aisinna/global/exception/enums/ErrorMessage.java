package com.aisinna.global.exception.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    AUTHENTICATION_REQUIRED(401, "COMMON4010", "로그인이 필요합니다."),
    INVALID_INPUT(400, "COMMON4000", "잘못된 입력입니다."),
    ACCESS_DENIED(403, "COMMON4030", "접근이 거부되었습니다."),

    // TravelPreference
    DUPLICATION_PREFERENCE_PRIORITY(400, "PREFERENCE4000", "선호 여행 답변의 우선순위가 중복되었습니다."),
    DUPLICATION_PREFERENCE_VALUE(400, "PREFERENCE4001", "선호 여행 답변의 값이 중복되었습니다."),
    PREFERENCE_NOT_FOUND(404, "PREFERENCE4041", "사용자의 선호 여행 답변이 존재하지 않습니다."),

    // FastAPI
    FASTAPI_COMMUNICATION_FAILED(500, "FASTAPI5000", "FastAPI 서버와 통신에 실패했습니다.");

    ;


    private final int statusCode;
    private final String resultCode;
    private final String resultMsg;

    ErrorMessage(int statusCode, String customStatusCode, String message) {
        this.statusCode = statusCode;
        this.resultCode = customStatusCode;
        this.resultMsg = message;
    }
}