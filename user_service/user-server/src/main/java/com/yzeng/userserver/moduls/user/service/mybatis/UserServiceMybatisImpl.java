package com.yzeng.userserver.moduls.user.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.mapper.user.UserMapper;

@Service
public class UserServiceMybatisImpl {
	
	@Autowired
	private UserMapper userMapper;
	
	public List<UserDO> ListUser(){
		return userMapper.listUser();
	}
	
}
