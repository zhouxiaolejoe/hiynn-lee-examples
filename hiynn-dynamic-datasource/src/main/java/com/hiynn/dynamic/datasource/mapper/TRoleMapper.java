package com.hiynn.dynamic.datasource.mapper;

import com.hiynn.dynamic.datasource.config.datasource.DS;
import com.hiynn.dynamic.datasource.config.datasource.DSEnum;
import com.hiynn.dynamic.datasource.entity.TRole;
import org.apache.ibatis.annotations.Param;

public interface TRoleMapper {
	TRole findRoleById(@Param("id")Integer id);
	int insertRole();
}
