package com.hiynn.spring.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiynn.untils.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.config.properties
 * @Author ZhouXiaoLe
 * @Date 2019-07-28 11:30
 */
@Component
@Slf4j
public class HiynnAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	ObjectMapper objectMapper;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		log.info("登录成功");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(auth));
	}
}
