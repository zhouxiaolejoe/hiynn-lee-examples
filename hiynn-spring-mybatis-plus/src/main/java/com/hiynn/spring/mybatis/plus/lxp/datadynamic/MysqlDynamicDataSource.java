package com.hiynn.spring.mybatis.plus.lxp.datadynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MysqlDataDynamic
 * @Description TODO 动态创建mysql数据连接及各操作
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:27
 * @Version 1.0.0
 */
@Slf4j
public class MysqlDynamicDataSource extends DynamicDataSource{
    private  String url = "jdbc:mysql://10.0.92.127:3306/test";
    private  String user = "root";
    private  String password = "123456";

    public MysqlDynamicDataSource(String url, String user, String password){
        this.url=url;
        this.user = user;
        this.password = password;
    }
    // 连接数据库的方法
    @Override
    public void getConnection()  throws Exception{
            // 初始化驱动包
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 根据数据库连接字符，名称，密码给conn赋值
            conn = DriverManager.getConnection(url, user, password);
    }

    /**
     * 查询表数据
     * @param tableName
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Map> selectTableData(String tableName,Integer pageNum,Integer pageSize) throws  Exception{
        // 查询数据的sql语句
//        String sql = "select * from " + tableName ;
        int startPage = (pageNum-1) * pageSize;
        String sql = "select * from " + tableName + " limit " + startPage + "," + pageSize;
        log.info("sql语句==>{}",sql);
        return  executeSql(sql);
    }

    @Override
    public List<Map> selectTableData(boolean isPaging,String sql, String tableName, Integer pageNum, Integer pageSize) throws Exception {
        int startPage = (pageNum-1) * pageSize;
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ");
        sb.append(tableName);
        if(! StringUtils.isEmpty(sql)){
            sb.append(String.format(" where  %s",sql));
        }
        if(isPaging){
            sb.append(" limit ");
            sb.append(startPage);
            sb.append(",");
            sb.append(pageSize);
        }
        String executeSql = sb.toString();
        return executeSql(executeSql);
    }

    /**
     * 查询表基本信息
     * @param tableName
     * @return
     */
    @Override
    public List<Map> selectTableInfo(String tableName) throws  Exception{
        String sql = "select column_name as columnname, table_name as tablename,column_type as datatype,column_comment as comments from information_schema.columns where table_name = '" + tableName + "'";
        log.info("sql语句==>{}",sql);
        return executeSql(sql);
    }
    /**
     * 查询表数据量
     * @param tableName
     * @return
     * @throws Exception
     */
    @Override
    public List<Map> selectTableDataCount(String tableName) throws  Exception{
        String sql = "select count(*) count from " + tableName;
        System.out.println(sql);
        return  executeSql(sql);
    }

    // 测试能否与oracle数据库连接成功
    public static void main(String[] args) throws Exception{
      String url = "jdbc:mysql://localhost:3306/bladex";
      String user = "root";
      String password = "123456";
        DynamicDataSource basedao = new MysqlDynamicDataSource(url, user, password);
        basedao.getConnection();
        if (conn == null){
            System.out.println("与mysql数据库连接失败！");
        } else{
            System.out.println("与mysql数据库连接成功！");
        }
        List list = basedao.selectTableData("blade_dict",1,3);
        for (Object s : list) {
            System.err.println(s);
        }

        List<Map> w_optimizition = basedao.selectTableInfo("blade_client");
        for (Object s : w_optimizition) {
            System.err.println(s);
        }
//        System.out.println(list.size());
    }
}
