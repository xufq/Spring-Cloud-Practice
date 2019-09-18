package com.xufq.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ProductServerPreFilter
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 9/8/2019 04:14 PM
 * @Version 1.0
 */
@Component
public class ProductServerPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // pre post routing error
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.addHeader("note", "ProductServerPostFilter has been invoked");
        return null;
    }
}
