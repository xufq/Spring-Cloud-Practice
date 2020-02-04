package com.xufq.userserver.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RequestUtil
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/12/2019 10:06 PM
 * @Version 1.0
 */
@UtilityClass
public class RequestUtil {
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
