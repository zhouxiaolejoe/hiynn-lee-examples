package com.hiynn.dynamic.datasource.service;

import com.hiynn.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    TestService service;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("joe", service.findUserById(1));
    }

    @Test
    public void test2() {
        Object joe = redisTemplate.opsForValue().get("joe");
        log.info(FastJsonUtils.getBeanToJson(joe));
    }

}
