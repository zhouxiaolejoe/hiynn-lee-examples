package com.hiynn.dynamic.datasource.service;

import com.hiynn.dynamic.datasource.entity.TUser;
import com.hiynn.dynamic.datasource.entity.UserInfo;

import java.util.List;

public interface UserMongoService {
    /**
     * @return java.util.List<com.hiynn.dockertest.pojo.UserInfo>
     * @Description
     * @Author ZhouXiaoLe
     * @Date 2019/6/12  14:00
     * @Param []
     **/
    List<UserInfo> getUserInfoAll();

    /**
     * @return com.hiynn.dockertest.pojo.UserInfo
     * @Description
     * @Author ZhouXiaoLe
     * @Date 2019/6/12  14:00
     * @Param [id]
     **/
    UserInfo getUserInfoOne(Object id);

    /**
     * @return void
     * @Description
     * @Author ZhouXiaoLe
     * @Date 2019/6/12  14:00
     * @Param [userInfo]
     **/
    void insertUserInfo(UserInfo userInfo);

    /**
     * @return void
     * @Description
     * @Author ZhouXiaoLe
     * @Date 2019/6/12  14:00
     * @Param [userInfo]
     **/
    void updateUserInfo(UserInfo userInfo);

    /**
     * @return void
     * @Description
     * @Author ZhouXiaoLe
     * @Date 2019/6/12  14:00
     * @Param [id]
     **/
    void deleteUserInfo(Object id);
}
