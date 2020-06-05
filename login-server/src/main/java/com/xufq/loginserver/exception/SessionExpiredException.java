package com.xufq.loginserver.exception;

/**
 * @ClassName SessionExpiredException
 * @Description Session Expired Exception. If to throw this exception, the http status of response is 401.
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:04 PM
 * @Version 1.0
 */
public class SessionExpiredException extends BaseException {

    public SessionExpiredException() {
        super();
    }

    public SessionExpiredException(String errCode) {
        super(errCode);
    }

    public SessionExpiredException(String errCode, String message) {
        super(errCode, message);
    }
}
