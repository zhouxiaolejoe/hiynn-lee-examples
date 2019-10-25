package com.hiynn.spring.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hiynn.spring.mybatis.plus.entity.DataConstruction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DataSourceMapper
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/9/25 12:44
 * @Version 1.0.0
 */
public interface DataSourceMapper extends BaseMapper<DataConstruction> {
    /**
    * @Description 查询数据结构
    * @Method selectDataConstructionByTableName
    * @Param dataBase
    * @Param tableName
    * @return java.util.List<com.hiynn.spring.mybatis.plus.entity.DataConstruction>
    * @Author ZhouXiaoLe
    * @Date  2019-09-25  13:50:59
    **/
    List<DataConstruction> selectDataConstructionByTableName(@Param("dataBase")String dataBase,@Param("tableName")String tableName);
    /**
    * @Description 查询表数据
    * @Method selectDataByTableName
    * @Param tableName
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author ZhouXiaoLe
    * @Date  2019-09-26  11:31:48
    **/
    List<Map<String,Object>> selectDataByTableName (IPage<Map<String,Object>> page, @Param("tableName")String tableName);

}
