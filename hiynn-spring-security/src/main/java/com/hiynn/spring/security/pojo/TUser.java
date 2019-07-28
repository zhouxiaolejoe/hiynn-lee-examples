package com.hiynn.spring.security.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_USER")
public class TUser implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID",columnDefinition = "int(11) comment '用户id'")
    private Long userId;

    @Column(name = "USER_NAME",columnDefinition = "varchar(50) comment '用户名'", unique = true)
    private String username;

    @Column(name = "PASSWORD",columnDefinition = "varchar(100) comment '密码'")
    private String password;

    @Column(name = "FIRST_NAME",columnDefinition = "varchar(50) comment '姓'")
    private String firstName;

    @Column(name = "LAST_NAME",columnDefinition = "varchar(50) comment '名'")
    private String lastName;

    @Column(name = "UPDATE_TIME" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "IS_ACCOUNT_NON_LOCKED",columnDefinition = "int(2) comment '账号是否锁定'")
    private Integer isAccountNonLocked;

    @Column(name = "IS_ACCOUNT_NON_EXPIRED",columnDefinition = "int(2) comment '账号是否过期'")
    private Integer isAccountNonExpired;

    @Column(name = "IS_CREDENTIALS_NON_EXPIRED",columnDefinition = "int(2) comment '凭证是否过期'")
    private Integer isCredentialsNonExpired;

    @Column(name = "IS_ENABLED",columnDefinition = "int(2) comment '账号是否启用'")
    private Integer isEnabled;

    @Column(name = "IS_DELETE",columnDefinition = "int(2) comment '账号是否删除'")
    private Integer isDELETE;

    /**
     * 急加载 会查询role表
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_USER_ROlE",
            joinColumns = {@JoinColumn(name = "T_USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "T_ROLE_ID", referencedColumnName = "ROLE_ID")})
    private List<TRole> roles;

    /**
    * @Description 实现UserDetails 重写的方法
    * @Method getAuthorities
    * @param
    * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
    * @throws
    * @Author ZhouXiaoLe
    * @Date  2019-07-23  04:14:38
    **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<GrantedAuthority> auths = new ArrayList<>();
        List<TRole> TRoles = this.getRoles();
        for (TRole TRole : TRoles) {
            for (TPermission aurh : TRole.getPermissions()) {
                auths.add(new SimpleGrantedAuthority(aurh.getPermissionName()));
            }
        }
        return auths;
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}