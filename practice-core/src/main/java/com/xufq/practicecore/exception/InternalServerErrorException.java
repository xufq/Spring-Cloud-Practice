package com.xufq.practicecore.exception;

/**
 * @ClassName InternalServerErrorException
 * @Description Internal Exception. If to throw this exception, http status of response is 500.
 * @Author fangqiang.xu
 * @Date 2/24/2020 03:46 PM
 * @Version 1.0
 */
public class InternalServerErrorException extends BaseException {

    public InternalServerErrorException(String errCode) {
        super(errCode);
    }

    public InternalServerErrorException(String errCode, String message) {
        super(errCode, message);
    }
}
