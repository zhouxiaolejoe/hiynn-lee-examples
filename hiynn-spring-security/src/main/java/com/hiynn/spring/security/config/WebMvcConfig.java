//package com.hiynn.spring.security.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.io.IOException;
//
///**
// * @Description
// * @Project hiynn-lee-examples
// * @Package com.hiynn.spring.security.config
// * @Author ZhouXiaoLe
// * @Date 2019-07-23 13:58
// */
//@Slf4j
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//	@Autowired
//	private StaticPagePathFinder staticPagePathFinder;
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		try{
//			for(StaticPagePathFinder.PagePaths pagePaths :staticPagePathFinder.findPath()){
//				String urlPath = pagePaths.getUrlPath();
//				registry.addViewController(urlPath).setViewName(pagePaths.getFilePath());
//				if(!urlPath.isEmpty()){
//					registry.addViewController(urlPath).setViewName(pagePaths.getFilePath());
//				}
//			}
//		}catch(IOException e){
//			log.error("Unable to locate static pages:"+e.getMessage(),e);
//			throw new RuntimeException("Unable to locate static pages:"+e.getMessage(),e);
//		}
//
//	}
//}
