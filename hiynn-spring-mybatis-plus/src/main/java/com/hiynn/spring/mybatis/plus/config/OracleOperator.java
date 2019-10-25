package com.hiynn.spring.mybatis.plus.config;

/**
 * @ClassName OracleOperator
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:29
 * @Version 1.0.0
 */
public class OracleOperator extends BaseDataBaseOperator{


    public OracleOperator(String allSampleDataSqlSuffix, String allTablesSql, String allColumnsSql, DataSourceProperties dataSourceProperties) {
        super(allSampleDataSqlSuffix, allTablesSql, allColumnsSql, dataSourceProperties);
    }
}
