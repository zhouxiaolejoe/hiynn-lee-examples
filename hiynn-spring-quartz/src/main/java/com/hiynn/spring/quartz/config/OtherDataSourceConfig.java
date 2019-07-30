package com.hiynn.spring.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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
@MapperScan(basePackages = {"com.hiynn.spring.quartz.mapper.other"},sqlSessionFactoryRef = "otherSqlSessionFactory")
public class OtherDataSourceConfig {

    private static final String otherLocalMapper = "classpath:mapper/**/*.xml";

    /**
    * @Description 配置quartz单独数据源
    * @Method dataSource
    * @return javax.sql.DataSource
    * @Author ZhouXiaoLe
    * @Date  2019-07-30  14:20:24
    **/
    @Bean("otherDataSource")
    @ConfigurationProperties("spring.datasource.other")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "otherSqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("otherDataSource") DataSource dataSource) throws  Exception {
        SqlSessionFactoryBean bean;
        bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(OtherDataSourceConfig.otherLocalMapper));
        return bean.getObject();
    }

    @Bean(name = "otherTransaction")
    public DataSourceTransactionManager buildTransactionManager(@Qualifier("otherDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "otherSqlSessionTemplate")
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("otherSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
