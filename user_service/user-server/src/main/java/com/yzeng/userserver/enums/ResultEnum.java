package com.yzeng.userserver.enums;

/**
 * 全局返回结果枚举 
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月11日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public enum ResultEnum {
	REGISTER_FAIL(1001, "注册失败，请重试"),
	USER_SAVE_FAIL(1099,"保存用户失败！"),
	
	PARAMS_ERROR(9001, "参数错误"),
	SYSTEM_EXCEPTION(9999,"系统异常！"),
	SUCCESS(1,"操作成功！"),
	;
	
	private Integer code;
	
	private String msg;
	
	ResultEnum(Integer code, String msg) {
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
