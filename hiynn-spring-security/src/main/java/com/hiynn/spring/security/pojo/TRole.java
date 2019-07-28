package com.hiynn.spring.security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_ROLE")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID",columnDefinition = "int(11) comment '角色id'")
    private Long roleId;
    @Column(name = "ROLE_NAME",columnDefinition = "varchar(25) comment '角色名称'")
    private String roleName;
    /**
     * 懒加载 不会查询role表
     */
    @ManyToMany(mappedBy = "TRoles",fetch = FetchType.LAZY)
    private List<TUser> users;
    /**
     * 急加载 会查询role表
     */
    @ManyToMany(mappedBy = "TRoles",fetch = FetchType.EAGER)
    private List<TPermission> Permission;
}
