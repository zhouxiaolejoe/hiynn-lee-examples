package com.hiynn.micro.service.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "micro-service-consumer",
       fallbackFactory = ConsumerClientFallbackFactory.class
)
public interface IConsumerClient {
    @GetMapping("test1")
    void test1();
}
