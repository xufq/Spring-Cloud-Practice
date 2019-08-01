package com.xufq.userserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName UserServerApplication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:53 PM
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
