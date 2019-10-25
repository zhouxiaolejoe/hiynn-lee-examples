package com.hiynn.spring.kafka.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestKafka
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/2 16:45
 * @Version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestKafka {
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    KafkaAdmin admin;
    @Test
    public void testKafka(){
        File file = new File("C:\\Users\\Lenovo\\Desktop\\new 1.yml");
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream (new FileInputStream(file));
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = is.read(b)) != -1) {
                //将读取的字节转为字符串对象
                String chunk = new String(b, 0, len);
                kafkaTemplate.send("topic1",chunk);
            }
//        kafkaTemplate.send("topic1", msg);
        } catch (Exception e) {
                e.printStackTrace();
            }

    }


    @Test
    public void testAdmin(){
        AdminClient client = AdminClient.create(admin.getConfig());
        NewTopic topic2 = new NewTopic("topic2", 3, (short) 3);
        client.createTopics(Arrays.asList(topic2));
    }


    @Test
    public void testTransaction() throws InterruptedException {
        Map map = new HashMap<>();
        map.put(KafkaHeaders.TOPIC,"topic3");
        map.put(KafkaHeaders.PARTITION_ID,0);
        map.put(KafkaHeaders.MESSAGE_KEY,"0");
        map.put(KafkaHeaders.TIMESTAMP,System.currentTimeMillis());
        MessageHeaders messageHeaders = new MessageHeaders(map);
        GenericMessage message = new GenericMessage("今天是个好日子", messageHeaders);
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send(message);
            Thread.sleep(2000l);
        }
    }


    @Test
    public void testSendDefault(){
//        /**
//         * 使用sendDefault
//         */
//        defaultKafkaTemplate.sendDefault("123","321");

        Map map = new HashMap<>();
        map.put(KafkaHeaders.TOPIC,"topic3");
//        map.put(KafkaHeaders.PARTITION_ID,0);
//        map.put(KafkaHeaders.MESSAGE_KEY,"0");
//        map.put(KafkaHeaders.TOPIC,"joe");
//        map.put(KafkaHeaders.OFFSET,"myoffset");
        map.put(KafkaHeaders.TIMESTAMP,System.currentTimeMillis());
        MessageHeaders messageHeaders = new MessageHeaders(map);
        GenericMessage message = new GenericMessage("今天是个好日子", messageHeaders);
        ListenableFuture<SendResult<Integer,String >>future = kafkaTemplate.send(message);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer,String >>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.err.println(result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.err.println(ex.getMessage());
            }

        });
    }


























//    private KafkaMessageListenerContainer<Integer, String> createContainer(
//            ContainerProperties containerProps) {
//        Map<String, Object> props = consumerProps();
//        DefaultKafkaConsumerFactory<Integer, String> cf =
//                new DefaultKafkaConsumerFactory<Integer, String>(props);
//        KafkaMessageListenerContainer<Integer, String> container =
//                new KafkaMessageListenerContainer<>(cf, containerProps);
//        return container;
//    }
//
//    private KafkaTemplate<Integer, String> createTemplate() {
//        Map<String, Object> senderProps = senderProps();
//        ProducerFactory<Integer, String> pf =
//                new DefaultKafkaProducerFactory<Integer, String>(senderProps);
//        KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
//        return template;
//    }
//
//    private Map<String, Object> consumerProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.238.106:19093");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return props;
//    }
//
//    private Map<String, Object> senderProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.238.106:19093");
//        props.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }
//    @Test
//    public void testAutoCommit() throws Exception {
//        log.info("Start auto");
//        ContainerProperties containerProps = new ContainerProperties("topic1", "topic2");
//        final CountDownLatch latch = new CountDownLatch(4);
//        containerProps.setMessageListener(new MessageListener<Integer, String>() {
//
//            @Override
//            public void onMessage(ConsumerRecord<Integer, String> message) {
//                log.info("received: " + message);
//                latch.countDown();
//            }
//
//        });
//        KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProps);
//        container.setBeanName("testAuto");
//        container.start();
//        Thread.sleep(1000); // wait a bit for the container to start
//        KafkaTemplate<Integer, String> template = createTemplate();
//        template.setDefaultTopic("topic1");
//        template.sendDefault(0, "foo");
//        template.sendDefault(2, "bar");
//        template.sendDefault(0, "baz");
//
//        template.setDefaultTopic("topic2");
//        template.sendDefault(0, "foo");
//        template.sendDefault(2, "bar");
//        template.sendDefault(0, "baz");
//        template.sendDefault(2, "qux");
//        template.flush();
//        assertTrue(latch.await(20, TimeUnit.SECONDS));
//        container.stop();
//        log.info("Stop auto");
//
//    }
}
