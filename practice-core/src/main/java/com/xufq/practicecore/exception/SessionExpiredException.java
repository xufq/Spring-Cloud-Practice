package com.xufq.practicecore.exception;

/**
 * @ClassName SessionExpiredException
 * @Description TODO
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

    public SessionExpiredException(String message, String errCode) {
        super(message, errCode);
    }
}
