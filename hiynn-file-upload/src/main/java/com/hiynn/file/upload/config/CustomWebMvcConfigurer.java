package com.hiynn.file.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Value("${upload.file.path}")
    public String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射本地资源
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + uploadPath);
    }
}
