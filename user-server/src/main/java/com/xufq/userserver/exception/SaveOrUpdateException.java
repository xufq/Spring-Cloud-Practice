package com.xufq.userserver.exception;

import com.xufq.practicecore.exception.InternalServerErrorException;

public class SaveOrUpdateException extends InternalServerErrorException {
    public SaveOrUpdateException(ErrorCode errCode) {
        this(errCode.getCode(), errCode.getMessage());
    }

    public SaveOrUpdateException(String errCode) {
        super(errCode);
    }

    public SaveOrUpdateException(String errCode, String message) {
        super(errCode, message);
    }
}
