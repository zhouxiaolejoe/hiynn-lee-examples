package com.hiynn.dynamic.datasource.service.impl;

import com.hiynn.dynamic.datasource.entity.UserInfo;
import com.hiynn.dynamic.datasource.service.UserMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserMongoServiceImpl implements UserMongoService {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<UserInfo> getUserInfoAll() {
		return  mongoTemplate.findAll(UserInfo.class);
	}

	@Override
	public UserInfo getUserInfoOne(Object id) {
		Query query = new Query((Criteria.where("_id").is(id)));
		return mongoTemplate.findOne(query, UserInfo.class);
	}

	@Override
	public void insertUserInfo(UserInfo userInfo) {
		mongoTemplate.save(userInfo);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		Query query = new Query((Criteria.where("_id").is(userInfo.getUserId())));
		Update update = new Update()
				.set("userName",userInfo.getUserName())
				.set("userAccount",userInfo.getUserAccount())
				.set("userAddress",userInfo.getUserAddress())
				.set("userAge",userInfo.getUserAge());
		mongoTemplate.findAndModify(query,update,UserInfo.class);
	}

	@Override
	public void deleteUserInfo(Object id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query,UserInfo.class);
	}
}
