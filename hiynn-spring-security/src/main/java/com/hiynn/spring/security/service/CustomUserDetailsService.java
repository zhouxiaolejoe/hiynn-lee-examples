package com.hiynn.spring.security.service;

import com.hiynn.spring.security.dao.UserDao;
import com.hiynn.spring.security.pojo.TUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.service
 * @Author ZhouXiaoLe
 * @Date 2019-07-27 20:20
 */
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TUser user = userDao.findByUsername(username);
		return user;
	}
}
