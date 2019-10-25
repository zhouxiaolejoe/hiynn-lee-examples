package com.hiynn.spring.mybatis.plus.lxp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataResourceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("DATASOURCEID")
    private String datasourceid;

    /**
     * ip地址
     */
    @TableField("IP")
    private String ip;

    /**
     * 端口号
     */
    @TableField("PORT")
    private String port;

    /**
     * 数据库名
     */
    @TableField("DBNAME")
    private String dbname;

    /**
     * sid
     */
    @TableField("INAME")
    private String iname;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 数据库类型
     */
    @TableField("DBTYPE")
    private String dbtype;

    @TableField("CREATETIME")
    private LocalDateTime createtime;


}
