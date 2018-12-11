package com.yzeng.userserver.exception;

import com.yzeng.userserver.enums.ResultEnum;

/**
 * 自定义用户服务异常 
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月11日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class UserException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	public UserException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public UserException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
	
}
