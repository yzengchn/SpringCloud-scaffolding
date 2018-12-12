package com.yzeng.userserver.moduls.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DTO.UserMsgDTO;
import com.yzeng.userserver.enums.ResultEnum;
import com.yzeng.userserver.exception.UserException;
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

	@Override
	public UserDO getUserByName(String username) {
		return userRepository.getUserByUsername(username);
	}

	@Override
	@Transactional
	public UserDO saveUser(UserMsgDTO dto) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(dto, userDO);
		UserDO do1 = userRepository.save(userDO);
		if(do1 == null) {
			throw new UserException(ResultEnum.USER_SAVE_FAIL);
		}
		return do1;
	}

	
	
}
