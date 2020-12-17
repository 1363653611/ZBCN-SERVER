package com.zbcn.sentinelserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SentinelServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelServerApplication.class, args);
    }

}
