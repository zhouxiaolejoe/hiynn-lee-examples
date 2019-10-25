package com.hiynn.spring.mybatis.plus.lxp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作记录
 * </p>
 *
 * @author joe
 * @since 2019-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataOperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 模型名
     */
    @TableField("MODEL_ID")
    private String modelId;

    /**
     * 访问IP
     */
    @TableField("APP_IP")
    private String appIp;

    /**
     * 操作类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 访问时间
     */
    @TableField("TIME")
    private LocalDateTime time;

    /**
     * 操作返回码
     */
    @TableField("RESULT_CODE")
    private String resultCode;

    /**
     * 条件语句
     */
    @TableField("REQUEST_CONDITION")
    private String requestCondition;

    /**
     * 耗时
     */
    @TableField("TIME_CONSUMING")
    private String timeConsuming;

    /**
     * 请求url
     */
    @TableField("ACCESSPATH")
    private String accesspath;


}
