package com.hiynn.spring.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TUser {


    private Long userId;


    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Date updateTime;

    private Integer isAccountNonLocked;

    private Integer isAccountNonExpired;

    private Integer isCredentialsNonExpired;

    private Integer isEnabled;

    private Integer isDELETE;

}