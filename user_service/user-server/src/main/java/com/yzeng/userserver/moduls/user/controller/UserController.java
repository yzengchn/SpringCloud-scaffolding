package com.yzeng.userserver.moduls.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DTO.UserMsgDTO;
import com.yzeng.userserver.VO.ResultVO;
import com.yzeng.userserver.VO.UserVO;
import com.yzeng.userserver.converter.UserDO2UserMsgDTOConverter;
import com.yzeng.userserver.enums.ResultEnum;
import com.yzeng.userserver.exception.UserException;
import com.yzeng.userserver.moduls.user.service.UserService;
import com.yzeng.userserver.moduls.user.service.mybatis.UserServiceMybatisImpl;
import com.yzeng.userserver.utils.IPUtils;
import com.yzeng.userserver.utils.ResultVOUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "用户服务")
@RestController
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@Autowired
	private UserServiceMybatisImpl userServiceMybatis;
	
	@GetMapping("/get")
	@ApiOperation(value="查询用户列表",notes="分页查询用户列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page",value="页码(从0起)",dataType="int"),
		@ApiImplicitParam(name="size",value="每页数量",dataType="int")
	})
	public List<UserMsgDTO> listUser(@RequestParam("page")Integer page,
									 @RequestParam("size")Integer size)
	{
		List<UserDO> listUser = userServiceMybatis.ListUser();
		List<UserDO> listUsers = userService.listUsers(page, size, null);
		List<UserMsgDTO> msgDTOList = UserDO2UserMsgDTOConverter.UserDOList2UserMsgDTOList(listUsers);
		return msgDTOList;
	}
	
	@GetMapping("/get/name/{username}")
	public UserMsgDTO getUserByName(@PathVariable("username")String username) {
		return UserDO2UserMsgDTOConverter.userDO2UserMsgDTO(userService.getUserByName(username));
	}
	
	@PostMapping("login")
	@ApiOperation(value = "用户登录")
	public ResultVO login(@RequestParam("username")String username,
						  @RequestParam("password")String password) {
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new UserException(ResultEnum.PARAMS_NOT_ERROR);
		}
		
		UserMsgDTO userMsgDTO = userService.login(username,password);
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(userMsgDTO, userVO);
		
		return ResultVOUtils.success(userVO);
	}
	
	@PostMapping("/register")
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
		if(StringUtils.isBlank(password) || StringUtils.isBlank(oldPassword) 
				|| userId == null) {
			throw new UserException(ResultEnum.PARAMS_NOT_ERROR);
		}
		
		String ip = IPUtils.getIpAddr(request);
		
		userService.resetPassword(userId,oldPassword,password,ip);
		
		return ResultVOUtils.success();
	}
	
	
	@GetMapping("/logout/{userId}")
	@ApiOperation(value="注销登录")
	public ResultVO logout(@PathVariable("userId")String userId) {
		userService.logout(userId);
		return ResultVOUtils.success();
	}
}
