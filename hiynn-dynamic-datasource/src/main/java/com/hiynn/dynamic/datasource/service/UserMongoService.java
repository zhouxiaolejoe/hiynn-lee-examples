package com.hiynn.dynamic.datasource.service;

import com.hiynn.dynamic.datasource.entity.TUser;
import com.hiynn.dynamic.datasource.entity.UserInfo;

import java.util.List;

public interface UserMongoService {
	/**
	* @Description  
	* @Author ZhouXiaoLe
	* @Date  2019/6/12  14:00
	* @Param []
	* @return java.util.List<com.hiynn.dockertest.pojo.UserInfo>
	**/
	List<UserInfo> getUserInfoAll();
	/**
	* @Description  
	* @Author ZhouXiaoLe
	* @Date  2019/6/12  14:00
	* @Param [id]
	* @return com.hiynn.dockertest.pojo.UserInfo
	**/
	UserInfo getUserInfoOne(Object id);
	/**
	* @Description  
	* @Author ZhouXiaoLe
	* @Date  2019/6/12  14:00
	* @Param [userInfo]
	* @return void
	**/
	void insertUserInfo(UserInfo userInfo);
	/**
	* @Description  
	* @Author ZhouXiaoLe
	* @Date  2019/6/12  14:00
	* @Param [userInfo]
	* @return void
	**/
	void updateUserInfo(UserInfo userInfo);
	/**
	* @Description
	* @Author ZhouXiaoLe
	* @Date  2019/6/12  14:00
	* @Param [id]
	* @return void
	**/
	void deleteUserInfo(Object id);
}
