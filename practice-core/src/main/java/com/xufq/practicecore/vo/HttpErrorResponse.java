package com.xufq.practicecore.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HttpErrorResponse
 * @Description Error Response
 * @Author fangqiang.xu
 * @Date 2/27/2020 04:52 PM
 * @Version 1.0
 */
@Data
public class HttpErrorResponse {

    private List<Error> errors;
    private LocalDateTime timeStamps;

    public HttpErrorResponse() {
        this.timeStamps = LocalDateTime.now();
    }

    public HttpErrorResponse(String errCode, String errMessage) {
        this.errors = Collections.singletonList(new Error(errCode, errMessage));
        this.timeStamps = LocalDateTime.now();
    }

    public HttpErrorResponse(List<Error> errors) {
        this.errors = errors;
        this.timeStamps = LocalDateTime.now();
    }

    @Data
    private class Error{
        private String errCode;
        private String errMessage;

        Error(String errCode, String errMessage) {
            this.errCode = errCode;
            this.errMessage = errMessage;
        }
    }
}
