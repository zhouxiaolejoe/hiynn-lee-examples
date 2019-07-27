package com.hiynn.spring.security.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.filter
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 09:21
 */
@Slf4j
public class TimeFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		log.info("time filter start");
		long start = System.currentTimeMillis();
		filterChain.doFilter(servletRequest, servletResponse);
		log.info(String.format("接口耗时:%s",System.currentTimeMillis()-start));
		log.info("time filter finish");
	}

}
