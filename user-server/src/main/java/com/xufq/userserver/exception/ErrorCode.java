package com.xufq.userserver.exception;

public enum ErrorCode {
    ROLE_NOT_FOUND("ROLE_NOT_FOUND",""),
    USER_NOT_FOUND("USER_NOT_FOUND",""),
    USER_SAVE_ERROR("USER_SAVE_ERROR",""),
    USER_ROLE_SAVE_ERROR("USER_ROLE_SAVE_ERROR",""),
    USER_UPDATE_ERROR("USER_UPDATE_ERROR",""),
    USER_DELETE_ERROR("USER_DELETE_ERROR",""),
    USER_ROLE_UPDATE_ERROR("USER_ROLE_UPDATE_ERROR",""),
    CONFIRM_PASSWORD_ERROR("CONFIRM_PASSWORD_ERROR",""),
    USERID_PASSWORD_ERROR("USERID_PASSWORD_ERROR",""),
    PASSWORD_UPDATE_ERROR("PASSWORD_UPDATE_ERROR",""),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
