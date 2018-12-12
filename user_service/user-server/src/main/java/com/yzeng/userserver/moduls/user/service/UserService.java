package com.yzeng.userserver.moduls.user.service;

import java.util.List;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DTO.UserMsgDTO;

public interface UserService {
	List<UserDO> listUsers();
	UserDO getUserByName(String username);
	UserDO saveUser(UserMsgDTO dto);
}
