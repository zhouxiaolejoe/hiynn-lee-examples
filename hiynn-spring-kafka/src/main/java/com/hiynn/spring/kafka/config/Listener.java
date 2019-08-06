package com.hiynn.spring.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Listener
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/5 10:58
 * @Version 1.0.0
 */
@Slf4j
@Component
public class Listener {
    @KafkaListener(id = "foo",topics = {"topic1"})
    public  void listen(String data){
        log.error(data);
    }

}
