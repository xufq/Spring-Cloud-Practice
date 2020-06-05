package com.xufq.practicecore.security;

import com.xufq.practicecore.client.LoginFeignClient;
import com.xufq.practicecore.vo.UserVo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName TokenAuthenticationProvider
 * @Description token验证器
 * @Author fangqiang.xu
 * @Date 3/3/2020 04:23 PM
 * @Version 1.0
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final LoginFeignClient loginFeignClient;

    public TokenAuthenticationProvider(LoginFeignClient loginFeignClient) {
        this.loginFeignClient = loginFeignClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        UserVo userVo = loginFeignClient.validate(token);
        if (Objects.isNull(userVo) || expired(userVo)) {
            return authentication;
        } else {
            TokenAuthentication tokenAuthentication = new TokenAuthentication(userVo);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }

    private boolean expired(UserVo userVo) {
        return Objects.isNull(userVo) ? true : userVo.getExpiredDate().isBefore(LocalDateTime.now());
    }
}