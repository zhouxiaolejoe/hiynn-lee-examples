package com.hiynn.spring.mybatis.plus.lxp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hiynn.spring.mybatis.plus.lxp.datadynamic.MysqlDynamicDataSource;
import com.hiynn.spring.mybatis.plus.lxp.entity.DataResourceDetail;
import com.hiynn.spring.mybatis.plus.lxp.entity.DataServiceInfo;
import com.hiynn.spring.mybatis.plus.lxp.entity.QueryDto;
import com.hiynn.spring.mybatis.plus.lxp.mapper.DataResourceDetailMapper;
import com.hiynn.spring.mybatis.plus.lxp.mapper.DataServiceInfoMapper;
import com.hiynn.spring.mybatis.plus.lxp.service.IDataServiceInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据服务详情 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-10-21
 */
@Service
@AllArgsConstructor
@Slf4j
public class DataServiceInfoServiceImpl extends ServiceImpl<DataServiceInfoMapper, DataServiceInfo> implements IDataServiceInfoService {

    DataServiceInfoMapper dataServiceInfoMapper;
    DataResourceDetailMapper dataResourceDetailMapper;

    @Override
    public  Map<String, Object> getDataServiceInfo(Integer id, QueryDto queryDto) {
        /**
         * 查询条件
         */
        String sqlCondition = queryDto.getCondition();
        boolean isTotal = queryDto.isTotal();
        boolean isPaging = queryDto.isPaging();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("SERVICE_ID",id);
        DataServiceInfo dataServiceInfo = dataServiceInfoMapper.selectOne(queryWrapper);
        if( null == dataServiceInfo){
            throw new RuntimeException("没有查询到数据");
        }
        String dataSourceId = dataServiceInfo.getDatasourceid();
        String tableName = dataServiceInfo.getDataTableName();

        QueryWrapper detailWrapper = new QueryWrapper();
        detailWrapper.eq("DATASOURCEID",dataSourceId);
        DataResourceDetail dataResourceDetail = dataResourceDetailMapper.selectOne(detailWrapper);
        if( null == dataResourceDetail){
            throw new RuntimeException("没有查询到数据");
        }


        String ip = dataResourceDetail.getIp();
        String username = dataResourceDetail.getUsername();
        String password = dataResourceDetail.getPassword();
        String port = dataResourceDetail.getPort();
        String dbname = dataResourceDetail.getDbname();
        String dataSourceType = dataResourceDetail.getDbtype();
        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=utf8",
                ip,port,dbname);
        Integer pageNum = queryDto.getPageNum()==null?1:queryDto.getPageNum();
        Integer pageSize = queryDto.getPageSize()==null?10:queryDto.getPageSize();


        Map<String, Object> maps = selectDataSource(isTotal,isPaging,sqlCondition,tableName, dataSourceType,
                url, username, password, pageNum, pageSize);
        return maps;
    }

    /**
     * 选择数据源
     */
    public  Map<String, Object> selectDataSource(boolean isTotal,boolean isPaging,String sqlCondition,String tableName,String dataSourceType,String url, String username, String password,
                                 Integer pageNum,Integer pageSize){
        if("1".equals(dataSourceType)){
            /**
             * 1.oracle
             */

        }else if("2".equals(dataSourceType)){
            /**
             * 2.mysql
             */
            Map<String, Object> maps = executeMysqlDetail(isTotal,isPaging,sqlCondition,url, username, password,tableName,pageNum,pageSize);
            log.info("mysql{}",maps);
            return maps;
        }else {
            /**
             * 其他
             */
            throw new RuntimeException("数据库类型不匹配");
        }
        return null;
    }


    /**
     * 执行MySQL库数据查询
     */
    public  Map<String, Object> executeMysqlDetail(boolean isTotal,boolean isPaging,String sqlCondition,String url, String user, String password,
                                       String tableName,Integer pageNum,Integer pageSize){
        MysqlDynamicDataSource mysqlDynamicDataSource = new MysqlDynamicDataSource(url, user, password);
        try {
            mysqlDynamicDataSource.getConnection();
            List<Map> allData = mysqlDynamicDataSource.selectTableData(isPaging,sqlCondition, tableName, pageNum, pageSize);
            Map<String, Object> result = new HashMap<>(2);
            result.put("list",allData);
            if(isTotal){
                List<Map> allCount = mysqlDynamicDataSource.selectTableDataCount(tableName);
                Integer count = Integer.valueOf(allCount.get(0).get("count").toString());
                result.put("allCount",count);
            }
            return result;
        } catch (Exception e) {
            log.info("数据库连接异常");
        }
        return null;
    }



}
