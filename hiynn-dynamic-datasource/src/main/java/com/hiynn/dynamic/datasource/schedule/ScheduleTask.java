package com.hiynn.dynamic.datasource.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
* @Description
* @Author ZhouXiaoLe
* @Date  2019/7/17  17:16
* @Param 
* @return 
**/
@Component
@Slf4j
public class ScheduleTask {
	/**
	* @Description  @Async多线程定时任务
	* @Author ZhouXiaoLe
	* @Date  2019/7/17  17:27
	* @Param []
	* @return void
	**/
//	@Scheduled(cron = "0/5 * * * * *")
//	@Async
//	public void scheduled(){
//		log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
//	}
//	@Scheduled(fixedRate = 5000)
//	@Async
//	public void scheduled1() {
//		log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
//	}
//	@Scheduled(fixedDelay = 5000)
//	@Async
//	public void scheduled2() {
//		log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
//	}

}
