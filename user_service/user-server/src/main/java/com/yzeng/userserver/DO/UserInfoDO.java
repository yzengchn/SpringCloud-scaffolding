package com.yzeng.userserver.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息表
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月28日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
@Entity
@Table(name="t_user_info")
public class UserInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    /**
    * id
    */
    private String id;

    /**
    * 用户名
    */
    private String name;

    /**
    * 密码
    */
    private String password;

    /**
    * 盐
    */
    private String salt;

    /**
    * 性别，0未知 1男 2女
    */
    private int sex;

    /**
    * 电话
    */
    private String tel;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 头像
    */
    private String avatar;

    /**
    * 微信openid
    */
    private String openid;

    /**
    * 用户状态，0已注销 1正常
    */
    private int status;

    /**
    * 登录ip
    */
    private String ip;

    /**
    * 上次登录时间
    */
    private Date lastTime;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    
}
