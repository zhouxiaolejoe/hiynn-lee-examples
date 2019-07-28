package com.hiynn.spring.security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_PERMISSION")
public class TPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PERMISSION_ID",columnDefinition = "int(11) comment '权限id'")
    private Long permissionId;

    @Column(name="PERMISSION_NAME",columnDefinition = "varchar(25) comment '权限名称'")
    private String permissionName;

    @Column(name="URL",columnDefinition = "varchar(50) comment '权限url'")
    private String url;

    @Column(name="MENU",columnDefinition = "varchar(25) comment '前端权限菜单名'")
    private String meau;

    @Column(name="DESCRIPTION",columnDefinition = "varchar(200) comment '权限描述'")
    private String description;
    /**
     * 懒加载   快速查询 不会查询role表
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_ROlE_PERMISSION",
            joinColumns = {@JoinColumn(name = "T_PERMISSION_ID", referencedColumnName = "PERMISSION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "T_ROLE_ID", referencedColumnName = "ROLE_ID")})
    private List<TRole> roles;

}
