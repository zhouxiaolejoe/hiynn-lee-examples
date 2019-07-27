package com.hiynn.spring.security.job.example6;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @Description This job demonstrates how Quartz can handle JobExecutionExceptions that are thrown by jobs.
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example6
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 16:00
 */
@Slf4j
public class JobExceptionExample {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		Date startStime = DateBuilder.nextGivenSecondDate(null, 15);

		JobDetail job = JobBuilder.newJob(BadJob1.class)
				.withIdentity("badJob1", "group1")
				.usingJobData("denominator", "0")
				.build();
		// badJob1 will run every 10 seconds
		// this job will throw an exception and refire
		// immediately
		SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.withSchedule(SimpleScheduleBuilder
						.repeatSecondlyForever(10)
						.repeatForever())
				.build();

		Date ft = scheduler.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		job = JobBuilder.newJob(BadJob2.class)
				.withIdentity("badJob2", "group1")
				.build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.withIdentity("trigger2", "group1")
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5).repeatForever())
				.build();

		ft = scheduler.scheduleJob(job, trigger);

		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");




		scheduler.start();

		try {
			Thread.sleep(30L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		scheduler.shutdown(false);

	}
}
