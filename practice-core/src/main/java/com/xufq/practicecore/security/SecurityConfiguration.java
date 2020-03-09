package com.xufq.practicecore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@ConditionalOnProperty("spring.security.user.name")
@DependsOn(value = {"securityProperties"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SessionAuthenticationFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/captcha").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .csrf()
                .disable()
                .addFilterAfter(filter, BasicAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDetailsManagerConfigurer.UserDetailsBuilder builder = auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser(securityProperties.getName()).password(passwordEncoder.encode(securityProperties.getPassword()));
        securityProperties.getRoles().stream().map(role -> builder.roles(role)).findAny().orElse(builder.roles("HTTP_BASIC"));
    }
}
