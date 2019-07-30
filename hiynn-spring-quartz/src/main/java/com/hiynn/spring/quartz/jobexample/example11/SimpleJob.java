/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */

package com.hiynn.spring.quartz.jobexample.example11;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * <p>
 * This is just a simple job that gets fired off many times by example 1
 * </p>
 *
 * @author Bill Kratzer
 */
@Slf4j
public class SimpleJob implements Job {


    // job parameter
    public static final String DELAY_TIME = "delay time";

    /**
     * Empty constructor for job initilization
     */
    public SimpleJob() {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a <code>{@link org.quartz.Trigger}</code> fires that
     * is associated with the <code>Job</code>.
     * </p>
     *
     * @throws JobExecutionException if there is an exception while executing the job.
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("Executing job: " + jobKey + " executing at " + new Date());

        // wait for a period of time
        long delayTime = context.getJobDetail().getJobDataMap().getLong(DELAY_TIME);
        try {
            Thread.sleep(delayTime);
        } catch (Exception e) {
            //
        }

        log.info("Finished Executing job: " + jobKey + " at " + new Date());
    }

}
