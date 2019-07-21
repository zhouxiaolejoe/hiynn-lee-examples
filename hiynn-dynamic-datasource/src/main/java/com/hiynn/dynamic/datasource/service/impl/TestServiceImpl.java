package com.hiynn.dynamic.datasource.service.impl;


import com.hiynn.dynamic.datasource.dto.UserDTO;
import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.entity.TUser;
import com.hiynn.dynamic.datasource.mapper.TRoleMapper;
import com.hiynn.dynamic.datasource.mapper.TUserMapper;
import com.hiynn.dynamic.datasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TRoleMapper roleMapper;

	@Override
	public TUser findUserById(Integer id)  {
		return userMapper.findUserById(id);
	}
	@Override
	public TRole findRoleById(Integer id){
		return roleMapper.findRoleById(id);
	}
	/**
	 * 如果切面不加@Order，添加Transactional注解会导致切换数据源失效
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertRole() {
		roleMapper.insertRole();
		int i = 1 / 0;
	}

	@Override
	public int insertUser(UserDTO userDTO) {
		return userMapper.insertUser(userDTO);
	}

	@Override
	public UserDTO updatetUser(UserDTO userDTO) {
		userMapper.updatetUser(userDTO);
		return userDTO;

	}

	@Override
	public List<TUser> findUserAll() {
		return userMapper.findUserAll();
	}
}
