package com.xufq.zuulserver.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RouteRuleConfiguration
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 9/17/2019 09:37 PM
 * @Version 1.0
 */
@Configuration
public class RouteRuleConfiguration {

//    @Bean
//    public PatternServiceRouteMapper userServerMapper(){
//        return new PatternServiceRouteMapper("user-server","user");
//    }


    @Bean
    public PatternServiceRouteMapper productServerMapper(){
        return new PatternServiceRouteMapper("product-server","product");
    }
}
