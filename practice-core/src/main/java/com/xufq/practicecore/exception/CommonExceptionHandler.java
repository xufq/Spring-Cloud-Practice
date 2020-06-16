package com.xufq.practicecore.exception;

import com.xufq.practicecore.vo.HttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionHandler
 * @Description Exception Handler
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:24 PM
 * @Version 1.0
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpErrorResponse handleSessionExpiredException(UnauthorizedException ex) {
        return convertException(ex);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpErrorResponse handleBusinessException(BadRequestException ex) {
        return convertException(ex);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpErrorResponse handleInternalException(InternalServerErrorException ex) {
        return convertException(ex);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpErrorResponse handleUnexpectedException(Throwable ex) {
        return new HttpErrorResponse();
    }

    private HttpErrorResponse convertException(BaseException ex) {
        return new HttpErrorResponse(ex.getErrCode(), ex.getErrMessage());
    }
}
