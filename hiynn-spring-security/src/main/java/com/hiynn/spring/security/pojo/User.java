//package com.hiynn.spring.security.pojo;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//@Data
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "USER_INFO")
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "USER_ID")
//    private Long userId;
//
//    @Column(name = "USER_NAME", length = 50, unique = true)
//    private String username;
//
//    @Column(name = "PASSWORD", length = 100)
//    private String password;
//
//    @Column(name = "FIRST_NAME", length = 50)
//    private String firstName;
//
//    @Column(name = "LAST_NAME", length = 50)
//    private String lastName;
//
//    @Column(name = "STATUS", length = 2)
//    private Integer status;
//
//    @Column(name = "UPDATE_TIME")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updateTime;
//
//    /**
//     * 急加载 会查询role表
//     */
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "USER_ROlE",
//            joinColumns = {@JoinColumn(name = "UR_USER_ID", referencedColumnName = "USER_ID")},
//            inverseJoinColumns = {@JoinColumn(name = "UR_ROLE_ID", referencedColumnName = "ROLE_ID")})
//    private List<Role> roles;
//
//    /**
//    * @Description 实现UserDetails 重写的方法
//    * @Method getAuthorities
//    * @param
//    * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
//    * @throws
//    * @Author ZhouXiaoLe
//    * @Date  2019-07-23  04:14:38
//    **/
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // TODO Auto-generated method stub
//        List<GrantedAuthority> auths = new ArrayList<>();
//        List<Role> roles = this.getRoles();
//        for (Role role : roles) {
//            for (Permission aurh : role.getAuthoritys()) {
//                auths.add(new SimpleGrantedAuthority(aurh.getPermissionName()));
//            }
//        }
//        return auths;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//}