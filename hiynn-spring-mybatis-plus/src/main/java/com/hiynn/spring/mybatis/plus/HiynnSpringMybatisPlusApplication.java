package com.hiynn.spring.mybatis.plus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.hiynn.spring.mybatis.plus.mapper","com.hiynn.spring.mybatis.plus.lxp.mapper"})
@EnableTransactionManagement
public class HiynnSpringMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiynnSpringMybatisPlusApplication.class, args);
    }
    /**
     * 添加分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
