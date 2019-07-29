package com.hiynn.dynamic.datasource.mapper;

import com.hiynn.dynamic.datasource.dto.UserDTO;
import com.hiynn.dynamic.datasource.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    TUser findUserById(@Param("id") Integer id);

    List<TUser> findUserAll();

    int insertUser(UserDTO userDTO);

    int updatetUser(UserDTO userDTO);
}
