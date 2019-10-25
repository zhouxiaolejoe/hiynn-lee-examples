package com.hiynn.spring.mybatis.plus.config;

/**
 * @ClassName DriverUrlProperties
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:41
 * @Version 1.0.0
 */
public interface DriverUrlProperties {
    /**
     * 数据库类型
     * @return
     */
    DataBaseType dbType();

    /**
     * 驱动类名
     * @return
     */
    String driverClassName();

    /**
     * 数据库连接url
     * @return
     */
    String url();
}
