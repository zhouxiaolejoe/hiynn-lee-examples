package com.hiynn.dynamic.datasource.service;


import com.hiynn.dynamic.datasource.datasource.DataSource;
import com.hiynn.dynamic.datasource.datasource.DataSourceEnum;
import com.hiynn.dynamic.datasource.dto.UserDTO;
import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.entity.TUser;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @Description  TestService
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:34
* @Param 
* @return 
**/
public interface TestService {

    @DataSource(DataSourceEnum.DB1)
    TUser findUserById(Integer id);

    @DataSource(DataSourceEnum.DB2)
    TRole findRoleById(Integer id) ;

    @DataSource(DataSourceEnum.DB2)
    void insertRole() ;

    @DataSource(DataSourceEnum.DB1)
    int insertUser(UserDTO userDTO);

    @DataSource(DataSourceEnum.DB1)
    UserDTO updatetUser(UserDTO userDTO);
    @DataSource(DataSourceEnum.DB1)
    List<TUser> findUserAll();
}
