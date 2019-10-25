package com.hiynn.spring.quartz.controller;

import com.hiynn.spring.quartz.dto.TimeDTO;
import com.hiynn.spring.quartz.untils.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestTimeController
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/9/3 10:04
 * @Version 1.0.0
 */
@RestController
@Api(tags = "测试时间传递")
public class TestTimeController {

    @PostMapping("/testTimeTransmit")
    @ApiOperation(value = "测试时间传递", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    public ResultBuilder testTimeTransmit(@RequestBody TimeDTO timeDTO){
        System.err.println(timeDTO);
        return ResultBuilder.success(timeDTO);
    }

}
