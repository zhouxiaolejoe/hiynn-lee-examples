package com.hiynn.dynamic.datasource.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* @Description  数据源枚举
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:34
* @Param 
* @return 
**/
@AllArgsConstructor
@Getter
public enum DataSourceEnum {

    DEFAULT("db1"),
    DB1("db1"),
    DB2("db2");
    private String name;
}
