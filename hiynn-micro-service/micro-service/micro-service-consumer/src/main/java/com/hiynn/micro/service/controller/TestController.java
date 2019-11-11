package com.hiynn.micro.service.controller;

import com.hiynn.micro.service.api.DTO.TestProducerDTO;
import com.hiynn.micro.service.api.feign.IProducerClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/11/11 10:51
 * @Version 1.0.0
 */
@RestController
@AllArgsConstructor
public class TestController {
    IProducerClient producerClient;
    @GetMapping("/test")
    public TestProducerDTO test(){
        return producerClient.testProducerDTO();
    }
}
