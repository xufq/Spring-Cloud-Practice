package com.xufq.practicecore.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName ExceptionHandler
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:24 PM
 * @Version 1.0
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(SessionExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public HttpEntity<BaseException> handleSessionExpiredException(SessionExpiredException ex){
        return new HttpEntity<>(ex);
    }
}
