package com.hiynn.spring.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DatacenterCatalogShow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 数据目录
     */
    private String dataCatalog;

    /**
     * 目录id
     */
    private Integer catalogId;

    /**
     * 数据量
     */
    private Long dataNumber;

    /**
     * 权限状态（0没权限1有权限）
     */
    private Integer authStatus;

    /**
     * 收藏状态（0未收藏1已收藏）
     */
    private Integer collectStatus;

    /**
     * 发布时间
     */
    private LocalDateTime issueTime;


}
