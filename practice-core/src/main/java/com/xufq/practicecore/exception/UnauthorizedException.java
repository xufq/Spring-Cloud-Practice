package com.xufq.practicecore.exception;

public class UnauthorizedException extends BaseException {
    protected UnauthorizedException(String errCode) {
        super(errCode);
    }

    protected UnauthorizedException(String errCode, String message) {
        super(errCode, message);
    }
}
