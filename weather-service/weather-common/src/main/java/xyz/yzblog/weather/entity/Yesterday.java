package xyz.yzblog.weather.entity;

import java.io.Serializable;

/**
 * 昨天天气信息
 * @author  yzblog.xyz
 * @version  [1.0, 2018年11月26日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class Yesterday implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * "date": "25日星期日"
	 */
	private String date;
	/**
	 * "high": "高温 23℃"
	 */
	private String high;
	/**
	 * "fx": "无持续风向"
	 */
	private String fx;
	/**
	 * "low": "低温 19℃"
	 */
	private String low;
	/**
	 * "fl": "<![CDATA[<3级]]>"
	 */
	private String fl;
	/**
	 * "type": "多云"
	 */
	private String type;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
