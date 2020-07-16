package com.xufq.practicecore.exception;

import com.sun.javafx.binding.StringFormatter;
import com.xufq.practicecore.constants.ErrorCode;
import com.xufq.practicecore.vo.HttpErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @ClassName ExceptionHandler
 * @Description Exception Handler
 * @Author fangqiang.xu
 * @Date 2/7/2020 09:24 PM
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpErrorResponse handleBadRequestException(BadRequestException ex) {
        return convertException(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return convertException(ex);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpErrorResponse handleUnauthorizedException(UnauthorizedException ex) {
        return convertException(ex);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HttpErrorResponse handleForbiddenException(ForbiddenException ex) {
        return convertException(ex);
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HttpErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        BaseException exception = new BaseException(ErrorCode.ACCESS_DENIED.name());
        return convertException(exception);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleNotFoundException(NotFoundException ex) {
        return convertException(ex);
    }


    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpErrorResponse handleInternalException(InternalServerErrorException ex) {
        log.error("===There is an error:", ex);
        return convertException(ex);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpErrorResponse handleUnexpectedException(Throwable ex) {
        log.error("===There is an error:", ex);
        return new HttpErrorResponse();
    }

    private HttpErrorResponse convertException(BaseException ex) {
        log.debug("===There is an exception:", ex);
        return new HttpErrorResponse(ex.getErrCode(), ex.getErrMessage());
    }

    private HttpErrorResponse convertException(MethodArgumentNotValidException ex) {
        HttpErrorResponse response = new HttpErrorResponse();
        if (Objects.nonNull(ex.getBindingResult()) && !CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())) {
            String message = "%s%s";
            ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                    response.addError(ErrorCode.VALIDATE_ERROR.name(), StringFormatter.format(message, fieldError.getField(), fieldError.getDefaultMessage()).getValue())
            );
        }
        return response;

    }
}
