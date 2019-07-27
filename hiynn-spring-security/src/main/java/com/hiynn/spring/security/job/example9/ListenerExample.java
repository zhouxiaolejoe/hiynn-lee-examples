package com.hiynn.spring.security.job.example9;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example9
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 17:36
 */
public class ListenerExample {
	public static void main(String[] args) throws SchedulerException {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


		// schedule a job to run immediately

		JobDetail job = JobBuilder.newJob(SimpleJob1.class).withIdentity("job1").build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1").startNow().build();


//		JobListener listener=new JobListener();
//		Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
//		scheduler.getListenerManager().addJobListener(listener,matcher);
	}

}
