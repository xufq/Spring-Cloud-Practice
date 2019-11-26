package com.xufq.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @ClassName ZipkinServerApplication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 10/13/2019 05:00 PM
 * @Version 1.0
 */
@SpringBootApplication
@EnableZipkinServer
//@EnableDiscoveryClient
public class ZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
