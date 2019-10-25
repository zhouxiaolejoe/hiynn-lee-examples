package com.hiynn.spring.quartz.config.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.blob.BlobTransferPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.DeliveryMode;

/**
 * @ClassName Activemq
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/19 17:24
 * @Version 1.0.0
 */
@Configuration
public class ActivemqConfig {
//    /**
//    * @Description jms连接池
//    * @Method pooledConnectionFactory
//    * @return org.apache.activemq.jms.pool.PooledConnectionFactory
//    * @Author ZhouXiaoLe
//    * @Date  2019-08-20  10:49:33
//    **/
//    @Bean
//    public PooledConnectionFactory pooledConnectionFactory(){
//        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
//        pooledConnectionFactory.setConnectionFactory(connectionFactory());
//        pooledConnectionFactory.setMaxConnections(20);
//        pooledConnectionFactory.setMaximumActiveSessionPerConnection(50);
//        return pooledConnectionFactory;
//    }
//    /**
//    * @Description jms监听器工厂
//    * @Method jmsListenerContainerFactory
//    * @return org.springframework.jms.config.JmsListenerContainerFactory
//    * @Author ZhouXiaoLe
//    * @Date  2019-08-20  10:48:59
//    **/
//    @Bean
//    public JmsListenerContainerFactory jmsListenerContainerFactory(){
//        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
//        simpleJmsListenerContainerFactory.setConnectionFactory(connectionFactory());
//        return simpleJmsListenerContainerFactory;
//    }
    /**
    * @Description 容器工厂
    * @Method connectionFactory
    * @return javax.jms.ConnectionFactory
    * @Author ZhouXiaoLe
    * @Date  2019-08-20  10:49:47
    **/
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBlobTransferPolicy(blobTransferPolicy());
        activeMQConnectionFactory.setBrokerURL("nio://10.0.91.88:5671");
        activeMQConnectionFactory.setUserName("admin");
        activeMQConnectionFactory.setPassword("admin");
        activeMQConnectionFactory.setUseAsyncSend(true);
        activeMQConnectionFactory.setDispatchAsync(true);
        return activeMQConnectionFactory;
    }
    /**
    * @Description blobTransferPolicy策略 使用ftp服务器
    * @Method blobTransferPolicy
    * @return org.apache.activemq.blob.BlobTransferPolicy
    * @Author ZhouXiaoLe
    * @Date  2019-08-20  10:49:59
    **/
    @Bean
    public BlobTransferPolicy blobTransferPolicy(){
        BlobTransferPolicy blobTransferPolicy = new BlobTransferPolicy();
        blobTransferPolicy.setDefaultUploadUrl("ftp://admin:admin@10.0.91.88:2341//");
        return blobTransferPolicy;
    }
    /**
    * @Description jms模板配置
    * @Method jmsTemplate
    * @return org.springframework.jms.core.JmsTemplate
    * @Author ZhouXiaoLe
    * @Date  2019-08-20  10:50:30
    **/
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return jmsTemplate;
    }
}
