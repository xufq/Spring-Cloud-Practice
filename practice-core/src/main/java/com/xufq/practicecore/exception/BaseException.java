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

    private String errMessage;

    BaseException() {
        super();
    }

    protected BaseException(String errCode) {
        this.errCode = errCode;
    }

    protected BaseException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.errMessage = message;
    }

    BaseException(String message, Throwable cause, String errCode) {
        super(message, cause);
        this.errCode = errCode;
        this.errMessage = message;
    }

    BaseException(Throwable cause, String errCode) {
        super(cause);
        this.errCode = errCode;
    }

    BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
