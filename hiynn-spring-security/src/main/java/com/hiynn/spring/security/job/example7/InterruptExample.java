package com.hiynn.spring.security.job.example7;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example7
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 16:45
 */
@Slf4j
public class InterruptExample {
	public static void main(String[] args) throws SchedulerException {

		Date startTime = DateBuilder.nextGivenSecondDate(null, 5);

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail job = JobBuilder.newJob(DumbInterruptableJob.class).withIdentity("job1", "group1").build();

		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.withIdentity("triagge1", "group1")
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2).repeatForever())
				.startAt(startTime)
				.build();
		Date ft = scheduler.scheduleJob(job, trigger);

		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		// start up the scheduler (jobs do not start to fire until
		// the scheduler has been started)
		scheduler.start();

		log.info("------- Starting loop to interrupt job every 7 seconds ----------");
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(3000L);
				// tell the scheduler to interrupt our job
				scheduler.interrupt(job.getKey());
			} catch (Exception e) {
				//
			}
		}

		log.info("------- Shutting Down ---------------------");

		scheduler.shutdown(true);






	}
}
