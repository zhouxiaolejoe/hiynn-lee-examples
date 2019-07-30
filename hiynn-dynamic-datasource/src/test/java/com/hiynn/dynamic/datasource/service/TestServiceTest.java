package com.hiynn.dynamic.datasource.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.entity.TUser;
import com.hiynn.dynamic.datasource.untils.CustomPageInfo;
import com.hiynn.dynamic.datasource.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestServiceTest {
    @Autowired
    TestService service;

    @Test
    public void findTest1() {
        TUser test1 = service.findUserById(1);
    }

    @Test
    public void findTest2() {
        TRole test1 = service.findRoleById(1);

    }

    @Test
    public void insert() {
        service.insertRole();
    }

    @Test
    public void findUserAll() {
        PageHelper.startPage(1, 1);
        List<TUser> userAll = service.findUserAll();
        log.info(FastJsonUtils.getBeanToJson(CustomPageInfo.of(PageInfo.of(userAll))));
    }
}