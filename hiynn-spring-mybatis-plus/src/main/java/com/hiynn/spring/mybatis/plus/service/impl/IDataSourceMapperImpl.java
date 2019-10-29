package com.hiynn.spring.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hiynn.spring.mybatis.plus.entity.DataConstruction;
import com.hiynn.spring.mybatis.plus.mapper.DataSourceMapper;
import com.hiynn.spring.mybatis.plus.service.IDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IDataSourceMapperImpl
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/9/25 13:39
 * @Version 1.0.0
 */
@Service
public class IDataSourceMapperImpl extends ServiceImpl<DataSourceMapper, DataConstruction> implements IDataSourceService {
    @Autowired
    DataSourceMapper dataSourceMapper;

    @Override
    public List<DataConstruction> selectDataConstructionByTableName(String dataBase, String tableName) {
        return dataSourceMapper.selectDataConstructionByTableName(dataBase, tableName);
    }

    @Override
    public List<Map<String, Object>> selectDataByTableName(String tableName) {
        return null;
    }

}
