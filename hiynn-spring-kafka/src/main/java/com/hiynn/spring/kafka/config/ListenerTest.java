//package com.hiynn.spring.kafka.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName Listener
// * @Description TODO
// * @Author ZhouXiaoLe
// * @Date 2019/8/5 10:58
// * @Version 1.0.0
// */
//@Slf4j
//@Component
//public class ListenerTest {
//    @Autowired
//    KafkaTemplate kafkaTemplate;
//
//    @KafkaListener(id = "aaa",topics = {"topic5"})
//    public  void listen3(String data){
//        log.error(data);
//    }
//
//    @KafkaListener(id = "vvv",topics = {"topic6"})
//    public  void listen(String data){
//        log.error(data);
//        kafkaTemplate.send("topic7","启动导入");
//    }
//
//}
