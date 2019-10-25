/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.hiynn.spring.mybatis.plus.lxp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 描述：查询dto
 *
 * @author lxp
 * @date 2019/10/12 17:36
 * @modified By
 * @since 1.0
 */
@Data
public class QueryDto {

    /**
     * 条件
     * 字段 = 值
     */
    private String condition;

    /**
     * 是否查询总数
     */
    @JsonProperty("total")
    private boolean total;

    /**
     * 是否分页显示
     */
    @JsonProperty("paging")
    private boolean paging;

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页数据量
     */
    private Integer pageSize;

}