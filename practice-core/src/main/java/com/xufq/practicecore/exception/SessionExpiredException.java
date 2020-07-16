package com.xufq.practicecore.exception;

import com.xufq.practicecore.constants.ErrorCode;

/**
 * @ClassName SessionExpiredException
 * @Description Session Expired Exception. If to throw this exception, the http status of response is 401.
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:04 PM
 * @Version 1.0
 */
public class SessionExpiredException extends UnauthorizedException {

    public SessionExpiredException(ErrorCode errorCode){ super(errorCode.name());}

    public SessionExpiredException(String errCode) {
        super(errCode);
    }

    public SessionExpiredException(String errCode, String message) {
        super(errCode, message);
    }
}
