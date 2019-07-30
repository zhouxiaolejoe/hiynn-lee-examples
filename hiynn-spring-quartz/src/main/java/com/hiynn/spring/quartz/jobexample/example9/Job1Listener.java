package com.hiynn.spring.quartz.jobexample.example9;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example9
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 17:37
 */
@Slf4j
public class Job1Listener implements JobListener {
    @Override
    public String getName() {
        return "job1_to_job2";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("Job1Listener 将被执行");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("Job1Listener 被否决了");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobDetail job2 = JobBuilder.newJob(SimpleJob2.class).withIdentity("job2").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("job2Trigger")
                .startNow()
                .build();

        try {
            context.getScheduler().scheduleJob(job2, trigger);
        } catch (SchedulerException e) {
            log.error("Unable to schedule job2!");
            e.printStackTrace();
        }

    }
}
