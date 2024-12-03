package com.aisinna.global.dto;

import com.aisinna.global.exception.enums.ErrorMessage;
import com.aisinna.global.exception.enums.SuccessMessage;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {

    // 성공 응답
    public static <T> ResponseEntity<ApiResponseDTO<T>> success(SuccessMessage successMessage, T data) {
        ApiResponseDTO<T> response = ApiResponseDTO.<T>builder()
                .header(ApiResponseDTO.HeaderDTO.builder()
                        .resultCode(successMessage.getResultCode())
                        .resultMsg(successMessage.getResultMsg())
                        .build())
                .body(data)
                .build();

        return ResponseEntity.status(successMessage.getStatusCode()).body(response);
    }

    // 성공 응답 (data 없음)
    public static <T> ResponseEntity<ApiResponseDTO<T>> success(SuccessMessage successMessage) {
        return success(successMessage, null);
    }

    // 에러 응답
    public static <T> ResponseEntity<ApiResponseDTO<T>> error(ErrorMessage errorMessage) {
        ApiResponseDTO<T> body = ApiResponseDTO.<T>builder()
                .header(ApiResponseDTO.HeaderDTO.builder()
                        .resultCode(errorMessage.getResultCode())
                        .resultMsg(errorMessage.getResultMsg())
                        .build())
                .body(null)
                .build();

        return ResponseEntity.status(errorMessage.getStatusCode()).body(body);
    }
}