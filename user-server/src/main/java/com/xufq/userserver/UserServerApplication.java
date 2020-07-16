package com.xufq.userserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName UserServerApplication
 * @Description manage userinfo, addressinfo and roleinfo
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:53 PM
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.xufq.practicecore",
        "com.xufq.userserver"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.xufq.practicecore.client"})
public class UserServerApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(UserServerApplication.class, args);
    }
}
