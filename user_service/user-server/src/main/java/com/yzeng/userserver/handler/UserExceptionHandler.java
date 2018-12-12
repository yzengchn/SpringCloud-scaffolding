package com.yzeng.userserver.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzeng.userserver.VO.ResultVO;
import com.yzeng.userserver.enums.ResultEnum;
import com.yzeng.userserver.exception.UserException;

/**
 * 全局异常处理
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月11日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(value=UserException.class)
	@ResponseBody
	public ResultVO handler(Exception e) {
		ResultVO vo = new ResultVO();
		UserException userException = null;
		if(e instanceof UserException) {
			userException = (UserException) e;
		}else {
			userException = new UserException(ResultEnum.SYSTEM_EXCEPTION);
		}
		
		vo.setCode(userException.getCode());
		vo.setMsg(userException.getMessage());
		return vo;
	}
}
