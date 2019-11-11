package com.hiynn.micro.service.feign;

import com.hiynn.micro.service.api.DTO.TestProducerDTO;
import com.hiynn.micro.service.api.feign.IProducerClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IProducerClient
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/28 9:09
 * @Version 1.0.0
 */
@RestController
@AllArgsConstructor
public class ProducerClient implements IProducerClient {
    @Override
    @GetMapping("/testProducerDTO")
    public TestProducerDTO testProducerDTO() {
        TestProducerDTO testProducerDTO = new TestProducerDTO();
        testProducerDTO.setName("joe");
        return testProducerDTO;
    }
}
