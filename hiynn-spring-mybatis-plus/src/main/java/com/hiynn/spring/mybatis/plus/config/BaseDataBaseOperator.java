package com.hiynn.spring.mybatis.plus.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @ClassName BaseDataBaseOperator
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:27
 * @Version 1.0.0
 */
@Slf4j
@Data
@AllArgsConstructor
public abstract class BaseDataBaseOperator implements DataBaseOperator{
    /**
     * 样本数据前缀
     */
    private static final String ALL_SAMPLE_DATA_SQL_PREFIX = "SELECT * FROM ? ";
    /**
     * 样本数据后缀
     */
    private  String allSampleDataSqlSuffix = "";


    private String allTablesSql;
    private String allColumnsSql;
    private DataSourceProperties dataSourceProperties;


    @Override
    public List<String> getCurrentDataBaseAllTables() {
        log.debug("获取所有表名称: 数据源: {}, sql: {}", dataSourceProperties, getAllTablesSql());
        List<Map<String, Object>> result = queryForListMap(dataSourceProperties, getAllTablesSql());
        return mappingAllTablesResult(result);
    }
    /**
     * 对查询所有表名称结果的映射，默认情况下值读取到Map中的第一个值
     */
    protected List<String> mappingAllTablesResult(List<Map<String, Object>> allTablesMap) {
        return allTablesMap.stream().map(tableMap -> String.valueOf(tableMap.entrySet().stream().findFirst().get().getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DataBaseTableColumn> getCurrentDataBaseAllColumns(String tableName) {
        log.debug("获取 {}表信息: 数据源: {}, sql: {}", tableName, dataSourceProperties, getAllColumnsSql());
        List<Map<String, Object>> result = queryForListMap(getAllColumnsSql(), tableName);
        return mappingAllColumnsResult(result);
    }
    protected List<DataBaseTableColumn> mappingAllColumnsResult(List<Map<String, Object>> allColumnsMap) {
        return allColumnsMap.stream().map(columnMap -> convertMap2Bean(columnMap, new DataBaseTableColumn()))
                .collect(Collectors.toList());
    }
    /**
     * 获取JdbcTemplate
     */
    protected JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(DataSourceUtils.getDataSource(this.dataSourceProperties));
    }

    protected JdbcTemplate getJdbcTemplate(DataSourceProperties dataSourceProperties) {
        return new JdbcTemplate(DataSourceUtils.getDataSource(dataSourceProperties));
    }

    protected List<Map<String, Object>> queryForListMap(DataSourceProperties dataSourceProperties, String sql, Object... params) {
        return getJdbcTemplate(dataSourceProperties).queryForList(sql, params);
    }
    protected List<Map<String, Object>> queryForListMap(String sql, Object... params) {

        return getJdbcTemplate().queryForList(sql, params);
    }
    private <T> T convertMap2Bean(Map<String, Object> map, T bean) {
        try {
            BeanUtils.populate(bean, map);
            return bean;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Map2Bean转换失败", e);
        }
    }
}
