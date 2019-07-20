package com.hiynn.dynamic.datasource.generator.service;

import com.hiynn.dynamic.datasource.datasource.DataSource;
import com.hiynn.dynamic.datasource.datasource.DataSourceEnum;
import com.hiynn.dynamic.datasource.generator.entity.TUserInfoExample;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@DataSource(DataSourceEnum.DB1)
public interface TUserInfoService {
	long countByExample(TUserInfoExample example);
}
