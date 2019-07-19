package com.hiynn.dynamic.datasource.schedule;

import com.hiynn.dynamic.datasource.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
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

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	TestService testService;


	/**
	* @Description  @Async多线程定时任务
	* @Author ZhouXiaoLe
	* @Date  2019/7/17  17:27
	* @Param []
	* @return void
	**/
//	@Scheduled(cron = "0/2 * * * * *")
//	@Async
//	public void scheduled(){
//		log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
//		messagingTemplate.convertAndSend("/topic/public", testService.findUserById(1));
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
