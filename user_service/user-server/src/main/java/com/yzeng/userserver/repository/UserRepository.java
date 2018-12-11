package com.yzeng.userserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzeng.userserver.DO.UserDO;

public interface UserRepository extends JpaRepository<UserDO, Integer>{
	
}
