package com.hiynn.spring.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hiynn.spring.mybatis.plus.entity.DataSubscribeInfo;
import com.hiynn.spring.mybatis.plus.mapper.DataSubscribeInfoMapper;
import com.hiynn.spring.mybatis.plus.service.IDataSubscribeInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据订阅信息表 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-10-30
 */
@Service
public class DataSubscribeInfoServiceImpl extends ServiceImpl<DataSubscribeInfoMapper, DataSubscribeInfo> implements IDataSubscribeInfoService {

}
