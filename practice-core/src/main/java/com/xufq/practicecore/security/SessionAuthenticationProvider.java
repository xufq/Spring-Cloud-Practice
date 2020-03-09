package com.xufq.practicecore.security;

import com.xufq.practicecore.exception.SessionExpiredException;
import com.xufq.practicecore.utils.RequestUtil;
import com.xufq.practicecore.vo.UserVo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName SessionAuthenticationProvider
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/3/2020 04:23 PM
 * @Version 1.0
 */
@Component
public class SessionAuthenticationProvider implements AuthenticationProvider {
    @Override
    public SessionAuthentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpSession session = RequestUtil.getRequest().getSession();
        if(Objects.isNull(session)){
            throw new SessionExpiredException();
        }
        UserVo userVo = (UserVo)Optional.ofNullable(session.getAttribute(SessionConstants.SESSION_USER_KEY)).orElseThrow(SessionExpiredException::new);
        if(userVo.getExpiredDate().isBefore(LocalDateTime.now())){
            throw new SessionExpiredException();
        }
        return new SessionAuthentication(userVo);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SessionAuthentication.class.equals(aClass);
    }
}