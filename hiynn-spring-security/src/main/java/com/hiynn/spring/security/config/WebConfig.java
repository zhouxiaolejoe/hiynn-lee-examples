package com.hiynn.spring.security.config;

import com.hiynn.spring.security.filter.TimeFilter;
import com.hiynn.spring.security.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.config
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 09:47
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {
//	@Autowired
//	TimeInterceptor timeInterceptor;

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
//	}
//
//	/**
//	 * 注册自定义过滤器
//	 */
//	@Bean
//	public FilterRegistrationBean registrationBean(){
//		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//		TimeFilter timeFilter = new TimeFilter();
//		registrationBean.setFilter(timeFilter);
//		registrationBean.addUrlPatterns("/*");
//		return registrationBean;
//	}

}
