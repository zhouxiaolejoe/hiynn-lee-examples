package com.hiynn.spring.quartz.jobexample.example1;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 09:50
 */
@Slf4j
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        log.warn(format.format(System.currentTimeMillis()));
    }
}
