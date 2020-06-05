package com.xufq.practicecore.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenValidationException extends AuthenticationException {

    public TokenValidationException(String msg) {
        super(msg);
    }

}
