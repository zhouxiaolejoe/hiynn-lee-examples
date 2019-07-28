package com.hiynn.spring.security.dao;

import com.hiynn.spring.security.pojo.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<TUser, Long> {

    TUser findByUsername(String name);
    TUser findByUserId(Long id);
}