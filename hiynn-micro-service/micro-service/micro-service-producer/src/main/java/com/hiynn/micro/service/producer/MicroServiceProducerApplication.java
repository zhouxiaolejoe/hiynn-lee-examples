package com.hiynn.micro.service.producer;

import com.hiynn.micro.service.consumer.api.DTO.TestDTO;
import com.hiynn.micro.service.consumer.api.feign.IConsumerClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@AllArgsConstructor
@EnableFeignClients("com.hiynn")
@NoArgsConstructor
public class MicroServiceProducerApplication {

    IConsumerClient consumerClient;

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceProducerApplication.class, args);
    }
    @GetMapping("/test")
    public String test(){
        TestDTO testDTO = new TestDTO();
        testDTO.setName("zxl");

        TestDTO test = consumerClient.getTest(testDTO);

        return test.toString();
    }
}
