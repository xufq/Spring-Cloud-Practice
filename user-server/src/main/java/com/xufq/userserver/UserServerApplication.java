package com.xufq.userserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName UserServerApplication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:53 PM
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.xufq.practicecore",
        "com.xufq.userserver"})
@EnableEurekaClient
public class UserServerApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(UserServerApplication.class, args);
    }
}
