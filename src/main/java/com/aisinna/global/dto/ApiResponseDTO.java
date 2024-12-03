package com.aisinna.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {

    private HeaderDTO header;
    private T body;

    @Getter
    @Builder
    public static class HeaderDTO {
        private String resultCode;
        private String resultMsg;
    }
}