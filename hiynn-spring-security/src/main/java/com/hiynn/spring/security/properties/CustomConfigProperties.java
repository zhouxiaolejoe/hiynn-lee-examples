package com.hiynn.spring.security.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.properties
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 15:57
 */
@Configuration
@EnableConfigurationProperties(CustomPropleProperties.class)
public class CustomConfigProperties {
}
