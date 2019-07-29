package com.hiynn.spring.security.job.example6;

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
public class BadJob2 implements Job {
    private int calculation;

    /**
     * Empty public constructor for job initialization
     */
    public BadJob2() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("---" + jobKey + " executing at " + new Date());
        // a contrived example of an exception that
        // will be generated by this job due to a
        // divide by zero error

        try {
            int zero = 0;
            calculation = 4815 / zero;
        } catch (Exception e) {
            log.info("--- Error in job!");
            JobExecutionException e2 =
                    new JobExecutionException(e);
            // Quartz will automatically unschedule
            // all triggers associated with this job
            // so that it does not run again
            e2.setUnscheduleAllTriggers(true);
            throw e2;
        }

        log.info("---" + jobKey + " completed at " + new Date());
    }
}
