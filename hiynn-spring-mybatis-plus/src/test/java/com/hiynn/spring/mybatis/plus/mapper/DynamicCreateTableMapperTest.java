package com.hiynn.spring.mybatis.plus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicCreateTableMapperTest {
    @Autowired
    DynamicCreateTableMapper dynamicCreateTableMapper;
    @Test
    public void dynamicCreateTableMapperByList() {
        List<String> list = Arrays.asList("name","nock_name");
        List<Map<String,String>> listMap = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("name","name");
        map1.put("type","varchar");
        map1.put("lengh","50");
        map1.put("comment","名称");
        map1.put("default","joe");
        map1.put("empty","0");
        map1.put("key","1");

        Map<String, String> map2 = new HashMap<>();
        map2.put("name","nock_name");
        map2.put("type","varchar");
        map2.put("lengh","10");
        map2.put("comment","昵称");
        map2.put("default","小明");
        map2.put("empty","1");
        map2.put("key","1");


        Map<String, String> map3 = new HashMap<>();
        map3.put("name","id");
        map3.put("type","int");
        map3.put("lengh","11");
        map3.put("comment","主键id");
        map3.put("empty","0");
        map3.put("key","0");
        map3.put("autoIncr","0");

        listMap.add(map1);
        listMap.add(map2);
        listMap.add(map3);

        Map<String, Object> map = new HashMap<>(3);
        map.put("list",listMap);
        map.put("tableName","new_table");
        dynamicCreateTableMapper.dynamicCreateTableMapperByList(map);
    }
}