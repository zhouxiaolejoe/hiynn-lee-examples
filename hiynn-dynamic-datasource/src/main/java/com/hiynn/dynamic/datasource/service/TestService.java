package com.hiynn.dynamic.datasource.service;


import com.hiynn.dynamic.datasource.config.datasource.DS;
import com.hiynn.dynamic.datasource.config.datasource.DSEnum;
import com.hiynn.dynamic.datasource.dto.UserDTO;
import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.entity.TUser;

import java.util.List;

/**
* @Description  TestService
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:34
* @Param 
* @return 
**/
public interface TestService {

    @DS(DSEnum.DB1)
    TUser findUserById(Integer id);

    @DS(DSEnum.DB2)
    TRole findRoleById(Integer id) ;

    @DS(DSEnum.DB2)
    void insertRole() ;

    @DS(DSEnum.DB1)
    int insertUser(UserDTO userDTO);

    @DS(DSEnum.DB1)
    UserDTO updatetUser(UserDTO userDTO);

    @DS(DSEnum.DB1)
    List<TUser> findUserAll();
}
