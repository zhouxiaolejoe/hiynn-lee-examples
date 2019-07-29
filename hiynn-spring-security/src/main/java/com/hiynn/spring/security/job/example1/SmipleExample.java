package com.hiynn.spring.security.job.example1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 09:50
 */
public class SmipleExample {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //1.获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2.使用JobDetail将HelloJob定义Quartz Job
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class)
                .withIdentity("test1", "group1")//设置任务名称   任务组名
                .build();
        //3.创建触发器
        // compute a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3).repeatForever())
                .build();

        //4.将trigger 和 job   使用scheduler相关联
        scheduler.scheduleJob(jobDetail, trigger);
        //5.启动调度程序
        scheduler.start();

        Thread.sleep(90L * 1000L);

        scheduler.shutdown(true);

    }
}
