package com.hiynn.spring.quartz.jobexample.example11;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.DateBuilder.futureDate;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example11
 * @Author ZhouXiaoLe
 * @Date 2019-07-29 10:12
 */

@Slf4j
public class LoadExample {

    private int _numberOfJobs = 500;

    public LoadExample(int inNumberOfJobs) {
        _numberOfJobs = inNumberOfJobs;
    }

    public void run() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        for (int count = 1; count < _numberOfJobs; count++) {
            JobDetail job = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity("job" + count, "group_1")
                    .requestRecovery()
                    .build();

            long timeDelay = (long) (Math.random() * 2500);

            job.getJobDataMap().put(SimpleJob.DELAY_TIME, timeDelay);

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger" + count, "group_1")
                    .startAt(futureDate((10000 + (count * 100)), DateBuilder.IntervalUnit.MILLISECOND))
                    .build();
            scheduler.scheduleJob(job, trigger);


            if (count % 25 == 0) {
                log.info("...scheduled " + count + " jobs");
            }

        }

        scheduler.start();

    }

    public static void main(String[] args) throws SchedulerException {

        int numberOfJobs = 500;
        if (args.length == 1) {
            numberOfJobs = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            System.out.println("Usage: java " + LoadExample.class.getName() + "[# of jobs]");
            return;
        }
        LoadExample example = new LoadExample(numberOfJobs);
        example.run();


    }
}
