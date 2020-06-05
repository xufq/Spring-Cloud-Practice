package com.xufq.loginserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName LoginServerApplication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/29/2020 09:58 PM
 * @Version 1.0
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.xufq.loginserver"})
public class LoginServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class, args);
    }
}
