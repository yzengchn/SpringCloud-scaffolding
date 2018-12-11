package com.yzeng.userserver.enums;

/**
 * 性别枚举
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月11日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public enum SexEnum {
	NUKNOW(0,"未知"),
	MALE(1,"男"),
	FEMALE(2,"女"),
	;
	
	private Integer code;
	
	private String msg;
	
	SexEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
