package com.hiynn.micro.service.controller;

import com.hiynn.micro.service.api.feign.IConsumerClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProducerClientController
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/11/11 11:47
 * @Version 1.0.0
 */
@AllArgsConstructor
@RestController
public class ProducerClientController {
    IConsumerClient consumerClient;

}
