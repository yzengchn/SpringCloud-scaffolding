package xyz.yzblog.weather.vo;

import java.io.Serializable;

import xyz.yzblog.weather.entity.Weather;

/**
 * 天气消息的返回对象封装
 * @author  yzblog.xyz
 * @version  [1.0, 2018年11月26日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class WeatherResBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 消息数据
	 */
	private Weather data; 
	/**
	 * 消息状态
	 */
	private String status; 
	/**
	 * 消息描述
	 */
	private String desc;
	public Weather getData() {
		return data;
	}
	public void setData(Weather data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	

}
