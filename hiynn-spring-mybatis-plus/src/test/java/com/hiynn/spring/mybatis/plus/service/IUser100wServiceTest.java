//package com.hiynn.spring.mybatis.plus.service;
//
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.hiynn.spring.mybatis.plus.HiynnSpringMybatisPlusApplicationTests;
//import com.hiynn.spring.mybatis.plus.entity.User100w;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Map;
//
//public class IUser100wServiceTest extends HiynnSpringMybatisPlusApplicationTests {
//    @Autowired
//    IUser100wService user100wService;
//
//    @Test
//    public void select01(){
//        User100w byId = user100wService.getById(12239104);
//        System.err.println(byId);
//    }
//    @Test
//    public void select02(){
//        Map<String, Object> map = user100wService.getMap(Wrappers.<User100w>lambdaQuery().eq(User100w::getLastName, "小乐1"));
//        System.err.println(map);
//    }
//
//    @Test
//    public void select03(){
//        User100w user100w = user100wService.getOne(Wrappers.<User100w>lambdaQuery().eq(User100w::getLastName, "小乐1"));
//        System.err.println(user100w);
//
//    }
//
//    @Test
//    public void select04(){
//        List<User100w> list = user100wService.lambdaQuery().eq(User100w::getLastName, "小乐1").list();
//        System.err.println(list);
//    }
//
//    @Test
//    public void select05(){
//
//    }
//}