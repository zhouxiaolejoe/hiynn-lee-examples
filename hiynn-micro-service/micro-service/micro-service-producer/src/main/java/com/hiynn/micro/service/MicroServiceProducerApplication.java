package com.hiynn.micro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZhouXiaoLe
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.hiynn")
public class MicroServiceProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceProducerApplication.class, args);
    }
}
