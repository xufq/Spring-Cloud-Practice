package com.xufq.practicecore.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginFeignClientConfiguration {

    @Value("${com.xufq.login.basic.user-name}")
    private String userName;
    @Value("${com.xufq.login.basic.password}")
    private String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(userName, password);
    }
}
