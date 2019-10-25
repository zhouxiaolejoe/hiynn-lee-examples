package com.hiynn.spring.mybatis.plus.config;

import java.util.List;

/**
 * @ClassName DataBaseOperator
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:17
 * @Version 1.0.0
 */
public interface DataBaseOperator {
    
    /**
    * @Description  获取当前数据源中的所有表
    * @Method getCurrentDataBaseAllTables
    * @return java.util.List<java.lang.String>
    * @Author ZhouXiaoLe
    * @Date  2019-10-21  10:26:44
    **/
    List<String> getCurrentDataBaseAllTables();
    
    /**
    * @Description  获取当前数据源中指定表的字段信息
    * @Method getCurrentDataBaseAllColumns
    * @Param tableName
    * @return java.util.List<com.hiynn.spring.mybatis.plus.config.DataBaseTableColumn>
    * @Author ZhouXiaoLe
    * @Date  2019-10-21  10:27:14
    **/
    List<DataBaseTableColumn> getCurrentDataBaseAllColumns(String tableName);


    /**
     * 获取当前数据源中指定表的样例数据
     */
    
}
