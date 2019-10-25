package com.hiynn.spring.quartz.config.activemq;

import com.google.common.collect.Maps;
import com.hiynn.spring.quartz.untils.DataBaseUtils;
import com.hiynn.spring.quartz.untils.FastJsonUtils;
import com.hiynn.spring.quartz.untils.JdbcProperties;
import com.hiynn.spring.quartz.untils.Zip4JUtils;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName DataBaseListener
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/20 16:55
 * @Version 1.0.0
 */
@Component
@Slf4j
public class DataBaseListener {

    @Value("${file.base.export-path}")
    private String exportPath;
    @Value("${file.base.import-path}")
    private String importPath;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @JmsListener(destination = "database.export")
    public void exportDataBaseMessage(String message){
        log.info("数据库导出开始");
        String dataBaseFileName = String.format("%s.sql",UUID.randomUUID().toString().replace("-", ""));
        /**
         *  拼接导出文件路径及名字
         */
        String dataBasePath=exportPath+dataBaseFileName;
//        JdbcProperties properties = JdbcProperties.builder()
//                .host(param.get("host"))
//                .port(param.get("port"))
//                .username(param.get("username"))
//                .password(param.get("password"))
//                .exportPath(dataBasePath)
//                .exportDataBase(param.get("exportDataBase"))
//                .build();

            JdbcProperties properties = JdbcProperties.builder()
            .host("localhost")
            .port("3306")
            .username("root")
            .password("123456")
            .exportPath(dataBasePath)
            .exportDataBase("test")
            .build();
        DataBaseUtils.exportDataBase(properties);
        String zipName = dataBaseFileName.replaceAll("\\.sql", "\\.zip");
        Map<String, String> map = Maps.newHashMapWithExpectedSize(2);
        map.put("fileName",zipName);
        map.put("queue","File.Transport"); //目标节点队列
        map.put("exportPath",exportPath);


        String jsonParam = FastJsonUtils.getBeanToJson(map);

        try {
            String pwd = UUID.randomUUID().toString().replace("-", "");

            Zip4JUtils.zip(dataBasePath,exportPath+zipName,false,pwd);

            redisTemplate.opsForValue().set(zipName,pwd);
            jmsTemplate.convertAndSend("send.file",jsonParam);
        } catch (ZipException e) {
            e.printStackTrace();
        }
        log.info("数据库导出完成");
    }
    @JmsListener(destination = "database.import")
    public void importDataBaseMessage(String message){

        String message1 = (String) redisTemplate.opsForValue().get(message);
        try {
            Zip4JUtils.unZip(new File(importPath+message),importPath,message1);
        } catch (java.util.zip.ZipException e) {
            e.printStackTrace();
        } catch (ZipException e) {
            e.printStackTrace();
        }
        String replace = message.replaceAll("\\.zip", "\\.sql");

        log.info("数据库导入开始");
        JdbcProperties properties = JdbcProperties.builder()
                .host("192.168.238.101")
                .port("3306")
                .username("root")
                .password("123456")
                .importPath(importPath+replace)
                .build();
        DataBaseUtils.importDataBase(properties);
        new File(importPath+replace).delete();
        log.info("数据库导入完成");
    }

}
