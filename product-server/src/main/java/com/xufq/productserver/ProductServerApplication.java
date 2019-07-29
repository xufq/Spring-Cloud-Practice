package com.xufq.productserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ProductServerApplication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 7/28/2019 11:10 PM
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductServerApplication {

    public static void main(String[] args){
        SpringApplication.run(ProductServerApplication.class,args);
    }
}
