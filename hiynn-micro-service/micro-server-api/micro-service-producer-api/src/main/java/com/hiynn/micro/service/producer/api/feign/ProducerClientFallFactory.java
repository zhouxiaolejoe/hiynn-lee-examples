package com.hiynn.micro.service.producer.api.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProducerClientFallFactory
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/28 9:11
 * @Version 1.0.0
 */
@Component
public class ProducerClientFallFactory implements FallbackFactory<IProducerClient> {
    @Override
    public IProducerClient create(Throwable throwable) {
        return null;
    }
}
