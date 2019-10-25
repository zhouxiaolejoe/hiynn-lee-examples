package com.hiynn.spring.quartz.controller;

import com.hiynn.spring.quartz.untils.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ActiveMQController
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/19 17:50
 * @Version 1.0.0
 */
@RestController
@Slf4j
public class ActiveController {

    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/sendMessage")
    @ApiOperation(value = "发送消息", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    public ResultBuilder getSendMessageInfo(@RequestBody TestDto testDto){
//        log.info("接受到读取文件的参数："+testDto.getFileName());
//        Map<String, String> map = Maps.newHashMapWithExpectedSize(2);
//        map.put("fileName",testDto.getFileName());
//        map.put("queue","File.Transport");
//        String param = FastJsonUtils.getBeanToJson(map);
//        jmsTemplate.convertAndSend("send.file",param);
        jmsTemplate.convertAndSend("database.export","11111");



        return ResultBuilder.success();
    }
}


@Data
class TestDto{
    private String fileName;
}