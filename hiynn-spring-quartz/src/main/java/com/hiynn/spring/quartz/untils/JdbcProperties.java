package com.hiynn.spring.quartz.untils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName JdbcProperties
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/5 20:07
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JdbcProperties implements Serializable {
    private String username;
    private String password;
    private String host;
    private String port;
    private String exportDataBase;
    private String exportPath;
    private String importPath;
    private String importDataBase;

}
