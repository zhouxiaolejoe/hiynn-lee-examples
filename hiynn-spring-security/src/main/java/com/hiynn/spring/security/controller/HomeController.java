package com.hiynn.spring.security.controller;

import com.hiynn.spring.security.pojo.Role;
import com.hiynn.spring.security.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @RequestMapping("/main")
    public String main(Model model) {
        User user =getUserDetails();
        Set set = new HashSet();
         List<Role> roles = user.getRoles();
         for (Role role : roles) {
             set.addAll(role.getAuthoritys());
        }System.out.println(set);
    model.addAttribute("authorities",set);
        
    return "index";
    }
    private  User getUserDetails() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        return (User)auth.getPrincipal();
    }
}
