package com.hiynn.spring.security.job.example5;

import org.quartz.*;

import java.util.Date;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.job.example5
 * @Author ZhouXiaoLe
 * @Date 2019-07-26 15:22
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job {
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Constants.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	public static final String NUM_EXECUTIONS  = "NumExecutions";

	public static final String EXECUTION_DELAY = "ExecutionDelay";

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Constructors.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	public StatefulDumbJob() {
	}


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println("---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");
		JobDataMap map = context.getJobDetail().getJobDataMap();
		int executeCount = 0;
		if(map.containsKey(NUM_EXECUTIONS)){
			executeCount=map.getInt(NUM_EXECUTIONS);
		}
		executeCount++;

		map.put(NUM_EXECUTIONS, executeCount);

		//利用传参数方式延迟任务执行
		long delay = 5000L;

		if (map.containsKey(EXECUTION_DELAY)){
			delay = map.getLong(EXECUTION_DELAY);
		}
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ").");
	}
}
