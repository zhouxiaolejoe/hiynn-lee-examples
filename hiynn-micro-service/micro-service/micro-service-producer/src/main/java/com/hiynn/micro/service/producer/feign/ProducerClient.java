package com.hiynn.micro.service.producer.feign;

import com.hiynn.micro.service.producer.api.DTO.TestProducerDTO;
import com.hiynn.micro.service.producer.api.feign.IProducerClient;

/**
 * @ClassName IProducerClient
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/28 9:09
 * @Version 1.0.0
 */

public class ProducerClient implements IProducerClient {

    @Override
    public TestProducerDTO getTestProducerDTO() {
        TestProducerDTO testProducerDTO = new TestProducerDTO();
        testProducerDTO.setName("joe");
        return testProducerDTO;
    }
}
