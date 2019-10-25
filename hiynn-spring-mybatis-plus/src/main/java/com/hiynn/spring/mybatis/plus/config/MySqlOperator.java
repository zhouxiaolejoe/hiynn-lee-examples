package com.hiynn.spring.mybatis.plus.config;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MySqlOperator
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:29
 * @Version 1.0.0
 */
public class MySqlOperator extends BaseDataBaseOperator{

    private static final String DEFAULT_ALL_TABLES_SQL = "SELECT DISTINCT(table_name) FROM `information_schema`.`columns` " +
            "WHERE `table_schema` = ?";
    private static final String DEFAULT_ALL_COLUMNS_SQL = "SELECT table_name as tableName, column_name as columnName," +
            " data_type as dataType, column_comment as comments " +
            " FROM information_schema.columns WHERE table_name = ? AND table_schema = ?";
    private static  final String allSampleDataSqlSuffix ="limit 100";

    private static  final String endSql =" limit %d,%d";

    public MySqlOperator(String allSampleDataSqlSuffix, String allTablesSql, String allColumnsSql, DataSourceProperties dataSourceProperties) {
        super(allSampleDataSqlSuffix, allTablesSql, allColumnsSql, dataSourceProperties);
    }
    public MySqlOperator(DataSourceProperties dataSourceProperties) {
        this(allSampleDataSqlSuffix,DEFAULT_ALL_TABLES_SQL, DEFAULT_ALL_COLUMNS_SQL,dataSourceProperties);
    }

    @Override
    public List<String> getCurrentDataBaseAllTables() {
        List<Map<String, Object>> allTablesMap = queryForListMap(getAllTablesSql(),
                getDataSourceProperties().getDatabaseName());
        return mappingAllTablesResult(allTablesMap);
    }

    @Override
    public List<DataBaseTableColumn> getCurrentDataBaseAllColumns(String tableName) {
        List<Map<String, Object>> allColumnsMap = queryForListMap(getAllColumnsSql(),
                tableName, getDataSourceProperties().getDatabaseName());
        return mappingAllColumnsResult(allColumnsMap);
    }


    public static void main(String[] args) {


    }
}
