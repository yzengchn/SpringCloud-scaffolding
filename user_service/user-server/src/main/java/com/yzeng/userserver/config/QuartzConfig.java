package com.yzeng.userserver.config;


import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yzeng.userserver.task.SendEmailTask;
import com.yzeng.userserver.task.SendNoticeTask;

//@Configuration
public class QuartzConfig {

    private static final String SEND_EMAIL_TASK = "sendEmailTask";
    private static final String SEND_EMAIL_TASK_TIME = "sendEmailTask";
    
    private static final String SEND_NOTICE_TASK = "sendNoticeTask";
    private static final String SEND_NOTICE_TASK_TIME = "*/5 * * * * ?";

    @Bean
    public JobDetail sendEmailDetail() {
        return JobBuilder.newJob(SendEmailTask.class).withIdentity(SEND_EMAIL_TASK).storeDurably().build();
    }

    @Bean
    public Trigger sendEmailTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInHours(2)  //两个小时执行一次
//                .withIntervalInMinutes(1)//一分钟一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(sendEmailDetail())
                .withIdentity(SEND_EMAIL_TASK)
                .withSchedule(scheduleBuilder)
                .build();
    }
    
    
    
    @Bean
    public JobDetail sendNoticeDetail() {
    	return JobBuilder.newJob(SendNoticeTask.class).withIdentity(SEND_NOTICE_TASK).storeDurably().build();
    }
    
    @Bean
    public Trigger sendNoticeTrigger() {
    	return TriggerBuilder.newTrigger().forJob(sendNoticeDetail())
    			.withIdentity(SEND_NOTICE_TASK)
    			.withSchedule(CronScheduleBuilder.cronSchedule(SEND_NOTICE_TASK_TIME))
    			.build();
    }
}
