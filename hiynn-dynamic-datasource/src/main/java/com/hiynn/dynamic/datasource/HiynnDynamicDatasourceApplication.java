package com.hiynn.dynamic.datasource;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @Description
* exclude = DataSourceAutoConfiguration.class 排除数据库自动init
* exclude = PageHelperAutoConfiguration.class 排除分页自动init
* @Author ZhouXiaoLe
* @Date  2019/7/17  14:58
* @Param 
* @return 
**/
@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class,
		PageHelperAutoConfiguration.class
})
@MapperScan({
		"com.hiynn.dynamic.datasource.mapper",
		"com.hiynn.dynamic.datasource.generator.mapper"
})
public class HiynnDynamicDatasourceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HiynnDynamicDatasourceApplication.class, args);
	}
}
