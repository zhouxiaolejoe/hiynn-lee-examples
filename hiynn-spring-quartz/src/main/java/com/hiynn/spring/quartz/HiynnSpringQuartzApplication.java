package com.hiynn.spring.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HiynnSpringQuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiynnSpringQuartzApplication.class, args);
    }

}
