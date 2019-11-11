package com.hiynn.spring.mybatis.plus.lxp.mapper;

//import com.hiynn.spring.mybatis.plus.entity.DataSubscribeInfo;

import com.hiynn.spring.mybatis.plus.entity.DataSubscribeInfo;
import com.hiynn.spring.mybatis.plus.mapper.DataSubscribeInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DataSubscribeInfoMapperTest {
    @Autowired
    DataSubscribeInfoMapper dataSubscribeInfoMapper;
    @Test
    public void Test01 (){
        DataSubscribeInfo dataSubscribeInfo = DataSubscribeInfo
                .builder()
                .applyTime(LocalDateTime.now())
                .authStatus(1)
                .authPeriod("发射点犯得上")
                .catalogShowId(1)
                .regularPeriod("v但是都是非法上访")
                .storageDirectoryId(1)
                .subscribeItemId(2)
                .subscribeNodeId(2)
                .build();

        dataSubscribeInfoMapper.insert(dataSubscribeInfo);
    }


    @Test
    public void Test02 (){
        DataSubscribeInfo dataSubscribeInfo = dataSubscribeInfoMapper.selectById(4);
    }
}