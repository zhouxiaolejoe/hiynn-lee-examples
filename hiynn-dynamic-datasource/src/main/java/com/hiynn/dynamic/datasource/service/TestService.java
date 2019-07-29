package com.hiynn.dynamic.datasource.service;


import com.hiynn.dynamic.datasource.config.datasource.DS;
import com.hiynn.dynamic.datasource.config.datasource.DSEnum;
import com.hiynn.dynamic.datasource.dto.UserDTO;
import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.entity.TUser;

import java.util.List;

/**
 * @Description TestService
 * @Author ZhouXiaoLe
 * @Date 2019/7/16  13:34
 * @Param
 * @return
 **/
public interface TestService {

    @DS("master")
    TUser findUserById(Integer id);

    @DS("slave")
    TRole findRoleById(Integer id);

    @DS("slave")
    void insertRole();

    @DS("master")
    int insertUser(UserDTO userDTO);

    @DS("master")
    UserDTO updatetUser(UserDTO userDTO);

    @DS("master")
    List<TUser> findUserAll();
}
