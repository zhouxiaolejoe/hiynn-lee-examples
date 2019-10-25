package com.hiynn.spring.mybatis.plus.lxp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hiynn.spring.mybatis.plus.lxp.entity.DataServiceInfo;
import com.hiynn.spring.mybatis.plus.lxp.entity.QueryDto;

import java.util.Map;

/**
 * <p>
 * 数据服务详情 服务类
 * </p>
 *
 * @author joe
 * @since 2019-10-21
 */
public interface IDataServiceInfoService extends IService<DataServiceInfo> {
    /**
    * @Description 访问控制
    * @Method getDataServiceInfo
    * @Param id
    * @Param map
    * @return void
    * @Author ZhouXiaoLe
    * @Date  2019-10-21  13:33:42
    **/
    public  Map<String, Object> getDataServiceInfo(Integer id, QueryDto queryDto);

}
