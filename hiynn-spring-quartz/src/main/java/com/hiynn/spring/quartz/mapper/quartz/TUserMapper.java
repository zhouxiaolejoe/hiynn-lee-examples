package com.hiynn.spring.quartz.mapper.quartz;
import com.hiynn.spring.quartz.entity.TUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhouXiaoLe
 */
public interface TUserMapper {
    TUser findByUsername(@Param("userName") String userName);
}