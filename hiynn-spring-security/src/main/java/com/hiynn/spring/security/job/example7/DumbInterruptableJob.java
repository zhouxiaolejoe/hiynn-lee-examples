package com.hiynn.spring.security.job.example7;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 * @Descriptionv A dumb implementation of an InterruptableJob, for unit testing purposes.
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.job.example7
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 16:45
 */
//@PersistJobDataAfterExecution
//@DisallowConcurrentExecution
@Slf4j
public class DumbInterruptableJob implements InterruptableJob {

	// has the job been interrupted?
	private boolean _interrupted = false;

	public DumbInterruptableJob() {
	}

	// job name
	private JobKey _jobKey = null;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		_jobKey = context.getJobDetail().getKey();
		log.error("---- " + _jobKey + " 执行中... " + new Date());

		try {
			for (int i = 0; i < 4; i++) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//检查我们是否被打扰了
				if (_interrupted){
					log.error("--- " + _jobKey + "  -- 打断... bailing out!");
					return;
				}
			}
		} finally {

			log.error("---- " + _jobKey + " 完成 " + new Date());

		}

	}

	//此方法中断作业
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.info("---" + _jobKey + "  -- 打断 --");
		_interrupted = true;
	}
}
