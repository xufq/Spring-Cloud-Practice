package com.xufq.practicecore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .authenticated()
//                .anyRequest()
//                .hasRole("HTTP_BASIC")
//                .and()
                .httpBasic()
//                .disable()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .disable()
                .logout()
//                .permitAll();
                .disable()
                .csrf()
                .disable();
        super.configure(http);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                //admin
//                .withUser("admin").password("123456").roles("EUREKA-CLIENT")
//                //eureka-security-client
//                .and()
//                .withUser("root").password("root").roles("EUREKA-CLIENT")
//        ;
//    }
}
