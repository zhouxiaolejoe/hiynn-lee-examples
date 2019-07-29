package com.hiynn.spring.security.config.properties;

import lombok.Data;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.config.properties
 * @Author ZhouXiaoLe
 * @Date 2019-07-28 10:45
 */
@Data
public class BrowserProperties {
    private String loginPage = "/customLogin";
}
