package com.hiynn.spring.quartz.jobexample.example4;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example4
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 14:54
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ColorJob implements Job {


    public static final String FAVORITE_COLOR = "favorite color";
    public static final String EXECUTION_COUNT = "count";

    // Since Quartz will re-instantiate a class every time it
    // gets executed, members non-static member variables can
    // not be used to maintain state!
    private int _counter = 1;

    public ColorJob() {
    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String favoriteColor = jobDataMap.getString(FAVORITE_COLOR);
        int count = jobDataMap.getInt(EXECUTION_COUNT);

        JobKey jobKey = context.getJobDetail().getKey();
        log.error("ColorJob: " + jobKey + " executing at " + new Date() + "\n" +
                "  favorite color is " + favoriteColor + "\n" +
                "  execution count (from job map) is " + count + "\n" +
                "  execution count (from job member variable) is " + count);

        count++;
        jobDataMap.put(EXECUTION_COUNT, count);

        _counter++;
    }
}
