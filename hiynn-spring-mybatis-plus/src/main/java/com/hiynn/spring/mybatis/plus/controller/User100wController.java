package com.hiynn.spring.mybatis.plus.controller;


import com.hiynn.spring.mybatis.plus.entity.DataConstruction;
import com.hiynn.spring.mybatis.plus.okhttp3.FastJsonUtils;
import com.hiynn.spring.mybatis.plus.okhttp3.ResultBuilder;
import com.hiynn.spring.mybatis.plus.service.IDataSourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author joe
 * @since 2019-09-02
 */
@RestController
@AllArgsConstructor
public class User100wController {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name","joe");
        map.put("age","12");
        String json = FastJsonUtils.getBeanToJson(map);
        System.err.println(FastJsonUtils.getBeanToJson(json));
    }

    IDataSourceService dataSourceService;

    @GetMapping(value = "/test",consumes = "application/json")
    public void testPost(@RequestBody Map map){
        System.err.println(map);
    }

    @PostMapping(value = "/test1")
    public String testPost1(@RequestParam("username")String username, @RequestParam("password")String password){
        System.err.println(username);
        System.err.println(password);

        return username;
    }


    @GetMapping(value = "/selectDataConstructionByTableName")
    public ResultBuilder selectDataConstructionByTableName(){
        List<DataConstruction> result = dataSourceService.selectDataConstructionByTableName("test", "datacenter-datamanager");

        return ResultBuilder.success(result);
    }
    @GetMapping(value = "/dynamicCreateTableMapperByList")
    public ResultBuilder dynamicCreateTableMapperByList(){
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
        return ResultBuilder.success(map);
    }
}
