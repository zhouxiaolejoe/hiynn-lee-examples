package com.hiynn.spring.quartz.jobexample.example8;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.quartz.DateBuilder.dateOf;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example8
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 17:20
 */

@Slf4j
public class CalendarExample {
    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // Add the holiday calendar to the schedule
        AnnualCalendar holidays = new AnnualCalendar();

        // fourth of July (July 4)
        Calendar fourthOfJuly = new GregorianCalendar(2020, 6, 4);
        holidays.setDayExcluded(fourthOfJuly, true);
        // halloween (Oct 31) 万圣节(10月31日)
        Calendar halloween = new GregorianCalendar(2020, 9, 31);
        holidays.setDayExcluded(halloween, true);
        // christmas (Dec 25) 圣诞节(12月25)
        Calendar christmas = new GregorianCalendar(2020, 11, 25);
        holidays.setDayExcluded(christmas, true);

        // tell the schedule about our holiday calendar
        scheduler.addCalendar("holidays", holidays, false, false);

        // schedule a job to run hourly, starting on halloween
        // at 10 am
        Date runDate = dateOf(0, 0, 10, 31, 10);

        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(runDate)
                .withSchedule(SimpleScheduleBuilder.repeatHourlyForever(1).repeatForever())
                .modifiedByCalendar("holidays")
                .build();

        Date firstRunTime = scheduler.scheduleJob(job, trigger);
        // print out the first execution date.
        // Note: Since Halloween (Oct 31) is a holiday, then
        // we will not run until the next day! (Nov 1)
        log.info(job.getKey() + " will run at: " + firstRunTime + " and repeat: " + trigger.getRepeatCount()
                + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");

        // All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started

        log.info("------- Starting Scheduler ----------------");
        scheduler.start();

        // wait 30 seconds:
        // note: nothing will run
        log.info("------- Waiting 30 seconds... --------------");
        try {
            // wait 30 seconds to show jobs
            Thread.sleep(30L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
}
