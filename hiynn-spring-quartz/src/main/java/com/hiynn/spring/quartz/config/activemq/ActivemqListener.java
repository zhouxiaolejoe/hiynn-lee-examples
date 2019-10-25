package com.hiynn.spring.quartz.config.activemq;

import com.google.common.collect.Maps;
import com.hiynn.spring.quartz.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;
import org.apache.activemq.Message;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @ClassName ActivemqListener
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/20 9:23
 * @Version 1.0.0
 */
@Component
@Slf4j
public class ActivemqListener {

    private static final String FILE_NAME = "FILE.NAME";
    private static final String FILE_SIZE = "FILE.SIZE";

    @Value("${file.base.import-path}")
    private String importPath;
    @Value("${file.base.export-path}")
    private String exportPath;
    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "File.Transport")
    public void receiveMessage(Message message){
        if (message instanceof BlobMessage) {
            BlobMessage blobMessage = (BlobMessage) message;
            String fileName =null;
            try {
                fileName = blobMessage.getStringProperty(FILE_NAME);
                log.info("文件接收请求处理：" + fileName + "，文件大小：" + blobMessage.getLongProperty(FILE_SIZE)+ " 字节");
                File file = new File(importPath+fileName);
                OutputStream os = new FileOutputStream(file);
                    log.info("开始接收文件：" + fileName);
                    InputStream inputStream = blobMessage.getInputStream();
                    byte[] buff = new byte[256];
                    int len = 0;
                    while ((len = inputStream.read(buff)) > 0) {
                        os.write(buff, 0, len);
                    }
                    os.close();
                    log.info("完成文件接收：" + fileName);
                jmsTemplate.convertAndSend("database.import",fileName);
            } catch (Exception e) {
                Map<String, Object> errorMap = Maps.newHashMapWithExpectedSize(2);
                errorMap.put("message",e.getMessage());
                errorMap.put("fileName",fileName);
                errorMap.put("queue",null);
                String errorJson = FastJsonUtils.getBeanToJson(errorMap);
                jmsTemplate.convertAndSend("file.error",errorJson);
            }
        }
    }

    @JmsListener(destination = "send.file")
    public void sendMessage(String message){
        Map<String, String> map = FastJsonUtils.getJsonToMap(message);
        String fileName = map.get("fileName");
        String queue = map.get("queue");
        try {
            File file = new File(exportPath+fileName);
            log.info("文件发送请求处理：" + file.getName() + "，文件大小：" + file.length()
                    + " 字节");
            log.info("开始发送文件：" + file.getName());

            jmsTemplate.send(queue,session -> {
                    ActiveMQSession session1 = (ActiveMQSession) session;
                    BlobMessage blobMessage = session1.createBlobMessage(file);
                    blobMessage.setStringProperty(FILE_NAME, file.getName());
                    blobMessage.setLongProperty(FILE_SIZE, file.length());
                    return blobMessage;
                });
            /**
             *  删除本地文件
             */
            FileUtils.deleteQuietly(file);
            log.info("完成文件发送：" + file.getName());
        } catch (Exception e) {
            Map<String, Object> errorMap = Maps.newHashMapWithExpectedSize(2);
            errorMap.put("message",e.getMessage());
            errorMap.put("fileName",fileName);
            errorMap.put("queue",queue);
            String errorJson = FastJsonUtils.getBeanToJson(errorMap);
            jmsTemplate.convertAndSend("file.error",errorJson);
        }
    }

    @JmsListener(destination = "file.error")
    public void errorMessage(String message){
        //异常处理
        System.err.println(message);
    }
}
