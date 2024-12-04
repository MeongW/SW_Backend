package com.aisinna.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(example = "COMMON2000")
        private String resultCode;
        @Schema(example = "리소스가 성공적으로 조회되었습니다.")
        private String resultMsg;
    }
}