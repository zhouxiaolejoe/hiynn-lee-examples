package com.hiynn.spring.mybatis.plus.lxp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据集合信息表
 * </p>
 *
 * @author joe
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DatasetInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 表连接方式（1内连接，2左连接，3右连接，4全连接）
     */
    private Integer joinType;

    /**
     * 存储类型（1基本2高级）
     */
    private Integer storageType;

    /**
     * 两表连接信息，json格式
     */
    private String joinInfo;

    /**
     * 保存高级方式的sql语句，或者基本方式解析的sql语句
     */
    private String sqlInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除（0未删除1已删除）
     */
    private Integer isDelete;

    /**
     * 数据描述
     */
    private String dataDescription;

    /**
     * 目录id
     */
    private Integer catalogId;

    /**
     * 数据名称
     */
    private String dataName;


}
