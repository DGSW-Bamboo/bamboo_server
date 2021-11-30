package com.bamboo.api.global.exception.errors;

import com.bamboo.api.global.exception.errors.codes.ErrorCodes;

public class CustomError extends RuntimeException {

    private final ErrorCodes errorCodes;

    public CustomError(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCodes = errorCode;
    }

    public CustomError(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCodes = errorCode;
    }

    public ErrorCodes getErrorCode() {
        return errorCodes;
    }
}
