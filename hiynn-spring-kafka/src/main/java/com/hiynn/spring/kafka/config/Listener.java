package com.hiynn.spring.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @ClassName Listener
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/5 10:58
 * @Version 1.0.0
 */
@Slf4j
@Component
public class
Listener {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @KafkaListener(id = "foo3",topics = {"topic3"})
    public  void listen3(String data){
        log.error(data);
    }

    @KafkaListener(id = "foo",topics = {"topic1"})
    public  void listen(String data){
        log.error(data);
        kafkaTemplate.send("topic2","启动导入");
    }
    @Autowired
    Executor taskExecutor;
    @KafkaListener(id = "foo1",topics = {"topic2"})
    public  void listen2(String data){
        log.error(data);
        try {
            List<String> list02 = Arrays.asList(
                    "mysql",
                    "-h192.168.238.101",
                    "-P3306",
                    "-uroot",
                    "-p123456"
            );
            ProcessBuilder processBuilder1 = new ProcessBuilder();
            processBuilder1.command(list02);
            Process process02 = processBuilder1.start();

            String switchCommand = String.format("use %s","test1");

            String importCommand = String.format("source %s","d:/database.sql");

            OutputStream outputStream = process02.getOutputStream();

            OutputStreamWriter writer = new OutputStreamWriter(outputStream);

            writer.write(switchCommand + "\r\n" + importCommand);

            writer.flush();

            writer.close();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long startTime1 = System.currentTimeMillis();
                    int errCode02 = 0;
                    try {
                        errCode02 = process02.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("command executed, any errors? " + (errCode02 == 0 ? "No" : "Yes"));
                    long endTime1 = System.currentTimeMillis();
                    String time1="耗时: "+((endTime1-startTime1)/1000)+"秒";
                    log.info(time1);
                    if(errCode02==0){
                        kafkaTemplate.send("topic3","数据库导入完成  "+time1);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
