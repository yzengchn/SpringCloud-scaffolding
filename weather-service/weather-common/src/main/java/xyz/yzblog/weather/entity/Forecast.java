package xyz.yzblog.weather.entity;

import java.io.Serializable;

/**
 * 未来天气信息
 * @author  yzblog.xyz
 * @version  [1.0, 2018年11月26日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class Forecast implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * date": "26日星期一"
	 */
	private String date;
	
	/**
	 * "high": "高温 21℃"
	 */
	private String high;
    /**
     * "fengli": "<![CDATA[<3级]]>"
     */
    private String fengxiang;
    /**
     * "low": "低温 17℃"
     */
    private String low;
    /**
     * "fengxiang": "无持续风向"
     */
    private String fengli;
    /**
     * "type": "小雨"
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
	public String getFengxiang() {
		return fengxiang;
	}
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getFengli() {
		return fengli;
	}
	public void setFengli(String fengli) {
		this.fengli = fengli;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
