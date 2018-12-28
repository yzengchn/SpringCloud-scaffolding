package xyz.yzblog.weather.client.hystric;

import org.springframework.stereotype.Component;

import xyz.yzblog.weather.client.WeatherClient;
import xyz.yzblog.weather.vo.WeatherResBodyVO;

//@Component
public class WeatherClientHystric implements WeatherClient{

	@Override
	public WeatherResBodyVO getWeatherDateByCityId(String cityId) {
		WeatherResBodyVO vo = new WeatherResBodyVO();
		vo.setDesc("暂无天气信息");
		vo.setStatus("error");
		return vo;
	}

	@Override
	public WeatherResBodyVO getWeatherDateByCityName(String cityName) {
		WeatherResBodyVO vo = new WeatherResBodyVO();
		vo.setDesc("暂无天气信息");
		vo.setStatus("error");
		return vo;
	}

}
