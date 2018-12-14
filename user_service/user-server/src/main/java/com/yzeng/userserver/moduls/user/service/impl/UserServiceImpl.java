package com.yzeng.userserver.moduls.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

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
		//当前时间作为盐
		String salt = getCurrentDateMd5Str();
		//MD5加盐
		String md5AddSalt = getMd5AddSalt(salt, userDO.getPassword());
		
		userDO.setPassword(md5AddSalt);
		userDO.setSalt(salt);
		userDO.setRegistertime(new Date());
		userDO.setLastTime(new Date());
		
		UserDO do1 = userRepository.save(userDO);
		
		if(do1 == null) {
			throw new UserException(ResultEnum.USER_SAVE_FAIL);
		}
		return do1;
	}

	@Override
	public void resetPassword(Integer userId, String oldPassword, String password, String ip) {
		UserDO userDO = userRepository.findById(userId).get();
		String salt = userDO.getSalt();
		String dbPassword = userDO.getPassword();
		String md5AddSalt = getMd5AddSalt(salt, oldPassword);
		//对比数据库和用户输入的是否一致
		if(dbPassword.equals(md5AddSalt)) {
			userDO.setPassword(getMd5AddSalt(salt, password));
			userDO.setLastTime(new Date());
			userDO.setLastIp(ip);
			
			userRepository.save(userDO);
			
		}else {
			throw new UserException(ResultEnum.OLD_PASSWORD_ERROR);
		}
		
	}
	
	/**
	 * 获取当前时间戳MD5加密字符
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月14日 上午11:46:38
	 * @title getCurrentMd5Str
	 * @return String
	 */
	String getCurrentDateMd5Str() {
		String millis = String.valueOf(System.currentTimeMillis());
		String salt = DigestUtils.md5DigestAsHex(millis.getBytes()); 
		return salt;
	}
	
	/**
	 * 获得MD5加盐后的字符串 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月14日 上午11:40:03
	 * @title getMd5AddSalt
	 * @param salt
	 * @param password
	 * @return String
	 */
	String getMd5AddSalt(String salt, String password) {
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		String md5AddSaltStr = md5Password + salt;
		return DigestUtils.md5DigestAsHex(md5AddSaltStr.getBytes());
	}
	
}
