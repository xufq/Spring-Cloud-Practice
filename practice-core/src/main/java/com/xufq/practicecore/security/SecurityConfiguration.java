package com.xufq.practicecore.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @ClassName SecurityConfiguration
 * @Description security configuration
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

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    public SecurityConfiguration(TokenAuthenticationFilter tokenAuthenticationFilter,
                                 TokenAuthenticationProvider tokenAuthenticationProvider) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
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
        auth.authenticationProvider(tokenAuthenticationProvider)
                .inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser(userName).password(passwordEncoder.encode(password)).roles("ADMIN");
    }

}
