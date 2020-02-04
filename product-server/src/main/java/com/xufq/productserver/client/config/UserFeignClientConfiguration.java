package com.xufq.productserver.client.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @ClassName UserFeignClientConfiguration
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/5/2019 10:08 PM
 * @Version 1.0
 */
@Configuration
public class UserFeignClientConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, SECONDS.toMillis(1),2);
    }
}
