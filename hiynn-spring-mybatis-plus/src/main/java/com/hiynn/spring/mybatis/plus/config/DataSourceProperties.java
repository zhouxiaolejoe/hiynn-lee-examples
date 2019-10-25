package com.hiynn.spring.mybatis.plus.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DataSourceProperties
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:38
 * @Version 1.0.0
 */
@Data
public class DataSourceProperties implements Serializable {


    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    private DataBaseType dataBaseType;

    private OracleInstanceType instanceType;

    private DriverUrlProperties driverUrlProperties;
    /**
     * 数据库主机名/IP
     */
    private String host;
    /**
     * 端口号
     */
    private String port;

    /**
     * 数据库名称
     */
    private String databaseName;
}
