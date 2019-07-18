package com.hiynn.dynamic.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description  exclude = DataSourceAutoConfiguration.class 排除数据库自动init
* @Author ZhouXiaoLe
* @Date  2019/7/17  14:58
* @Param 
* @return 
**/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.hiynn.dynamic.datasource.mapper")
public class HiynnDynamicDatasourceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HiynnDynamicDatasourceApplication.class, args);
	}

}
