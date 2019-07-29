package com.hiynn.dynamic.datasource.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@ApiModel(value = "com.hiynn.dynamic.datasource.entity.TRole")
@NoArgsConstructor
@AllArgsConstructor
public class TRole implements Serializable {
    private Integer id;
    private String name;
}
