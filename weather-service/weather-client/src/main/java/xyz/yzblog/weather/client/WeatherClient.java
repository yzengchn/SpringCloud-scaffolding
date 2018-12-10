package xyz.yzblog.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import xyz.yzblog.weather.vo.WeatherResBody;


@FeignClient(name="weather")
public interface WeatherClient {
	@GetMapping("/weather/cityId/{cityId}")
	WeatherResBody getWeatherDateByCityId(@PathVariable("cityId")String cityId);
	
	
	@GetMapping("/weather/cityName/{cityName}")
	public WeatherResBody getWeatherDateByCityName(@PathVariable("cityName")String cityName);
	
}
