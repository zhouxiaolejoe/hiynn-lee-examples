package com.hiynn.spring.quartz.jobexample.example6;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example6
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 16:00
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BadJob1 implements Job {
    private int calculation;

    /**
     * Empty public constructor for job initialization
     */
    public BadJob1() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        //获取JobDataMap
        JobDataMap map = context.getJobDetail().getJobDataMap();
        int denominator = map.getInt("denominator");
        log.error("---" + jobKey + " executing at " + new Date() + " with denominator " + denominator);
        try {
            calculation = 4815 / denominator;
        } catch (Exception e) {
            JobExecutionException exception = new JobExecutionException(e);
            exception.setRefireImmediately(true);
            throw exception;
        }
        log.info("---" + jobKey + " completed at " + new Date());
    }
}
