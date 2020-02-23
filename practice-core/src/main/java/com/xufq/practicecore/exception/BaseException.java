package com.xufq.practicecore.exception;

/**
 * @ClassName BaseException
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:04 PM
 * @Version 1.0
 */
public class BaseException extends RuntimeException {
    private String errCode;

    public BaseException() {
        super();
    }

    public BaseException(String errCode) {
        this.errCode = errCode;
    }

    public BaseException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public BaseException(String message, Throwable cause, String errCode) {
        super(message, cause);
        this.errCode = errCode;
    }

    public BaseException(Throwable cause, String errCode) {
        super(cause);
        this.errCode = errCode;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }
}
