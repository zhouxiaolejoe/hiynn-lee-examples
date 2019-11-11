package com.hiynn.spring.mybatis.plus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: chenmingjian
 * @Date: 18-10-25 18:14
 */
 
public class CodeGenerator {

    private static final String TABLE_NAME ="database_info";
    private static final String JDBC_URL ="jdbc:mysql://10.0.91.85:3306/datacenter_zyml?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String USER_NAME ="root";
    private static final String PASSWORD ="hymysql";
    private static final String DRIVER_NAME ="com.mysql.cj.jdbc.Driver";

    /**
     * 模块名 父项目下子项目  非子项目的分包的模块名字
     */
    private static final String MODULE = "hiynn-spring-mybatis-plus";
    private static final String PROJECT_PATH = System.getProperty("user.dir");



    public static void main(String[] args) {
        /**
         * 1. 代码生成器
         */
        AutoGenerator mpg = new AutoGenerator();
        /**
         * 2. 设置全局配置
         */
        mpg.setGlobalConfig(setGlobalConfig());

        /**
         * 3. 设置数据源
         */
        mpg.setDataSource(setDataSources());
        /**
         * 4. 设置包配置
         */
        PackageConfig pc = setPackageConfig(mpg);
        mpg.setPackageInfo(pc);
        /**
         * 5.设置自定义配置
         */
        mpg.setCfg(customConfig());
        /**
         * 6.设置模板
         */
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        /**
         * 7.设置策略
         */
        mpg.setStrategy(setStrategy(pc));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        /**
         * 8.执行
         */
        mpg.execute();
    }
    /**
    * @Description 自定义配置
    * @Method customConfig
    * @return com.baomidou.mybatisplus.generator.InjectionConfig
    * @Author ZhouXiaoLe
    * @Date  2019-09-02  12:04:35
    **/
    public static InjectionConfig customConfig(){
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return PROJECT_PATH + "/" + MODULE
//                        + "/src/main/resources/mapper/"
//                        + tableInfo.getEntityName()
//                        + "Mapper" + StringPool.DOT_XML;
                return PROJECT_PATH + "/" + MODULE
                        + "/src/main/java/com/hiynn/spring/mybatis/plus/lxp/mapper/"
                        + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }


        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
    * @Description 配置策略
    * @Method setStrategy
    * @Param pc
    * @return com.baomidou.mybatisplus.generator.config.StrategyConfig
    * @Author ZhouXiaoLe
    * @Date  2019-09-02  12:01:18
    **/
    public static StrategyConfig setStrategy(PackageConfig pc){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        /**
//         * 自定义继承的Entity类全称，带包名
//         */
////        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        // 公共父类
//        /**
//         * 自定义继承的Entity类全称，带包名
//         */
////        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        // 写于父类中的公共字段
////        strategy.setSuperEntityColumns("id");
        return strategy
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(TABLE_NAME)
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(pc.getModuleName() + "_");
    }


    /**
    * @Description 设置包配置
    * @Method setPackageConfig
    * @Param mpg
    * @return com.baomidou.mybatisplus.generator.config.PackageConfig
    * @Author ZhouXiaoLe
    * @Date  2019-09-02  11:17:19
    **/
    public static PackageConfig setPackageConfig(AutoGenerator mpg){
        PackageConfig pc = new PackageConfig();
        /**
         * 设置包的父包名   配置以后 直接写mapper,entity
         */
        return pc
            .setParent("com.hiynn.spring.mybatis.plus.lxp")
            .setEntity("entity")
            .setMapper("mapper")
            .setService("service")
            .setServiceImpl("service.impl");
    }

    /**
     * @Description 设置全局配置
     * @Method setGlobalConfig
     * @return GlobalConfig
     * @Author ZhouXiaoLe
     * @Date  2019-09-02  11:00:12
     **/
    public static GlobalConfig setGlobalConfig(){
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        return globalConfig.setOutputDir(PROJECT_PATH + "/" + MODULE + "/src/main/java")
        .setAuthor("joe")
        .setOpen(false);
    }
    /**
    * @Description 设置数据源
    * @Method setDataSources
    * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
    * @Author ZhouXiaoLe
    * @Date  2019-09-02  10:59:58
    **/
    public static DataSourceConfig setDataSources(){

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(JDBC_URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USER_NAME);
        dataSourceConfig.setPassword(PASSWORD);
        return dataSourceConfig;
    }
}