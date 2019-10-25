package com.hiynn.spring.mybatis.plus.lxp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据服务详情
 * </p>
 *
 * @author joe
 * @since 2019-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataServiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务id
     */
    @TableField("SERVICE_ID")
    private String serviceId;

    /**
     * 数据源id
     */
    @TableField("DATASOURCEID")
    private String datasourceid;

    @TableField("DATA_TABLE_NAME")
    private String dataTableName;

    /**
     * 元数据id
     */
    @TableField("SOURCEDATAID")
    private Integer sourcedataid;

    /**
     * 请求类型
     */
    @TableField("REQUEST_TYPE")
    private String requestType;

    /**
     * 数据服务请求路径
     */
    @TableField("DATA_SERVICE_PATH")
    private String dataServicePath;

    /**
     * 请求所需参数json
     */
    @TableField("REQUSET_PARAM")
    private String requsetParam;

    /**
     * 服务注册状态(1:注册成功 ,0:未注册)
     */
    @TableField("REGISTERSTATE")
    private String registerstate;

    /**
     * 用户id
     */
    @TableField("USERID")
    private String userid;

    /**
     * 注册api_id
     */
    @TableField("SERVICE_API_ID")
    private Integer serviceApiId;

    /**
     * 创建时间
     */
    @TableField("CREATETIMR")
    private LocalDateTime createtimr;


}
