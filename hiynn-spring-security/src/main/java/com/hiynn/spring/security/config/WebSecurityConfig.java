package com.hiynn.spring.security.config;

import com.hiynn.spring.security.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.config
 * @Author ZhouXiaoLe
 * @Date 2019-07-23 12:29
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	SecurityProperties securityProperties;

	@Autowired
	HiynnAuthenticationSuccessHandler hiynnAuthenticationSuccessHandler;

	/**
	 * 创建 spring security 密码策略
	 */
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//				.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//					.withUser("joe")
//					.password(new BCryptPasswordEncoder().encode("123456"))
//					.TRoles("ADMIN")
//				.and()
//					.withUser("tom")
//					.password(new BCryptPasswordEncoder().encode("123456"))
//					.TRoles("ADMIN","DBA");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
				.loginPage("/auth/require")//自定义登录页
					.loginProcessingUrl("/auth/from")//默认为login的post请求
					.successHandler(hiynnAuthenticationSuccessHandler)
					.and()
				.authorizeRequests()
					.antMatchers("/auth/require",
							securityProperties.getBrowser().getLoginPage()).permitAll()
					.anyRequest()
					.authenticated()
					.and()
				.csrf()
					.disable()
					;
	}

}
