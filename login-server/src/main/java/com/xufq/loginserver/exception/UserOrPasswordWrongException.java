package com.xufq.loginserver.exception;


public class UserOrPasswordWrongException extends BaseException {

    public UserOrPasswordWrongException() {
    }

    public UserOrPasswordWrongException(String errCode) {
        super(errCode);
    }

    public UserOrPasswordWrongException(String errCode, String message) {
        super(errCode, message);
    }
}
