package com.hiynn.micro.service.api.feign;

import com.hiynn.micro.service.api.DTO.TestProducerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName IProducerClient
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/28 9:09
 * @Version 1.0.0
 */
@FeignClient(
        value = "micro-service-producer",
        fallbackFactory = ProducerClientFallFactory.class
)
public interface IProducerClient {

    @GetMapping("/testProducerDTO")
    TestProducerDTO testProducerDTO();
}
