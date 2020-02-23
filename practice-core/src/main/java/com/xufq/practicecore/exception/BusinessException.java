package com.xufq.practicecore.exception;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:32 PM
 * @Version 1.0
 */
public class BusinessException extends BaseException {
    public BusinessException() {
    }

    public BusinessException(String errCode) {
        super(errCode);
    }

    public BusinessException(String message, String errCode) {
        super(message, errCode);
    }
}
