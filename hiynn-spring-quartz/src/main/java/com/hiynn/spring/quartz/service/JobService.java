package com.hiynn.spring.quartz.service;

import com.hiynn.spring.quartz.dto.QuartzJobDTO;
import com.hiynn.spring.quartz.entity.quartz.QrtzJobDetails;
import com.hiynn.spring.quartz.entity.quartz.QrtzJobDetailsExample;
import com.hiynn.spring.quartz.untils.ResultBuilder;
import org.quartz.SchedulerException;

import java.util.List;

public interface JobService {

    List<QrtzJobDetails> getJobList(QrtzJobDetailsExample example);

    ResultBuilder addJob(QuartzJobDTO quartzJobDTO) throws SchedulerException;

    ResultBuilder triggerJob(String jobName,String jobGroup) throws SchedulerException;

    ResultBuilder pauseJob(String jobName,String jobGroup) throws SchedulerException;
    
    ResultBuilder resumeJob(String jobName,String jobGroup) throws SchedulerException;

    ResultBuilder removeJob(String jobName,String jobGroup) throws SchedulerException;

    ResultBuilder rescheduleJob(QuartzJobDTO quartzJobDTO) throws SchedulerException;
}
