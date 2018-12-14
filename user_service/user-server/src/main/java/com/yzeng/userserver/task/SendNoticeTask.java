package com.yzeng.userserver.task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.repository.UserRepository;
import com.yzeng.userserver.utils.IPUtils;

import springfox.documentation.spring.web.json.Json;
import xyz.yzblog.weather.client.WeatherClient;
import xyz.yzblog.weather.vo.WeatherResBodyVO;

/**
 * 定时通过天气情况
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月14日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */

@Component
public class SendNoticeTask extends QuartzJobBean{

	private static Logger log = LoggerFactory.getLogger(SendNoticeTask.class);
	
	@Resource
	private WeatherClient weatherClient;
	
	@Resource
	private UserRepository userReposotory;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			
			List<UserDO> list = userReposotory.findAll();
			
			for (UserDO userDO : list) {
				
				String ip = userDO.getLastIp();
				
				if(StringUtils.isNotBlank(ip) && IPUtils.isIpv4(ip)) {
					String ipArea = IPUtils.getIpArea(ip);
					
					if(!ipArea.equals("未知归属地")) {
						WeatherResBodyVO weather = weatherClient.getWeatherDateByCityName(ipArea);
						String jsonString = JSON.toJSONString(weather);
						
						log.info(jsonString);
						/*
						 * 后续加上发邮件的方式通知
						 */
					}
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static final String IP_CHECK = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";


}
