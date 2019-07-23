package com.hiynn.spring.security.dao;

import com.hiynn.spring.security.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    
    public User findByUsername(String name);
}