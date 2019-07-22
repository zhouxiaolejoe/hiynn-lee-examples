package com.hiynn.dynamic.datasource.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
/**
* @Description  创建数据源
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:32
* @Param 
* @return 
**/
@Configuration
@MapperScan({
        "com.hiynn.dynamic.datasource.mapper",
        "com.hiynn.dynamic.datasource.generator.mapper"
})
public class DataSourceConfig implements EnvironmentAware {

    Environment environment;

    /**
     * @Description  数据源(由JavaBeanBinder实现参数绑定)
     * @Author ZhouXiaoLe
     * @Date  2019/7/16  13:38
     * @Param []
     * @return javax.sql.DS
     **/
    @Bean("master")
    @Qualifier("slave")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource test1Db() {
//        return new DruidDataSource();
        return DataSourceBuilder.create().build();
    }

   /**
   * @Description  数据源
   * @Author ZhouXiaoLe
   * @Date  2019/7/16  13:38
   * @Param []
   * @return javax.sql.DS
   **/
    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource test2Db() {
//        return new DruidDataSource();
        return DataSourceBuilder.create().build();
    }

    /**
    * @Description 简单多数据源
    * @Author ZhouXiaoLe
    * @Date  2019/7/16  13:38
    * @Param []
    * @return javax.sql.DS
    **/
    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(test1Db());
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master", test1Db());
        dataSourceMap.put("slave", test2Db());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }
    /**
    * @Description  事务管理器
    * @Author ZhouXiaoLe
    * @Date  2019/7/16  13:38
    * @Param []
    * @return org.springframework.transaction.PlatformTransactionManager
    **/
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}
