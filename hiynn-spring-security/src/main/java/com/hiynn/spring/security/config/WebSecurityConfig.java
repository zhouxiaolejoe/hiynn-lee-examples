package com.hiynn.spring.security.config;

import com.hiynn.spring.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.config
 * @Author ZhouXiaoLe
 * @Date 2019-07-23 12:29
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	UserDetailsService customUserService() {
		return new UserDetailsServiceImpl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
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
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/error","/success").permitAll()
				.antMatchers("/add","/find").hasRole("ADMIN")
				.antMatchers("/update","/delete").access("hasRole('ADMIN') and hasRole('DBA')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/userlogin")//登录页面
				.loginProcessingUrl("/custom/login")//post 登录地址
				.successForwardUrl("/success")//登录成功跳转页面
				.permitAll()
				.failureUrl("/error")
				.failureForwardUrl("/userlogin")
//				.and()
//			.logout()
//				.logoutUrl("/my/logout")
//				.logoutSuccessUrl("/my/index")
		;
	}

	public static void main(String[] args) {
		String encode = new BCryptPasswordEncoder().encode("123456");
		System.err.println(encode);
	}
}
