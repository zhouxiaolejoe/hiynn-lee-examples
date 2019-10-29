package com.hiynn.micro.service.consumer.api.feign;

import com.hiynn.micro.service.consumer.api.DTO.TestDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConsumerClientFallbackFactory
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/25 16:34
 * @Version 1.0.0
 */
@Component
public class ConsumerClientFallbackFactory implements FallbackFactory<IConsumerClient> {
    @Override
    public IConsumerClient create(Throwable throwable) {
        return new IConsumerClient() {
            @Override
            public TestDTO getTest(TestDTO testDTO) {
                return new TestDTO();
            }

            @Override
            public TestDTO getTest1(TestDTO testDTO) {
                return null;
            }
        };
    }

}
