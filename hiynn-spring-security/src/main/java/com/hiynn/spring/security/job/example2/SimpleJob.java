package com.hiynn.spring.security.job.example2;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.text.SimpleDateFormat;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example2
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 10:20
 */
@Slf4j
public class SimpleJob implements Job {

    //初始化作业空的构造函数
    public SimpleJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey job1 = context.getJobDetail().getKey();
        SimpleTrigger trigger1 = (SimpleTrigger) context.getTrigger();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date = format.format(System.currentTimeMillis());
//		log.error("SimpleJob says: " + job1 + " executing at " + date);
//		log.error("SimpleJob says: " + trigger1 + " executing at " + date);
        log.warn("分组:" + job1.getGroup() + "任务:"
                + job1.getName() + " 将会在: "
                + format.format(context.getFireTime()) + "执行,"
                + "触发器组:" + trigger1.getKey().getGroup()
                + "触发器名称:" + trigger1.getKey().getName());
    }
}
