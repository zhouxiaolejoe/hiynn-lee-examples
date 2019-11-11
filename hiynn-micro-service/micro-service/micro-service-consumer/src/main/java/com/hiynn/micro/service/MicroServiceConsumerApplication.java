package com.hiynn.micro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.hiynn")
public class MicroServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceConsumerApplication.class, args);
    }

}
