package com.yzeng.userserver.moduls.user.service;

import java.util.List;

import com.yzeng.userserver.DO.UserDO;

public interface UserService {
	List<UserDO> listUsers();
	UserDO getUserByName(String username);
}
