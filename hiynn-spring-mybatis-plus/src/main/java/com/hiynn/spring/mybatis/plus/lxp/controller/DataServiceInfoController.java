package com.hiynn.spring.mybatis.plus.lxp.controller;


import com.hiynn.spring.mybatis.plus.lxp.entity.QueryDto;
import com.hiynn.spring.mybatis.plus.lxp.service.IDataResourceDetailService;
import com.hiynn.spring.mybatis.plus.lxp.service.IDataServiceInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 数据服务详情 前端控制器
 * </p>
 *
 * @author joe
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/data-service-info")
@AllArgsConstructor
public class DataServiceInfoController {
    IDataServiceInfoService dataServiceInfoService;
    IDataResourceDetailService dataResourceDetailService;
    /**
    * @Description
    * @Method getDataServiceInfo
    * @Param id
    * @return void
    * @Author ZhouXiaoLe
    * @Date  2019-10-21  13:19:18
    **/
    @PostMapping("/api/service/{id}")
    public Map<String, Object> getDataServiceInfo(@PathVariable("id")Integer id, @RequestBody QueryDto queryDto){
        Map<String, Object> dataServiceInfo = dataServiceInfoService.getDataServiceInfo(id, queryDto);
        return dataServiceInfo;
    }



}
