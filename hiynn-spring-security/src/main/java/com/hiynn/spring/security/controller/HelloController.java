package com.hiynn.spring.security.controller;

import com.hiynn.spring.security.properties.CustomPropleProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.controller
 * @Author ZhouXiaoLe
 * @Date 2019-07-23 13:00x
 */
@RestController
@Slf4j
public class HelloController {

	@Autowired
	CustomPropleProperties manProperties;

	@GetMapping("/getInfo/{id}")
	public String getInfo(@PathVariable("id")String id){
		return String.format("Hello World!!! %s %s", manProperties.getMan().getName(),manProperties.getWoman().getName());
	}

	@GetMapping("/getAsyncInfo/{id}")
	public Callable<String> getAsyncInfo(@PathVariable("id")String id){
		log.info("主线程开始");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				log.info("副线程开始");
				Thread.sleep(1000);
				log.info("副线程结束");
				return String.format("Hello World!!! %s", id);
			}
		};
		log.info("主线程结束");
		return callable;
	}
}
