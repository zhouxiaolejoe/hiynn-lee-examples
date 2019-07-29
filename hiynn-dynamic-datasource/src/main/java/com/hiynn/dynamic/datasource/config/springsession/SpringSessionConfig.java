package com.hiynn.dynamic.datasource.config.springsession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description
 * @Author ZhouXiaoLe
 * @Date 2019/7/19  20:38
 * @Param
 * @return
 **/
@EnableRedisHttpSession
@Configuration
public class SpringSessionConfig {
    /**
     * @return org.springframework.data.redis.serializer.RedisSerializer<java.lang.Object>
     * @Description spring session K V 序列化
     * @Author ZhouXiaoLe
     * @Date 2019/7/19  21:35
     * @Param []
     **/
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> redisValueSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        return redisValueSerializer;
    }
}
