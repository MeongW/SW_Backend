package com.aisinna.global.exception.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    AUTHENTICATION_REQUIRED(401, "COMMON4001", "로그인이 필요합니다."),
    INVALID_INPUT(400, "COMMON4000", "잘못된 입력입니다."),
    ACCESS_DENIED(403, "COMMON4003", "접근이 거부되었습니다.")

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