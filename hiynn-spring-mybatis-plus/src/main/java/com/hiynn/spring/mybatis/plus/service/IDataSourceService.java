package com.hiynn.spring.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hiynn.spring.mybatis.plus.entity.DataConstruction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IDataSourceService extends IService<DataConstruction> {
    List<DataConstruction> selectDataConstructionByTableName(@Param("dataBase")String dataBase, @Param("tableName")String tableName);
    List<Map<String,Object>> selectDataByTableName (@Param("tableName")String tableName);
}
