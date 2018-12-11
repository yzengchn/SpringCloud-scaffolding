package com.yzeng.userserver.moduls.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.moduls.user.service.UserService;
import com.yzeng.userserver.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserRepository userRepository;
	
	@Override
	public List<UserDO> listUsers() {
		return userRepository.findAll();
	}
	
}
