package com.aisinna.global.exception.enums;

import lombok.Getter;

@Getter
public enum SuccessMessage {
    RESOURCE_FETCHED(200, "COMMON2000", "리소스가 성공적으로 조회되었습니다."),
    RESOURCE_CREATED(201, "COMMON2001", "리소스가 성공적으로 생성되었습니다."),
    REQUEST_ACCEPTED(202, "COMMON2002", "요청이 성공적으로 접수되었습니다."),
    RESOURCE_DELETED(204, "COMMON2004", "리소스가 성공적으로 삭제되었습니다.");

    private final int statusCode;
    private final String resultCode;
    private final String resultMsg;

    SuccessMessage(int statusCode, String customStatusCode, String message) {
        this.statusCode = statusCode;
        this.resultCode = customStatusCode;
        this.resultMsg = message;
    }
}

