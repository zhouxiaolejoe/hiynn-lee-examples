package com.hiynn.micro.service.consumer.api.feign;

import com.hiynn.micro.service.consumer.api.DTO.TestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        value = "micro-service-consumer",
       fallbackFactory = ConsumerClientFallbackFactory.class
)
public interface IConsumerClient {
    @PostMapping("/getTest")
    TestDTO getTest(TestDTO testDTO);
    @PostMapping("/getTest1")
    TestDTO getTest1(TestDTO testDTO);



}
