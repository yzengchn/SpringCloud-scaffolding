package xyz.yzblog.weather.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import xyz.yzblog.weather.service.WeatherService;
import xyz.yzblog.weather.vo.WeatherResBody;

/**
 * 天气信息查询控制器
 * @author  yzblog.xyz
 * @version  [1.0, 2018年11月26日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	@ApiOperation(value="城市ID", notes="根据城市ID获取该城市的天气预报信息")
    @ApiImplicitParam(paramType="path", name = "cityId", value = "城市ID", required = true, dataType = "String")
	@GetMapping("/cityId/{cityId}")
	public WeatherResBody getWeatherDateByCityId(@PathVariable("cityId")String cityId) {
		return weatherService.getWeatherDateByCityId(cityId);
	}
	
	@ApiOperation(value="城市名称", notes="根据城市名称获取该城市的天气预报信息")
    @ApiImplicitParam(paramType="path",name = "cityName", value = "城市名称", required = true, dataType = "String")
	@GetMapping("/cityName/{cityName}")
	public WeatherResBody getWeatherDateByCityName(@PathVariable("cityName")String cityName) {
		return weatherService.getWeatherDateByCityName(cityName);
	}
}
