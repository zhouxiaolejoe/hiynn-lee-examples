package com.hiynn.spring.quartz.mapper.other;

import com.hiynn.spring.quartz.entity.TUser;
import com.hiynn.spring.quartz.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TUserInfoMapperTest {
    @Autowired
    TUserInfoMapper tUserInfoMapper;
    @Test
    public void findByUserInfoname() {
        TUser tUser = tUserInfoMapper.findByUserInfoname("joe");
        log.info(FastJsonUtils.getBeanToJson(tUser));
    }
}