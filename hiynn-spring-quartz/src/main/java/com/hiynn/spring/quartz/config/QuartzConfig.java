package com.hiynn.spring.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName QuartzConfig
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/7/30 14:06
 * @Version 1.0.0
 */
@Slf4j
@Configuration
public class QuartzConfig {
    /**
    * @Description 配置quartz单独数据源
    * @Method dataSource
    * @return javax.sql.DataSource
    * @Author ZhouXiaoLe
    * @Date  2019-07-30  14:20:24
    **/
    @Bean
    @QuartzDataSource
    @ConfigurationProperties("spring.quartz.properties.org.quartz.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
}
