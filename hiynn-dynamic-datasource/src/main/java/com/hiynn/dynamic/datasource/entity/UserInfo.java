package com.hiynn.dynamic.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author ZhouXiaoLe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user_info")
public class UserInfo implements Serializable {
    @Field("_id")
    private String userId;
    private String userName;
    private String userAccount;
    private String userAddress;
    private Integer userAge;
    private Integer userGender;
    private String userPhoto;
    private String userPassword;
}
