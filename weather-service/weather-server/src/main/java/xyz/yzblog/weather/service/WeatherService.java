package xyz.yzblog.weather.service;

import xyz.yzblog.weather.vo.WeatherResBodyVO;

/**
 * 天气服务接口，提供天气的查询
 * @author  yzblog.xyz
 * @version  [1.0, 2018年11月26日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public interface WeatherService {
	/**
	 * 根据城市ID查询天气数据
	 * @author yzblog.xyz
	 * @date 2018年11月26日 下午2:27:02
	 * @title  getWeatherDateByCityId
	 * @param cityId 
	 * @return WeatherResBody
	 * @see [类、类#方法、类#成员]
	 */
	WeatherResBodyVO getWeatherDateByCityId(String cityId);
	
	/**
	 * 根据城市名称查询天气数据
	 * @author yzblog.xyz
	 * @date 2018年11月26日 下午2:29:12
	 * @title getWeatherDateByCityName
	 * @param cityName
	 * @return WeatherResBody
	 * @see [类、类#方法、类#成员]
	 */
	WeatherResBodyVO getWeatherDateByCityName(String cityName);
}
