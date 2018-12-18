package com.yzeng.userserver.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yzeng.userserver.DO.UserDO;

@Mapper
public interface UserMapper {
	
	List<UserDO> listUser();
}
