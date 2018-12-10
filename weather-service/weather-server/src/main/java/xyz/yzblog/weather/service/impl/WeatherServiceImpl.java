package xyz.yzblog.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import xyz.yzblog.weather.constants.BaseConstant;
import xyz.yzblog.weather.service.WeatherService;
import xyz.yzblog.weather.vo.WeatherResBody;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	private RestTemplate restTemplate; 

	@Override
	public WeatherResBody getWeatherDateByCityId(String cityId) {
		String uri = BaseConstant.WEATHER_API + "?citykey=" + cityId;
		return this.doGetWeatherData(uri);
	}

	@Override
	public WeatherResBody getWeatherDateByCityName(String cityName) {
		String uri = BaseConstant.WEATHER_API + "?city=" + cityName;
		return this.doGetWeatherData(uri);
	}
	
	/**
	 * 向天气接口API发送GET请求，返回天气信息
	 * @author yzblog.xyz
	 * @date 2018年11月26日 下午3:14:39
	 * @title	
	 * @param url
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private WeatherResBody doGetWeatherData(String url) {
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String ResBoby = null;
		WeatherResBody weather = null;
		
		if(response.getStatusCodeValue() == 200) {
			ResBoby = response.getBody();
		}
		
		weather = JSON.parseObject(ResBoby, WeatherResBody.class);
		return weather;
	}

}
