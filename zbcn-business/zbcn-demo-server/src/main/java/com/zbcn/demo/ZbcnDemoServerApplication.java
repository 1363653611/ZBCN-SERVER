package com.zbcn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
public class ZbcnDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZbcnDemoServerApplication.class, args);
    }

}
