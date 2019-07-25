package com.hiynn.spring.security.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.schedule
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 16:29
 */
@Configuration
@Slf4j
public class ScheduledTaskConfig{

//	@Bean
	public ScheduledTaskRegistrar scheduledTaskRegistrar(){
		ScheduledTaskRegistrar scheduledTaskRegistrar = new ScheduledTaskRegistrar();
		CronTask cronTask = new CronTask(new Thread(()->
			log.info(" hiynn-lee-examples")),new CronTrigger("0/2 * * * * *"));
		scheduledTaskRegistrar.addCronTask(cronTask);
		return scheduledTaskRegistrar;
	}
}
