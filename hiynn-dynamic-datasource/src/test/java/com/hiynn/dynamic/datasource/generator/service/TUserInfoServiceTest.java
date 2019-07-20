package com.hiynn.dynamic.datasource.generator.service;

import com.hiynn.dynamic.datasource.generator.entity.TUserInfoExample;
import com.hiynn.dynamic.datasource.generator.mapper.TUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TUserInfoServiceTest {
	@Autowired
	TUserInfoMapper tUserInfoMapper;

	@Test
	public void countByExample(){
		TUserInfoExample example = new TUserInfoExample();
		long count = tUserInfoMapper.countByExample(example);
		log.info(String.valueOf(count)+"----->");
	}

}