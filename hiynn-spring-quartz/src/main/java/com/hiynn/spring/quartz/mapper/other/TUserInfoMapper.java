package com.hiynn.spring.quartz.mapper.other;
import com.hiynn.spring.quartz.entity.TUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhouXiaoLe
 */
public interface TUserInfoMapper {
    TUser findByUserInfoname(@Param("userName") String userName);
}