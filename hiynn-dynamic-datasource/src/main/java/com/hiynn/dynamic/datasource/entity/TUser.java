package com.hiynn.dynamic.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ApiModel("com.hiynn.dynamic.datasource.entity.TUser")
@NoArgsConstructor
@AllArgsConstructor
public class TUser implements Serializable {
	private Integer id;
	private String account;
	@JsonIgnore
	private String password;
	@JsonProperty("name")
	private String username;
	@JsonProperty("time")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date regTime;
}
