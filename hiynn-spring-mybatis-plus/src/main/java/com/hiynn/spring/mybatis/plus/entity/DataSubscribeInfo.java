package com.hiynn.spring.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据订阅信息表
 * </p>
 *
 * @author joe
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class DataSubscribeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订阅交换数据到本地资源目录下
     */
    private Integer storageDirectoryId;

    /**
     * 权限周期
     */
    private String authPeriod;

    /**
     * 权限状态
     */
    private Integer authStatus;

    /**
     * 记录订阅了哪个数据中心节点的数据
     */
    private Integer subscribeNodeId;

    /**
     * 具体记录订阅的源节点上的数据id
     */
    private Integer catalogShowId;

    /**
     * 一次同步、定时采集、实时采集三种策略
     */
    private Integer subscribeItemId;

    /**
     * 定时采集策略时，设置定时周期
     */
    private String regularPeriod;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime applyTime;


}
