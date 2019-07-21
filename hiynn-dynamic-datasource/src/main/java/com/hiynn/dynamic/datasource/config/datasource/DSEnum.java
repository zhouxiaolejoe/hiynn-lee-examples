package com.hiynn.dynamic.datasource.config.datasource;

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
public enum DSEnum {

    DEFAULT("db1"),
    DB1("db1"),
    DB2("db2");
    public String name;
}
