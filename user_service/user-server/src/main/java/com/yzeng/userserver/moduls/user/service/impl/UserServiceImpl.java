package com.yzeng.userserver.moduls.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DO.UserInfoDO;
import com.yzeng.userserver.DTO.UserMsgDTO;
import com.yzeng.userserver.consts.RedisConsts;
import com.yzeng.userserver.enums.ResultEnum;
import com.yzeng.userserver.exception.UserException;
import com.yzeng.userserver.moduls.user.service.UserService;
import com.yzeng.userserver.repository.UserRepository;
import com.yzeng.userserver.utils.IdKeyUtils;
import com.yzeng.userserver.utils.Md5Utils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	@Cacheable(value="userInfo",key="'page_'+#page")
	public List<UserInfoDO> listUsers(Integer page, Integer size, Integer sort) {
		Sort orderBy = Sort.by(Sort.Direction.DESC,"createTime","lastTime");
		List<UserInfoDO> list = userRepository.findAll(PageRequest.of(page,size,orderBy)).getContent();
		//排序(注册时间倒叙，如果注册时间相等比较最后登录时间)
		return list;
	}

	@Override
	@Cacheable(value="userInfo",key="'user_'+#username")
	public UserInfoDO getUserByName(String username) {
		amqpTemplate.convertSendAndReceive("topic.message", username);
		return userRepository.getUserByName(username);
	}

	@Override
	@Transactional
	public UserInfoDO saveUser(UserMsgDTO dto) {
		UserInfoDO userDO = new UserInfoDO();
		BeanUtils.copyProperties(dto, userDO);
		//当前时间作为盐
		String salt = Md5Utils.getCurrentDateMd5Str();
		//MD5加盐
		String md5AddSalt = Md5Utils.getMd5AddSalt(salt, userDO.getPassword());
		
		userDO.setId(IdKeyUtils.genUniqueUUId());
		userDO.setPassword(md5AddSalt);
		userDO.setSalt(salt);
		userDO.setCreateTime(new Date());
		userDO.setLastTime(new Date());
		
		UserInfoDO do1 = userRepository.save(userDO);
		
		if(do1 == null) {
			throw new UserException(ResultEnum.USER_SAVE_FAIL);
		}
		return do1;
	}
	
	@Override
	public UserMsgDTO login(String username, String password) {
		UserInfoDO userDO = userRepository.getUserByName(username);
		//用户是否存在
		if(userDO == null) {
			throw new UserException(ResultEnum.USER_NOT_EXIST);
		}
		//密码是否一致
		if(!userDO.getPassword().equals(Md5Utils.getMd5AddSalt(userDO.getSalt(), password))) {
			throw new UserException(ResultEnum.USER_PASSWORD_ERROR);
		}
		//生成Token
		String token = IdKeyUtils.genUniqueKey();
		try {
			//放入Redis
			stringRedisTemplate.opsForValue()
				.set(String.format(RedisConsts.TOKEN_TEMPLATE, userDO.getId()), token, 2L, TimeUnit.HOURS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(ResultEnum.SYSTEM_EXCEPTION);
		}
		
		UserMsgDTO dto = new UserMsgDTO();
		BeanUtils.copyProperties(userDO, dto);
		
		return dto;
	}
	
	@Override
	public void resetPassword(String userId, String oldPassword, String password, String ip) {
		UserInfoDO userDO = userRepository.findById(userId).get();
		String salt = userDO.getSalt();
		String dbPassword = userDO.getPassword();
		String md5AddSalt = Md5Utils.getMd5AddSalt(salt, oldPassword);
		//对比数据库和用户输入的是否一致
		if(dbPassword.equals(md5AddSalt)) {
			userDO.setPassword(Md5Utils.getMd5AddSalt(salt, password));
			userDO.setLastTime(new Date());
			userDO.setIp(ip);
			
			userRepository.save(userDO);
			
		}else {
			throw new UserException(ResultEnum.OLD_PASSWORD_ERROR);
		}
		
	}
	
	@Override
	public void logout(String userId) {
		String key = String.format(RedisConsts.TOKEN_TEMPLATE, userId);
		stringRedisTemplate.delete(key);
	}
	
}
