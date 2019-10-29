package com.hiynn.micro.service.consumer.feign;

import com.hiynn.micro.service.consumer.api.DTO.TestDTO;
import com.hiynn.micro.service.consumer.api.feign.IConsumerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IConsumerClient
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/25 16:30
 * @Version 1.0.0
 */
@RestController
public class ConsumerClient implements IConsumerClient {

    @Override
    @PostMapping("/getTest")
    public TestDTO getTest(@RequestBody TestDTO testDTO) {
        return testDTO;
    }

    @Override
    @PostMapping("/getTest1")
    public TestDTO getTest1(TestDTO testDTO) {
        return testDTO;
    }
}
