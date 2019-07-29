package com.hiynn.spring.security.job.example4;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example4
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 14:57
 */
@Slf4j
public class JobStateExample {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // job1 will only run 5 times (at start time, plus 4 repeats), every 10 seconds
        Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
        JobDetail job = JobBuilder.newJob(ColorJob.class).withIdentity("job1", "group1").build();
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .startAt(startTime)
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 2))
                .build();
        // pass initialization parameters into the job
        job.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "red");
        job.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        Date scheduleTime1 = scheduler.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger.getRepeatCount()
                + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");


// job2 will also run 5 times, every 10 seconds
        JobDetail job2 = JobBuilder.newJob(ColorJob.class).withIdentity("job2", "group1").build();

        SimpleTrigger trigger2 = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();

        // pass initialization parameters into the job
        // this job has a different favorite color!
        job2.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "yellow");
        job2.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        // schedule the job to run
        Date scheduleTime2 = scheduler.scheduleJob(job2, trigger2);
        log.info(job2.getKey().toString() + " will run at: " + scheduleTime2 + " and repeat: " + trigger2.getRepeatCount()
                + " times, every " + trigger2.getRepeatInterval() / 1000 + " seconds");


// schedule the job to run
        scheduler.start();

    }
}
