package com.hiynn.spring.mybatis.plus.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DataSourceManager
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:48
 * @Version 1.0.0
 */
@Slf4j
public class DataSourceManager {

    private static Map<DataSourceProperties, DataSource> targetDataSources = new ConcurrentHashMap<>();

    /**
     * 连接时最大等待时间，单位毫秒
     */
    @Value("3000")
    private long maxWait;

    /**
     * 设置连接失败重连次数，默认为1，但实际使用的时候，如果oracle的实例名写错了，会不断重连
     */
    @Value("1")
    private int connectionErrorRetryAttempts;

    /**
     * true表示pool向数据库请求连接失败后标记整个pool为block并close，就算后端数据库恢复正常也不进行重连，客户端对pool的请求都拒绝掉
     */
    @Value("true")
    private boolean breakAfterAcquireFailure;

    /**
     * 获取dbProperties对应的数据源
     *
     * @param dataSourceProperties
     * @return
     */
    public DataSource getDataSource(DataSourceProperties dataSourceProperties) {
        DataSource dataSource = targetDataSources.get(dataSourceProperties);

        if (dataSource == null) {
            dataSource = newDataSource(dataSourceProperties);
        }
        log.debug("当前DataSourceManager#targetDataSources中包含的DataSource数量为{}，详细内容：{}",
                targetDataSources.size(), targetDataSources);
        return dataSource;
    }

    public synchronized boolean removeDataSource(DataSourceProperties dataSourceProperties) {
        DataSource dataSource = targetDataSources.get(dataSourceProperties);
        if (dataSource != null) {
            return dataSource == targetDataSources.remove(dataSourceProperties);
        }
        return false;
    }

    /**
     * 新建一个DataSource
     */
    private DataSource newDataSource(DataSourceProperties dataSourceProperties) {
        return doCreateDataSource(dataSourceProperties);
    }

    protected synchronized DataSource doCreateDataSource(DataSourceProperties dataSourceProperties) {
        DataSource dataSource = targetDataSources.get(dataSourceProperties);
        if (dataSource == null) {
            dataSource = newDataSourceInstance(dataSourceProperties);
        }
        return dataSource;
    }

    protected synchronized DataSource newDataSourceInstance(DataSourceProperties dataSourceProperties) {
        DriverUrlProperties driverUrlProperties = dataSourceProperties.getDriverUrlProperties();
        try {
            Class.forName(driverUrlProperties.driverClassName());
        } catch (ClassNotFoundException e) {
            String errorMessage = String.format("加载数据库驱动类[%s]失败！", driverUrlProperties.driverClassName());
            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverUrlProperties.driverClassName());
        druidDataSource.setUrl(driverUrlProperties.url());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        // 检测连接是否有效的超时时间，单位秒，不起作用
         druidDataSource.setValidationQueryTimeout(3);
        // 获取连接时最大等待时间，单位毫秒
        druidDataSource.setMaxWait(maxWait);
        // 设置连接失败重连次数，默认为1，但实际使用的时候，如果oracle的实例名写错了，会不断重连
        druidDataSource.setConnectionErrorRetryAttempts(connectionErrorRetryAttempts);
        //  true表示pool向数据库请求连接失败后标记整个pool为block并close，就算后端数据库恢复正常也不进行重连，客户端对pool的请求都拒绝掉
        druidDataSource.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
        // TODO 其他参数设置，如最大连接数等11
        targetDataSources.put(dataSourceProperties, druidDataSource);

        return druidDataSource;
    }
}
