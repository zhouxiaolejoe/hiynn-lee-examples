package com.hiynn.spring.quartz.jobexample.example2;

import com.hiynn.spring.quartz.jobexample.example1.HelloJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example2
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 10:23
 */
@Slf4j
public class SimpleTriggerExample2 {
    public static void main(String[] args) throws SchedulerException {
        Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
        sched.start();
        Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
// jobs can also be scheduled after start() has been called...
        // job7 will repeat 20 times, repeat every five minutes
        JobDetail job = newJob(HelloJob.class).withIdentity("job7", "group1").build();

        SimpleTrigger trigger =(SimpleTrigger) newTrigger().withIdentity("trigger7", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();

        Date ft  = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");

        // jobs can be fired directly... (rather than waiting for a trigger)
        job = newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();

        sched.addJob(job, true);


        log.info("'Manually' triggering job8...");
        sched.triggerJob(jobKey("job8", "group1"));

        log.info("------- Waiting 10 seconds... --------------");

        try {
            // wait 33 seconds to show jobs
            Thread.sleep(10L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // jobs can be re-scheduled...
        // job 7 will run immediately and repeat 10 times for every second
        log.info("------- Rescheduling... --------------------");
        trigger = newTrigger().withIdentity("trigger7", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();

        ft = sched.rescheduleJob(trigger.getKey(), trigger);
        log.info("job7 rescheduled to run at: " + ft);

        log.info("------- Waiting five minutes... ------------");

    }
}
