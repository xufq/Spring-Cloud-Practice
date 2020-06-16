package com.xufq.practicecore.exception;

public class BadRequestException extends BaseException {

    public BadRequestException(String errCode) {
        super(errCode);
    }

    public BadRequestException(String errCode, String message) {
        super(errCode, message);
    }
}
