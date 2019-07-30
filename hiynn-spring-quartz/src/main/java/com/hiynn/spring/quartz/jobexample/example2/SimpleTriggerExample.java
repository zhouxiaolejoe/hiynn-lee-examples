package com.hiynn.spring.quartz.jobexample.example2;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example2
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 10:23
 */
@Slf4j
public class SimpleTriggerExample {
    public static void main(String[] args) throws SchedulerException {

        log.info("------- Initializing -------------------");
        //1.获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        log.info("------- Initialization Complete --------");

        log.info("------- Scheduling Jobs ----------------");
        //2.任务1

        JobDetail job1 = newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();
        //3.创建触发器
        //job在未来的5秒,会触发
        Date startTime = DateBuilder.nextGivenSecondDate(null, 5);
        // 任务只会在延迟5秒,后触发一次
        SimpleTrigger trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .build();

        //4.使用scheduler关联触发器和任务
        Date ft = scheduler.scheduleJob(job1, trigger1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());

        // 任务2
        job1 = newJob(SimpleJob.class)
                .withIdentity("job2", "group1")
                .build();

        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger2", "group1")
                .startAt(startTime)
                .build();
        ft = scheduler.scheduleJob(job1, trigger1);
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());
        //任务3 实现重复十次间隔2秒,的任务
        job1 = newJob(SimpleJob.class)
                .withIdentity("job3", "group1")
                .build();
        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger3", "group1")
                .startAt(startTime)
//				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 2))
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        /**
                         * Specify a the number of time the trigger will repeat - total number of
                         * firings will be this number + 1
                         */
                        .withRepeatCount(10)
                        .withIntervalInSeconds(2)
                )
                .build();
        scheduler.scheduleJob(job1, trigger1);
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());
        //相同的任务由另一个触发器触发 重复两次   间隔10秒,
        trigger1 = TriggerBuilder
                .newTrigger()
                .startAt(startTime)
                .withIdentity("trigger3", "group2")
                .withSchedule(SimpleScheduleBuilder
                        .repeatSecondlyForTotalCount(3, 10)
                )
                .forJob(job1)
                .build();
        scheduler.scheduleJob(trigger1);
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());

        // 任务4将运行6次  运行一次执行5次
        // 间隔3秒一次
        job1 = newJob(SimpleJob.class)
                .withIdentity("job4", "group1")
                .build();
        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger4", "group1")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .withRepeatCount(5)
                )
                .build();

        ft = scheduler.scheduleJob(job1, trigger1);
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());

        // 任务5 在未来运行一次 10秒
        job1 = newJob(SimpleJob.class).withIdentity("job5", "group1").build();

        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger5", "group1")
                .startAt(futureDate(10, DateBuilder.IntervalUnit.SECOND))
                .build();
        ft = scheduler.scheduleJob(job1, trigger1);
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());

        // 无线循环。。2秒一次
        job1 = newJob(SimpleJob.class).withIdentity("job6", "group1").build();

        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger6", "group1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
                .build();

        ft = scheduler.scheduleJob(job1, trigger1);
        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());

        //5.启动schedluer
        scheduler.start();

        // 也可以在调用start()之后调度作业
        // 任务7会重复20次 间隔3秒

        job1 = newJob(SimpleJob.class).withIdentity("job7", "group1").build();
        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .startAt(startTime)
                .withIdentity("trigger7", "group1")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withRepeatCount(3)
                        .withIntervalInSeconds(2))
                .build();
        scheduler.scheduleJob(job1, trigger1);

        log.info("分组:" + job1.getKey().getGroup() + "任务:"
                + job1.getKey().getName() + " 将会在: "
                + format.format(ft) + "执行,重复 "
                + trigger1.getRepeatCount() + " 次, 每一次间隔"
                + trigger1.getRepeatInterval() / 1000 + " 秒,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());


//		 jobs can be fired directly... (rather than waiting for a trigger)
        job1 = newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();

        scheduler.addJob(job1, true);

        log.info("'Manually' triggering job8...");
        scheduler.triggerJob(jobKey("job8", "group1"));

        log.info("------- Waiting 5 seconds... --------------");

        try {
            // wait 33 seconds to show jobs
            Thread.sleep(15L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }


        //任务可以被重新安排

        trigger1 = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger7", "group1")
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withRepeatCount(10)
                        .withIntervalInSeconds(3))
                .build();

        ft = scheduler.rescheduleJob(trigger1.getKey(), trigger1);
        log.info("job7 rescheduled to run at: " + ft);


        // display some stats about the schedule that just ran
        SchedulerMetaData metaData = scheduler.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");


        log.info("------- Waiting five minutes... ------------");
        try {
            Thread.sleep(300L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("------- Shutting Down ---------------------");

        scheduler.shutdown(true);

        log.info("------- Shutdown Complete -----------------");
    }
}
