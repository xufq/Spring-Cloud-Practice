package com.xufq.practicecore.exception;

/**
 * @ClassName InternalException
 * @Description Internal Exception. If to throw this exception, http status of response is 500.
 * @Author fangqiang.xu
 * @Date 2/24/2020 03:46 PM
 * @Version 1.0
 */
public class InternalException extends BaseException {
    public InternalException() {
    }

    public InternalException(String errCode) {
        super(errCode);
    }

    public InternalException(String errCode, String message) {
        super(errCode, message);
    }
}
