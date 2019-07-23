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
@Table(name = "PERMISSION")
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自动生成
    @Column(name="PERMISSION_ID")
    private Long permissionId;
    @Column(name="PERMISSION_NAME")
    private String permissionName;

    private String url;

    private int pid;

    private String description;
    /**
     * 懒加载   快速查询 不会查询role表
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ROlE_PERMISSION",
            joinColumns = {@JoinColumn(name = "RP_PERMISSION_ID", referencedColumnName = "PERMISSION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "RP_ROLE_ID", referencedColumnName = "ROLE_ID")})
    private List<Role> roles;

}
