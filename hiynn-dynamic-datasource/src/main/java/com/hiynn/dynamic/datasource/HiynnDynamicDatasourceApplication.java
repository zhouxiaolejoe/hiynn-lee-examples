package com.hiynn.dynamic.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description exclude = DataSourceAutoConfiguration.class 排除数据库自动init
 * exclude = PageHelperAutoConfiguration.class 排除分页自动init
 * @Author ZhouXiaoLe
 * @Date 2019/7/17  14:58
 * @Param
 * @return
 **/
@SpringBootApplication
public class HiynnDynamicDatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiynnDynamicDatasourceApplication.class, args);
    }
}
