package com.xufq.practicecore.security;

import com.xufq.practicecore.client.LoginFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @ClassName SecurityConfiguration
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/9/2020 01:37 PM
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${com.xufq.security.basic.user-name}")
    private String userName;

    @Value("${com.xufq.security.basic.password}")
    private String password;

    private final LoginFeignClient loginFeignClient;

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public SecurityConfiguration(LoginFeignClient loginFeignClient, TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.loginFeignClient = loginFeignClient;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        tokenAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        http
                .addFilterAfter(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.authenticationProvider(new TokenAuthenticationProvider(loginFeignClient))
                .inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser(userName).password(passwordEncoder.encode(password)).roles("ADMIN");
    }

}
