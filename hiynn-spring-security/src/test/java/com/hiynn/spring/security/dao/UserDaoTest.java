package com.hiynn.spring.security.dao;

import com.hiynn.spring.security.pojo.TUser;
import com.hiynn.spring.security.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.dao
 * @Author ZhouXiaoLe
 * @Date 2019-07-27 19:15
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void findByUsername() {
        TUser user = userDao.findByUsername("joe");
        log.error(FastJsonUtils.getBeanToJson(user));

    }

    //	@Test
//	public void findByUserId(){
//		TUser user = userDao.findByUserId(1L);
//		log.error(FastJsonUtils.getBeanToJson(user));
//		String encode = new BCryptPasswordEncoder().encode("123456");
//		System.err.println(encode);
//	}
    @Test
    public void findByUserId1() {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.err.println(encode);
    }

}