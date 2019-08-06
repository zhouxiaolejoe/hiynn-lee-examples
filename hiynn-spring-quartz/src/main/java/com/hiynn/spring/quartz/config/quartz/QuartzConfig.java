package com.hiynn.spring.quartz.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
@MapperScan(basePackages = {"com.hiynn.spring.quartz.mapper.quartz"},sqlSessionFactoryRef = "customQuartzSqlSessionFactory")
public class QuartzConfig {

    private static final String localMapper = "classpath:mapper/**/*.xml";

    /**
    * @Description 配置quartz单独数据源
    * @Method dataSource
    * @return javax.sql.DataSource
    * @Author ZhouXiaoLe
    * @Date  2019-07-30  14:20:24
    **/
    @Bean("customQuartzDataSource")
    @QuartzDataSource
    @Primary
    @ConfigurationProperties("spring.quartz.properties.org.quartz.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "customQuartzSqlSessionFactory")
    @Primary
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("customQuartzDataSource") DataSource dataSource) throws  Exception {
        SqlSessionFactoryBean bean;
        bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(QuartzConfig.localMapper));
        return bean.getObject();
    }

    @Bean(name = "customQuartzTransaction")
    @Primary
    public DataSourceTransactionManager buildTransactionManager(@Qualifier("customQuartzDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "quartzSqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("customQuartzSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
