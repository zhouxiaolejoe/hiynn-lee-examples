package com.hiynn.micro.service.producer.api.feign;

import com.hiynn.micro.service.producer.api.DTO.TestProducerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

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
    @PostMapping("/getTestProducerDTO")
    TestProducerDTO getTestProducerDTO();
}
