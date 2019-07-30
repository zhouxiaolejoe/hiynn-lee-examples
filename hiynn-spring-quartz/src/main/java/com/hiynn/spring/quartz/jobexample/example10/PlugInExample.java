package com.hiynn.spring.quartz.jobexample.example10;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example10
 * @Author ZhouXiaoLe
 * @Date 2019-07-29 10:05
 */
@Slf4j
public class PlugInExample {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

//		JobDetail job = JobBuilder
//				.newJob(SimpleJob.class)
//				.withIdentity("job1", "group1")
//				.build();
        scheduler.start();
    }
}
