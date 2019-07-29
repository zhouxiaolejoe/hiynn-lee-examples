package com.hiynn.spring.security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.config.properties
 * @Author ZhouXiaoLe
 * @Date 2019-07-28 10:44
 */
@ConfigurationProperties(prefix = "hiynn.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
