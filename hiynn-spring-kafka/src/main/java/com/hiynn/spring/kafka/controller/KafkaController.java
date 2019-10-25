package com.hiynn.spring.kafka.controller;

import com.hiynn.spring.kafka.utils.JdbcProperties;
import com.hiynn.spring.kafka.utils.MysqlDumpUntil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName KafkaController
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/5 9:50
 * @Version 1.0.0
 */
@RestController
@Slf4j
public class KafkaController {
    @Autowired
    KafkaAdmin admin;
    @Autowired
    KafkaTemplate kafkaTemplate;
    /**
    * @Description 创建topic
    * @Method testKafka
    * @return void
    * @Author ZhouXiaoLe
    * @Date  2019-08-05  09:58:07
    **/
    @GetMapping("/testKafka")
    public void testKafka(){
        AdminClient client = AdminClient.create(admin.getConfig());
        NewTopic topic5 = new NewTopic("topic1", 3, (short) 3);
        client.createTopics(Arrays.asList(topic5));

    }

    @GetMapping("/exportCommand")
    public void exportCommand() throws IOException {
        /**
         * 导出语句
         */
        List<String> list = Arrays.asList(
                "mysqldump",
                "-hlocalhost",
                "-P3306",
                "-uroot",
                "-p123456",
                "test",
                "-r",
                "d:/database.sql"
        );
        ProcessBuilder processBuilder = new ProcessBuilder();
        /**
         * 设置导出语句
         */
        processBuilder.command(list);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long startTime = System.currentTimeMillis();
                    int errCode = process.waitFor();
                    long endTime = System.currentTimeMillis();
                    String time = "耗时: "+((endTime-startTime)/1000)+"秒";
                    log.info(time);
                    log.info("command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
                    if (errCode==0){
                        kafkaTemplate.send("topic1","数据库导出完成  "+time);
                    }
                    log.error("数据库导出完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


//        JdbcProperties properties = JdbcProperties.builder()
//                .host("localhost")
//                .port("3306")
//                .username("root")
//                .password("123456")
//                .exportDataBase("test")
//                .exportPath("d:/database.sql")
//                .build();
//        MysqlDumpUntil.export(properties);
    }

    @GetMapping("/importCommand")
    public void importCommand() throws IOException {
        JdbcProperties properties = JdbcProperties.builder()
                .host("localhost")
                .port("3306")
                .username("root")
                .password("123456")
                .importPath("d:/database.sql")
                .importDataBase("sso")
                .build();
        MysqlDumpUntil.importSql(properties);
    }

}
