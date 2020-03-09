package com.xufq.practicecore.security;

import com.xufq.practicecore.exception.SessionExpiredException;
import com.xufq.practicecore.utils.ObjectMapperUtil;
import com.xufq.practicecore.utils.RequestUtil;
import com.xufq.practicecore.vo.HttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName SessionAuthenticationFilter
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/7/2020 02:30 PM
 * @Version 1.0
 */
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private SessionAuthenticationProvider provider;

    public SessionAuthenticationFilter(SessionAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            HttpSession session = RequestUtil.getRequest().getSession();
            if (Objects.isNull(session)) {
                throw new SessionExpiredException();
            }
            SessionAuthentication authentication = new SessionAuthentication(session.getId());
            SecurityContextHolder.getContext().setAuthentication(provider.authenticate(authentication));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (SessionExpiredException ex) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            ObjectMapperUtil.getMapper().writeValue(httpServletResponse.getWriter(), new HttpErrorResponse());
        }
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        try {
//            HttpSession session = RequestUtil.getRequest().getSession();
//            if (Objects.isNull(session)) {
//                throw new SessionExpiredException();
//            }
//
//
//            SessionAuthentication authentication = new SessionAuthentication(session.getId());
//            return provider.authenticate(authentication);
//        } catch (SessionExpiredException ex) {
//            return null;
//        }
//    }
}
