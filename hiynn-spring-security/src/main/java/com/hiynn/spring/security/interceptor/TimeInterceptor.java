package com.hiynn.spring.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.interceptor
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 09:29
 */
@Slf4j
//@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.warn("preHandle");log.warn(((HandlerMethod)handler).getBean().getClass().getName());
		log.warn(((HandlerMethod)handler).getMethod().getName());
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.warn("postHandle");
		long startTime = (Long)request.getAttribute("startTime");
		log.warn(String.format("time interceptor 耗时:%s", System.currentTimeMillis()-startTime));


	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.warn("afterCompletion");
		long startTime = (Long)request.getAttribute("startTime");
		log.warn(String.format("time interceptor 耗时:%s", System.currentTimeMillis()-startTime));
		log.error(ex==null?null:ex.getMessage());
	}
}
