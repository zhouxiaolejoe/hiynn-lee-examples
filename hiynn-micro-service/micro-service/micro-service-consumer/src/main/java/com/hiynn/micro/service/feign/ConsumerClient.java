package com.hiynn.micro.service.feign;

import com.hiynn.micro.service.api.feign.IConsumerClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IConsumerClient
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/25 16:30
 * @Version 1.0.0
 */
@RestController
@AllArgsConstructor
public class ConsumerClient implements IConsumerClient {

    @Override
    @GetMapping("/test1")
    public void test1() {

    }
}
