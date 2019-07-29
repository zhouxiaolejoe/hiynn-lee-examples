package com.hiynn.spring.security.job.example8;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * @Description This is just a simple job that gets fired off many times by example 1
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example8
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 17:20
 */
@Slf4j
public class SimpleJob implements Job {
    /**
     * Empty constructor for job initialization
     */
    public SimpleJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        log.warn("SimpleJob says: " + jobKey + " executing at " + new Date());
    }
}
