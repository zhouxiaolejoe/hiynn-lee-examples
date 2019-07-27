package com.hiynn.dynamic.datasource.generator.service;

import com.github.pagehelper.PageHelper;
import com.hiynn.dynamic.datasource.generator.entity.TUserInfo;
import com.hiynn.dynamic.datasource.generator.entity.TUserInfoExample;
import com.hiynn.dynamic.datasource.generator.mapper.TUserInfoMapper;
import com.hiynn.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
	@Test
	public void deleteByExample(){
		TUserInfoExample example = new TUserInfoExample();
		example.createCriteria().andIdEqualTo(6);
		long count = tUserInfoMapper.deleteByExample(example);
	}
	@Test
	public void deleteByPrimaryKey(){
		long count = tUserInfoMapper.deleteByPrimaryKey(6);
	}
	@Test
	public void insert(){
		TUserInfo record = new TUserInfo();
		record.setAccount("1111");
		record.setPassword("11111");
		record.setUsername("1111");
		tUserInfoMapper.insert(record);

	}
	@Test
	public void selectByExample(){
		PageHelper.startPage(1, 2);
		TUserInfoExample example = new TUserInfoExample();
		example.or().andUsernameEqualTo("guest");
		example.or().andUsernameEqualTo("1111");
		List<TUserInfo> userInfos = tUserInfoMapper.selectByExample(example);
		log.info(FastJsonUtils.getBeanToJson(userInfos));

	}
	@Test
	public void updateByExample(){
		TUserInfoExample example = new TUserInfoExample();
		example.or().andUsernameEqualTo("1111");
		TUserInfo record = new TUserInfo();
		record.setAccount("222");
		record.setId(12);
		record.setPassword("222");
		record.setUsername("222");
		int i = tUserInfoMapper.updateByExample(record, example);
		log.info(String.valueOf(i)+"--->");

	}
	@Test
	public void updateByPrimaryKeySelective(){
		TUserInfo record = new TUserInfo();
		record.setAccount("444");
		record.setId(12);
		record.setPassword(null);
		record.setUsername("444");
		int i = tUserInfoMapper.updateByPrimaryKeySelective(record);
		log.info(String.valueOf(i)+"--->");

	}

}