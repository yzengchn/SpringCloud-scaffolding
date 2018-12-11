package com.yzeng.userserver.moduls.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DTO.UserMsgDTO;
import com.yzeng.userserver.converter.UserDO2UserMsgDTOConverter;
import com.yzeng.userserver.moduls.user.service.UserService;

@RestController
public class UserController {
	
	@Resource
	private UserService userService;
	
	@GetMapping("/get")
	@ResponseBody
	public List<UserMsgDTO> listUser(){
		List<UserDO> listUsers = userService.listUsers();
		List<UserMsgDTO> msgDTOList = UserDO2UserMsgDTOConverter.UserDOList2UserMsgDTOList(listUsers);
		return msgDTOList;
	}
}
