package com.xufq.practicecore.exception;

import com.xufq.practicecore.constants.ErrorCode;

public class ForbiddenException extends BaseException {
    public ForbiddenException(ErrorCode errorCode) {
        this(errorCode.name());
    }

    public ForbiddenException(String errCode) {
        super(errCode);
    }

    public ForbiddenException(String errCode, String message) {
        super(errCode, message);
    }
}
