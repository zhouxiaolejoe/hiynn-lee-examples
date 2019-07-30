package com.hiynn.spring.quartz.jobexample.example9;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example9
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 17:36
 */
@Slf4j
public class SimpleJob1 implements Job {
    public SimpleJob1() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("SimpleJob1 says: " + jobKey + " executing at " + new Date());
    }

}
