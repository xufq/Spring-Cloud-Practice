package com.xufq.practicecore.exception;

public class NotFoundException extends BaseException {

    public NotFoundException(String errCode) {
        super(errCode);
    }

    public NotFoundException(String errCode, String message) {
        super(errCode, message);
    }
}
