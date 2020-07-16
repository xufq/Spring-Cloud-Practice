package com.xufq.userserver.exception;

import com.xufq.practicecore.exception.ForbiddenException;

public class NotFoundTempResourceException extends ForbiddenException {
    public NotFoundTempResourceException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public NotFoundTempResourceException(String errCode) {
        super(errCode);
    }

    public NotFoundTempResourceException(String errCode, String message) {
        super(errCode, message);
    }
}
