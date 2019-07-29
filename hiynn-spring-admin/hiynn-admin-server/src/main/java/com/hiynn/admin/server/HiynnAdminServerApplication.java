package com.hiynn.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class HiynnAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiynnAdminServerApplication.class, args);
    }

}
