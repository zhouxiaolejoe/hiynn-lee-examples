package com.hiynn.spring.security.job.example3;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example3
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 14:30
 */
@Slf4j
public class CronTriggerExample {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// job 1 will run every 20 seconds
		JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();

		CronTrigger trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
				.build();

		Date ft = scheduler.scheduleJob(job, trigger);

		log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
				+ trigger.getCronExpression());

		// job 2 will run every other minute (at 15 seconds past the minute)
		job =  JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group1")
				.withSchedule(cronSchedule("5 0/2 * * * ?")).build();

		ft = scheduler.scheduleJob(job, trigger);
		log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
				+ trigger.getCronExpression());

	// job 3 will run every other minute but only between 8am and 5pm
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger3", "group1")
				.withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
//				.withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
				.build();

		ft = scheduler.scheduleJob(job, trigger);
		log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
				+ trigger.getCronExpression());



		scheduler.start();
	}
}
