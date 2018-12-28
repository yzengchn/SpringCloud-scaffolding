package com.yzeng.userserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yzeng.userserver.DO.UserInfoDO;

public interface UserRepository extends JpaRepository<UserInfoDO, String>{
	/**
	 * 根据用户名查询用户信息
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月12日 上午10:38:32
	 * @title getUserByName
	 * @param id
	 * @return
	 * @return UserDO
	 */
	UserInfoDO getUserByName(String name);
	
	@Override
	Page<UserInfoDO> findAll(Pageable pageable);
}
