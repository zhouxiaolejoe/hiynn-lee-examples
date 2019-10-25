package com.hiynn.spring.quartz.service.impl;

import com.hiynn.spring.quartz.dto.QuartzJobDTO;
import com.hiynn.spring.quartz.entity.quartz.QrtzJobDetails;
import com.hiynn.spring.quartz.entity.quartz.QrtzJobDetailsExample;
import com.hiynn.spring.quartz.job.MyJob;
import com.hiynn.spring.quartz.mapper.quartz.QrtzJobDetailsMapper;
import com.hiynn.spring.quartz.service.JobService;
import com.hiynn.spring.quartz.untils.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName JobServiceImpl
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/7/31 10:10
 * @Version 1.0.0
 */
@Slf4j
@Service
public class JobServiceImpl implements JobService {


    @Autowired
    QrtzJobDetailsMapper qrtzJobDetailsMapper;

    @Autowired
    Scheduler scheduler;

    @Override
    public List<QrtzJobDetails> getJobList(QrtzJobDetailsExample example) {
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
        try {
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            System.err.println(triggerState);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return qrtzJobDetailsMapper.selectByExample(null);
    }

    @Override
    public ResultBuilder addJob(QuartzJobDTO quartzJobDTO) throws SchedulerException {

        /**
         *  1.创建jobDetail
         */

        JobDetail job = JobBuilder
                .newJob(MyJob.class)
                .withIdentity(quartzJobDTO.getJobName(), quartzJobDTO.getJobGroup())
                .build();
        /**
         *  2.创建触发器
         */
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withDescription(quartzJobDTO.getDescription())
//                .startNow()
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                .withIdentity(quartzJobDTO.getTriggerNmae(),quartzJobDTO.getTriggerGroup())
                .withSchedule(CronScheduleBuilder
                        .cronSchedule(quartzJobDTO.getCronExpression())
                        .withMisfireHandlingInstructionDoNothing())
                .build();
        /**
         * 3.关联job 和 触发器
         */
        this.scheduler.scheduleJob(job,trigger);
        return ResultBuilder.success();
    }

    @Override
    public ResultBuilder triggerJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.triggerJob(JobKey.jobKey(jobName,jobGroup));
        return ResultBuilder.success();
    }

    @Override
    public ResultBuilder pauseJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName,jobGroup));
        return ResultBuilder.success();
    }

    @Override
    public ResultBuilder resumeJob(String jobName, String jobGroup) throws SchedulerException {
//        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
//        scheduler.resumeTrigger(triggerKey);
        scheduler.resumeJob(JobKey.jobKey(jobName,jobGroup));
        return ResultBuilder.success();
    }

    @Override
    public ResultBuilder removeJob(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        scheduler.pauseTrigger(triggerKey);

        scheduler.unscheduleJob(triggerKey);

        scheduler.deleteJob(JobKey.jobKey(jobName,jobGroup));

        return ResultBuilder.success();
    }

    @Override
    public ResultBuilder rescheduleJob(QuartzJobDTO quartzJobDTO) throws SchedulerException {
        /**
         *  注意此处 TriggerKey 封装是触发器名称和触发器组
         */
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzJobDTO.getTriggerNmae(), quartzJobDTO.getTriggerGroup());
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                .withDescription(quartzJobDTO.getDescription())
                .withIdentity(quartzJobDTO.getTriggerNmae(),quartzJobDTO.getTriggerGroup())
                .withSchedule(CronScheduleBuilder
                        .cronSchedule(quartzJobDTO.getCronExpression())
                        .withMisfireHandlingInstructionDoNothing())
                .build();
        scheduler.rescheduleJob(triggerKey,trigger);
        return ResultBuilder.success();
    }
}
