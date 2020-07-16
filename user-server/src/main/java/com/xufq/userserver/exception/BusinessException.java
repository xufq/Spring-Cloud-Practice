package com.xufq.userserver.exception;

import com.xufq.practicecore.exception.NotFoundException;

public class BusinessException extends NotFoundException {
    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public BusinessException(String errCode) {
        super(errCode);
    }

    public BusinessException(String errCode, String message) {
        super(errCode, message);
    }

}
