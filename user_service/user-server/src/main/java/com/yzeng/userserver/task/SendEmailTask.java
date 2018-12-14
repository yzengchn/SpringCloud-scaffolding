package com.yzeng.userserver.task;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.moduls.user.service.UserService;

@Component
public class SendEmailTask extends QuartzJobBean{

	@Resource
	private UserService userService;
	
	private static Logger log = LoggerFactory.getLogger(SendEmailTask.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("Task___++++");
		List<UserDO> listUsers = userService.listUsers();
		for (UserDO userDO : listUsers) {
			System.out.println(userDO.toString());
		}
	}
	
}
