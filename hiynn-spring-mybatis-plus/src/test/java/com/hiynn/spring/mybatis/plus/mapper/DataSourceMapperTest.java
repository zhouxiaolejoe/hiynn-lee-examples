package com.hiynn.spring.mybatis.plus.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hiynn.spring.mybatis.plus.entity.DataConstruction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceMapperTest {
    @Autowired
    DataSourceMapper dataSourceMapper;
    @Test
    public void test01(){
        List<DataConstruction> dataConstructions = dataSourceMapper.selectDataConstructionByTableName("test","new_table");
        System.err.println(dataConstructions);
    }

    @Test
    public void test02(){
        Page<Map<String, Object>> page = new Page(1,1000);
        List<Map<String, Object>> list = dataSourceMapper.selectDataByTableName(page,"new_table");
        System.err.println(list);
    }
}