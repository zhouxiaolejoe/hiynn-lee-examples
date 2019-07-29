package com.hiynn.dynamic.datasource.dto;

import com.hiynn.dynamic.datasource.dto.groupsvalid.GroupVaild;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("com.hiynn.dynamic.datasource.dto.UserDTO")
public class UserDTO {

    @NotNull(message = "id不能为空", groups = GroupVaild.UpdateGroup.class)
    @Min(value = 1, message = "id必须大于0", groups = GroupVaild.UpdateGroup.class)
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @NotEmpty(message = "账号不能为空", groups = GroupVaild.SaveGroup.class)
    @ApiModelProperty(value = "账号")
    private String account;

    @NotEmpty(message = "密码不能为空", groups = GroupVaild.SaveGroup.class)
    @ApiModelProperty(value = "密码")
    private String password;

    @NotEmpty(message = "姓名不能为空", groups = GroupVaild.UpdateGroup.class)
    @ApiModelProperty(value = "姓名")
    private String username;
}
