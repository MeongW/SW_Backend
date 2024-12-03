package com.aisinna.global.exception;

import com.aisinna.global.exception.enums.ErrorMessage;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.getResultMsg());
        this.errorMessage = errorMessage;
    }
}