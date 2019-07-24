//package com.hiynn.spring.security.pojo;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "ROLE")
//@Data
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ROLE_ID")
//    private Long roleId;
//    @Column(name = "NAME",length = 25)
//    private String name;
//    /**
//     * 懒加载 不会查询role表
//     */
//    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    private List<User> users;
//    /**
//     * 急加载 会查询role表
//     */
//    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
//    private List<Permission> Authoritys;
//}
