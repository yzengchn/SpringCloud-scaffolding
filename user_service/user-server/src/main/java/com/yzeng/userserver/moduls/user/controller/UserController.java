package com.yzeng.userserver.moduls.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DTO.UserMsgDTO;
import com.yzeng.userserver.VO.ResultVO;
import com.yzeng.userserver.VO.UserVO;
import com.yzeng.userserver.converter.UserDO2UserMsgDTOConverter;
import com.yzeng.userserver.enums.ResultEnum;
import com.yzeng.userserver.exception.UserException;
import com.yzeng.userserver.moduls.user.service.UserService;
import com.yzeng.userserver.utils.IPUtils;
import com.yzeng.userserver.utils.ResultVOUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@GetMapping("/get")
	@ResponseBody
	public List<UserMsgDTO> listUser(){
		List<UserDO> listUsers = userService.listUsers();
		List<UserMsgDTO> msgDTOList = UserDO2UserMsgDTOConverter.UserDOList2UserMsgDTOList(listUsers);
		return msgDTOList;
	}
	
	@GetMapping("/get/name/{username}")
	@ResponseBody
	public UserMsgDTO getUserByName(@PathVariable("username")String username) {
		return UserDO2UserMsgDTOConverter.userDO2UserMsgDTO(userService.getUserByName(username));
	}
	
	@PostMapping("/regiter")
	@ApiOperation(value = "注册用户")
	public ResultVO Register(@Valid UserVO user, BindingResult bindingResult,HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			log.error("参数错误{}",user.toString());
			throw new UserException(ResultEnum.PARAMS_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
		}
		ResultVO rVO = new ResultVO(ResultEnum.SUCCESS);
		UserMsgDTO dto = new UserMsgDTO();
		BeanUtils.copyProperties(user, dto);
		dto.setLastIp(IPUtils.getIpAddr(request));
		userService.saveUser(dto);
		return rVO;
	}
	
	@PostMapping("/resetPwd")
	@ApiOperation("修改密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value="用户ID"),
		@ApiImplicitParam(name="oldPassword",value="旧密码"),
		@ApiImplicitParam(name="password",value="新密码")
	})
	public ResultVO resetPwd(HttpServletRequest request,
							 @RequestParam("userId")Integer userId,
							 @RequestParam("oldPassword")String oldPassword,
							 @RequestParam("password")String password) {
		String ip = IPUtils.getIpAddr(request);
		
		userService.resetPassword(userId,oldPassword,password,ip);
		
		return ResultVOUtils.success();
	}
	
}
