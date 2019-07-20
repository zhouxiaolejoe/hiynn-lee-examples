package com.hiynn.dynamic.datasource.generator.service.impl;

import com.hiynn.dynamic.datasource.generator.entity.TUserInfoExample;
import com.hiynn.dynamic.datasource.generator.mapper.TUserInfoMapper;
import com.hiynn.dynamic.datasource.generator.service.TUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@Service
public class TUserInfoServiceImpl implements TUserInfoService {
	@Autowired
	TUserInfoMapper userInfoMapper;
	@Override
	public long countByExample(TUserInfoExample example) {
		return userInfoMapper.countByExample(example);
	}
}
