package com.yzeng.userserver.VO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <一句话功能简述>
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月12日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class UserVO {
	@NotEmpty(message="用户名不能为空")
	private String name;
	@NotEmpty(message="密码不能为空")
	private String password;
	@NotNull(message="年龄不能为空")
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
	
}
