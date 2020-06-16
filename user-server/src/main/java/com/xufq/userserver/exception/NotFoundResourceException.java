package com.xufq.userserver.exception;

import com.xufq.practicecore.exception.NotFoundException;

public class NotFoundResourceException extends NotFoundException {
    public NotFoundResourceException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public NotFoundResourceException(String errCode) {
        super(errCode);
    }

    public NotFoundResourceException(String errCode, String message) {
        super(errCode, message);
    }

}
