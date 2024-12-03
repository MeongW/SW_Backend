package com.aisinna.global.exception;

import com.aisinna.global.exception.enums.ErrorMessage;

public class TravelExceptionHandler extends BaseException {

    public TravelExceptionHandler(ErrorMessage message) {
        super(message);
    }
}
