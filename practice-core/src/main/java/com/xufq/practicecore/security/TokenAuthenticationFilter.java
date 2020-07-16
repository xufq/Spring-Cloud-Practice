package com.xufq.practicecore.security;

import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.utils.ObjectMapperUtil;
import com.xufq.practicecore.vo.HttpErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName TokenAuthenticationFilter
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/7/2020 02:30 PM
 * @Version 1.0
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("#{'${com.xufq.security.not-filter-urls:\"\"}'.split(',')}")
    private List<String> notFilterUrls;

    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(Constants.TOKEN);
        if (StringUtils.isEmpty(token)) {
            WriteResponseForSessionExpired(response);
            return;
        }
        Authentication authentication = authenticationManager.authenticate(new TokenAuthentication(token));
        if (!authentication.isAuthenticated()) {
            WriteResponseForSessionExpired(response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        boolean flag = false;
        AntPathMatcher pathMatcher = new AntPathMatcher();
        if (CollectionUtils.isEmpty(notFilterUrls)) {

        } else {
            for (int i = 0; i < notFilterUrls.size(); i++) {
                String url = notFilterUrls.get(i);
                if (pathMatcher.match(url, request.getRequestURI())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private void WriteResponseForSessionExpired(HttpServletResponse response) throws IOException {
        HttpErrorResponse errorResponse = new HttpErrorResponse("VALIDATE_FAILED", "Validate token failed!");
        response.setStatus(401);
        ObjectMapperUtil.getMapper().writeValue(response.getWriter(), errorResponse);
    }
}
