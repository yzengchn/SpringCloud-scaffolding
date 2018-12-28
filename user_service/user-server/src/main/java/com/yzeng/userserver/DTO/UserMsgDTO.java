package com.yzeng.userserver.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yzeng.userserver.DO.MessageDO;

public class UserMsgDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String password;
	private Date createTime;
	private Date lastTime;
	private String ip;
	private List<MessageDO> msg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<MessageDO> getMsg() {
		return msg;
	}
	public void setMsg(List<MessageDO> msg) {
		this.msg = msg;
	}
	
	
	
	
}
