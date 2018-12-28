package xyz.yzblog.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import xyz.yzblog.weather.client.hystric.WeatherClientHystric;
import xyz.yzblog.weather.vo.WeatherResBodyVO;


@FeignClient(name="weather",fallback=WeatherClientHystric.class)
public interface WeatherClient {
	@GetMapping("/weather/cityId/{cityId}")
	WeatherResBodyVO getWeatherDateByCityId(@PathVariable("cityId")String cityId);
	
	
	@GetMapping("/weather/cityName/{cityName}")
	public WeatherResBodyVO getWeatherDateByCityName(@PathVariable("cityName")String cityName);
	
	
    
    

}
