package com.hiynn.spring.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

public interface DynamicCreateTableMapper extends BaseMapper<Object> {
    /**
    * @Description 动态建表
    * @Method dynamicCreateTableMapperByList
    * @return void
    * @Author ZhouXiaoLe
    * @Date  2019-09-25  13:50:32
    **/
    void dynamicCreateTableMapperByList(Map map);
}
