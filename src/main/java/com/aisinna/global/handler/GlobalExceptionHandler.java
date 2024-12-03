package com.aisinna.global.handler;

import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.BaseException;
import com.aisinna.global.exception.enums.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleBaseException(BaseException ex) {
        ErrorMessage err = ex.getErrorMessage();
        ApiResponseDTO<String> response = ApiResponseDTO.<String>builder()
                .header(ApiResponseDTO.HeaderDTO.builder()
                        .resultCode(err.getResultCode())
                        .resultMsg(err.getResultMsg())
                        .build())
                .body(null)
                .build();
        return ResponseEntity.status(err.getStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<String>> handleGeneralException(Exception ex) {
        ApiResponseDTO<String> response = ApiResponseDTO.<String>builder()
                .header(ApiResponseDTO.HeaderDTO.builder()
                        .resultCode("5000")
                        .resultMsg("Internal Server Error")
                        .build())
                .body(ex.getMessage())
                .build();
        return ResponseEntity.status(500).body(response);
    }
}