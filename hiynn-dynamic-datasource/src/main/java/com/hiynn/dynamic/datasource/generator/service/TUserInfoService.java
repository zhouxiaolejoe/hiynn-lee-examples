package com.hiynn.dynamic.datasource.generator.service;



import com.hiynn.dynamic.datasource.config.datasource.DS;
import com.hiynn.dynamic.datasource.config.datasource.DSEnum;
import com.hiynn.dynamic.datasource.generator.entity.TUserInfo;
import com.hiynn.dynamic.datasource.generator.entity.TUserInfoExample;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@DS("master")
public interface TUserInfoService {
	long countByExample(TUserInfoExample example);
	TUserInfo selectByPrimaryKey(Integer id);
}
