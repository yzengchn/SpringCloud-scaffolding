package xyz.yzblog.weather.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 天气信息
 * @author  yzblog.xyz
 * @version  [1.0, 2018年11月26日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class WeatherDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * "city": "深圳"
	 */
	private String city;
	/**
	 * "aqi": "26"
	 */
	private String aqi;
	/**
	 * "wendu": "19"
	 */
	private String wendu;
	/**
	 * "ganmao": "天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"
	 */
	private String ganmao;
	/**
	 * 昨日天气
	 */
	private YesterdayDTO yesterday;
	/**
	 * 未来几天天气
	 */
	private List<ForecastDTO> forecast;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public YesterdayDTO getYesterday() {
		return yesterday;
	}
	public void setYesterday(YesterdayDTO yesterday) {
		this.yesterday = yesterday;
	}
	public List<ForecastDTO> getForecast() {
		return forecast;
	}
	public void setForecast(List<ForecastDTO> forecast) {
		this.forecast = forecast;
	}
	
	
}
