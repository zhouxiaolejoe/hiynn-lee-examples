package com.hiynn.dynamic.datasource.datasource;

/**
* @Description
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:34
* @Param 
* @return 
**/
public class DataSourceHolder {

    private static final ThreadLocal<String> DS_HOLDER = new ThreadLocal<>();

    public static void setDataSource(DataSourceEnum dataSource) {

        DS_HOLDER.set(dataSource.getName());
    }

    public static String getDataSource() {

        return DS_HOLDER.get();
    }

    public static void clearDataSource() {

        DS_HOLDER.remove();
    }
}
