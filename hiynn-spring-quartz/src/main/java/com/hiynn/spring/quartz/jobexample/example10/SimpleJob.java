package com.hiynn.spring.quartz.jobexample.example10;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;
import java.util.Set;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example10
 * @Author ZhouXiaoLe
 * @Date 2019-07-29 10:02
 */
@Slf4j
public class SimpleJob implements Job {
    public SimpleJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("Executing job: " + jobKey + " executing at " + new Date() + ", fired by: " + context.getTrigger().getKey());

        if (context.getMergedJobDataMap().size() > 0) {
            Set<String> keys = context.getMergedJobDataMap().keySet();
            for (String key : keys) {
                String val = context.getMergedJobDataMap().getString(key);
                log.info(" - jobDataMap entry: " + key + " = " + val);
            }
        }

        context.setResult("hello");


    }
}
