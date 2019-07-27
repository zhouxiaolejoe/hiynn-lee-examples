package com.hiynn.dynamic.datasource.service;

import com.hiynn.dynamic.datasource.entity.UserInfo;
import com.hiynn.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MongoTest {
	@Autowired
	UserMongoService mongoService;
	@Autowired
	MongoTemplate mongoTemplate;
	@Test
	public void insertUserInfo(){
		UserInfo userInfo = UserInfo.builder()
				.userAccount("456")
				.userAddress("南京456")
				.userAge(35)
				.userGender(1)
				.userName("joe")
				.userPassword("456")
				.userPhoto("无")
				.build();
		mongoService.insertUserInfo(userInfo);
	}

	@Test
	public void getUserInfoOne(){
		UserInfo infoOne = mongoService.getUserInfoOne("5d311c2a3cd58955401bff5f");
		log.info(FastJsonUtils.getBeanToJson(infoOne));

	}

	@Test
	public void getUserInfoAll(){
		List<UserInfo> infoOne = mongoService.getUserInfoAll();
		log.info(FastJsonUtils.getBeanToJson(infoOne));
	}

	@Test
	public void deleteUserInfo(){
		mongoService.deleteUserInfo("5d242f44fbf66b820f014e55");
	}

	@Test
	public void updateUserInfo(){
		Query query = new Query((Criteria.where("_id").is("5d311c2a3cd58955401bff5f")));
		mongoTemplate.updateFirst(query,Update.update("userPhoto","userPhoto"),UserInfo.class);
	}

}
