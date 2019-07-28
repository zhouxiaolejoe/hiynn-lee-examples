package com.hiynn.spring.security.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.config.properties
 * @Author ZhouXiaoLe
 * @Date 2019-07-28 10:48
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCroeConfig {
}
