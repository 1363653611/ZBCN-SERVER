package com.zbcn.microauth2auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroAuth2AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroAuth2AuthApplication.class, args);
    }

}
