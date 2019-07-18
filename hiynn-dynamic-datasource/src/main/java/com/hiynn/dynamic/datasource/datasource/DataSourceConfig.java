package com.hiynn.dynamic.datasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
* @Description  创建数据源
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:32
* @Param 
* @return 
**/
@Configuration
public class DataSourceConfig {

    /**
     * @Description  数据源(由JavaBeanBinder实现参数绑定)
     * @Author ZhouXiaoLe
     * @Date  2019/7/16  13:38
     * @Param []
     * @return javax.sql.DataSource
     **/
    @Bean("test1Db")
    @ConfigurationProperties("spring.datasource.test1")
    public DataSource test1Db() {
        return new DruidDataSource();
    }

   /**
   * @Description  数据源
   * @Author ZhouXiaoLe
   * @Date  2019/7/16  13:38
   * @Param []
   * @return javax.sql.DataSource
   **/
    @Bean("test2Db")
    @ConfigurationProperties("spring.datasource.test2")
    public DataSource test2Db() {

        return new DruidDataSource();
    }

    /**
    * @Description  动态数据源
    * @Author ZhouXiaoLe
    * @Date  2019/7/16  13:38
    * @Param []
    * @return javax.sql.DataSource
    **/
    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(test1Db());
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceEnum.DB1.getName(), test1Db());
        dataSourceMap.put(DataSourceEnum.DB2.getName(), test2Db());
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
}
