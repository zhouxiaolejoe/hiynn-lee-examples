package com.hiynn.spring.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.properties
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 15:58
 */
@ConfigurationProperties("people")
@Data
public class CustomPropleProperties {
	private CustomManProperties man = new CustomManProperties();
	private CustomWomanProperties woman = new CustomWomanProperties();
}
