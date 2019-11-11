package com.hiynn.spring.mybatis.plus.lxp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 实例名
     */
    private String instance;

    /**
     * 数据类型 0原始数据，1标准数据
     */
    @TableField("dataType")
    private Integer dataType;

    /**
     * 数据库类型（1.Mysql 2.Oracle 3.Hive 4.HBase）
     */
    private Integer databaseType;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * ip地址
     */
    private String databaseIp;

    /**
     * 端口号
     */
    private String databasePort;


}
