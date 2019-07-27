package com.hiynn.spring.security.job.example5;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example5
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 15:22
 */
@Slf4j
public class MisfireExample {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		Date startTime = DateBuilder.nextGivenSecondDate(null, 5);
		// statefulJob1 will run every three seconds
		// (but it will delay for five seconds)
		JobDetail job = JobBuilder
				.newJob(StatefulDumbJob.class)
				.withIdentity("statefulJob1", "group1")
				//利用传参数方式延迟任务执行
				.usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L)
				.build();

		SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(3)
						.repeatForever())
				.startAt(startTime)
				.build();
		Date ft = scheduler.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");


		job = JobBuilder
				.newJob(StatefulDumbJob.class).withIdentity("statefulJob2", "group1")
				.usingJobData(StatefulDumbJob.EXECUTION_DELAY, 6000L).build();

		trigger =(SimpleTrigger)TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group1")
				.startAt(startTime)
				.withSchedule(simpleSchedule().withIntervalInSeconds(1).repeatForever()
						.withMisfireHandlingInstructionNowWithExistingCount()) // set misfire instructions
				.build();

		ft = scheduler.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");









		scheduler.start();

	}
}
