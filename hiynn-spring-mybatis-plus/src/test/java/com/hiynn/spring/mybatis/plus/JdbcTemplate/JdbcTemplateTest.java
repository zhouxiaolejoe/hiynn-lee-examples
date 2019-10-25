package com.hiynn.spring.mybatis.plus.JdbcTemplate;

import com.hiynn.spring.mybatis.plus.entity.DataConstruction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName JdbcTemplateTest
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/9/25 9:42
 * @Version 1.0.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    public void test01(){
        String str = "create table mytable (%s)";
        List<String> list = Arrays.asList("id integer DEFAULT NULL COMMENT 'ID'", "name varchar(100) DEFAULT NULL COMMENT '名称'");
        String joint = StringUtils.collectionToCommaDelimitedString(list);
        String sql = String.format(str, joint);
        /**
         *  根据元数据创建表
         */
        jdbcTemplate.execute(sql);

    }
    @Test
    public void test02(){
        String sql = "SELECT\n" +
                "\tcolumn_name,\n" +
                "\tcolumn_comment,\n" +
                "\tdata_type column_type,\n" +
                "\tcolumn_type column_lengh \n" +
                "FROM\n" +
                "\tinformation_schema.COLUMNS \n" +
                "WHERE table_schema = 'test' \n" +
                "\tAND table_name = 'datacenter-datamanager'";

        List<DataConstruction> query = jdbcTemplate.query(sql, (rs,rowNum) -> {
                DataConstruction dataConstruction = new DataConstruction();
                dataConstruction.setColumnName(rs.getString("column_name"));
                dataConstruction.setColumnType(rs.getString("column_type"));
                dataConstruction.setColumnLengh(rs.getString("column_lengh"));
                dataConstruction.setColumnComment(rs.getString("column_comment"));
                return dataConstruction;
        });




        System.err.println(query);
    }

    @Test
    public void test03(){

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name","name");
        map1.put("type","varchar");
        map1.put("lengh","50");
        map1.put("comment","名称");
        map1.put("defaultValue","默认值");
        String format = String.format("%s %s(%s) DEFAULT %s COMMENT %s", map1.get("name"), map1.get("type"), map1.get("lengh"), map1.get("defaultValue"), map1.get("comment"));
        System.err.println(format);
    }

}
