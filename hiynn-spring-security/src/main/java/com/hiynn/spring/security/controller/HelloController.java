package com.hiynn.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.controller
 * @Author ZhouXiaoLe
 * @Date 2019-07-23 13:00
 */
@Controller
public class HelloController {
	@RequestMapping("/index")
	public String index(){
		return "/index";
	}
}
