package com.hiynn.dynamic.datasource.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @Description  DynamicDataSource
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:33
* @Param 
* @return 
**/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Switching dynamic data sources: [" + DataSourceHolder.getDataSource()+"]");
        return DataSourceHolder.getDataSource();
    }
}
