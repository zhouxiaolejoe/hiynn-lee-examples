//package com.hiynn.spring.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @Description
// * @Project hiynn-lee-example1
// * @Package com.hiynn.spring.security.config
// * @Author ZhouXiaoLe
// * @Date 2019-07-23 12:29
// */
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//				.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//					.withUser("joe")
//					.password(new BCryptPasswordEncoder().encode("123456"))
//					.roles("ADMIN")
//				.and()
//					.withUser("tom")
//					.password(new BCryptPasswordEncoder().encode("123456"))
//					.roles("ADMIN","DBA");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.httpBasic()
//					.and()
//				.authorizeRequests()
//					.anyRequest()
//				.authenticated();
//	}
//
//
//}
