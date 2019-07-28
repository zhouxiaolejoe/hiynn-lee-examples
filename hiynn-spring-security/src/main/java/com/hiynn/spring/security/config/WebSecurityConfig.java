package com.hiynn.spring.security.config;

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
				.loginPage("/customLogin")
					.loginProcessingUrl("/user/login")
					.and()
				.authorizeRequests()
					.antMatchers("/customLogin").permitAll()
					.anyRequest()
					.authenticated()
					.and()
				.csrf()
					.disable()
					;
	}

}
