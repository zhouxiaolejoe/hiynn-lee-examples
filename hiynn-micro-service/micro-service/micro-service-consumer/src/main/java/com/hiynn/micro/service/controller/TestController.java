package com.hiynn.micro.service.controller;

import com.hiynn.micro.service.api.DTO.TestProducerDTO;
import com.hiynn.micro.service.api.feign.IProducerClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/11/11 10:51
 * @Version 1.0.0
 */
@RestController
@AllArgsConstructor
public class TestController {
    IProducerClient producerClient;
    @GetMapping("/test")
    public TestProducerDTO test(){
        TestProducerDTO testProducerDTO = new TestProducerDTO();
        testProducerDTO.setName("joe");
        System.err.println(testProducerDTO);
        return testProducerDTO;
    }

    @GetMapping("/test1/{id}")
    public TestProducerDTO test1(@PathVariable("id")String id){
        TestProducerDTO testProducerDTO = new TestProducerDTO();
        testProducerDTO.setName("joe");
        System.err.println(testProducerDTO);
        System.err.println(id);
        return testProducerDTO;
    }
    @GetMapping("/test3")
    public Map test3(@RequestParam("name")String name, @RequestParam("pwd")String pwd){

        return new HashMap<String,Object>(2){{
            put("name",name);
            put("pwd",pwd);
        }};
    }
    @GetMapping("/test2")
    public Map test2(@RequestBody Map map){
        return map;
    }
}
