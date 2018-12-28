package com.yzeng.userserver.moduls.user.service;

import java.util.List;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DO.UserInfoDO;
import com.yzeng.userserver.DTO.UserMsgDTO;

public interface UserService {
	/**
	 * 查询用户列表 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月13日 下午2:17:30
	 * @title listUsers
	 * @return List<UserDO>
	 */
	List<UserInfoDO> listUsers(Integer page, Integer size, Integer sort);
	
	/**
	 * 根据用户名查询用户 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月13日 下午2:17:54
	 * @title getUserByName
	 * @param username
	 * @return UserDO
	 */
	UserInfoDO getUserByName(String username);
	
	/**
	 * 保存用户
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月13日 下午2:17:12
	 * @title saveUser
	 * @param dto
	 * @return UserDO
	 */
	UserInfoDO saveUser(UserMsgDTO dto);
	
	/**
	 * 修改密码 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月13日 下午2:16:53
	 * @title resetPassword
	 * @param userId
	 * @param oldPassword
	 * @param password
	 * @param ip
	 */
	void resetPassword(String userId, String oldPassword, String password,String ip);
	
	/**
	 * 用户名加密码登录
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月17日 下午2:01:36
	 * @title login
	 * @param username
	 * @param password
	 * @return
	 * @return UserMsgDTO
	 */
	UserMsgDTO login(String username, String password);

	/**
	 * 注销登录 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月17日 下午2:50:15
	 * @title logout
	 * @param userId
	 * @return void
	 */
	void logout(String userId);
}
