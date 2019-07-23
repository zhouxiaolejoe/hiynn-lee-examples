package com.hiynn.dynamic.datasource.controller;

import com.hiynn.dynamic.datasource.dto.UserDTO;
import com.hiynn.dynamic.datasource.dto.groupsvalid.GroupVaild;
import com.hiynn.dynamic.datasource.entity.TRole;
import com.hiynn.dynamic.datasource.entity.TUser;
import com.hiynn.dynamic.datasource.generator.entity.TUserInfo;
import com.hiynn.dynamic.datasource.generator.service.TUserInfoService;
import com.hiynn.dynamic.datasource.okhttp3.OkHttpUtil;
import com.hiynn.dynamic.datasource.service.TestService;
import com.hiynn.dynamic.datasource.untils.FastJsonUtils;
import com.hiynn.dynamic.datasource.untils.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* @Description
* @Author ZhouXiaoLe
* @Date  2019/7/17  15:14
* @Param 
* @return 
**/
@Slf4j
@Api(tags = "swagger测试")
@RestController
public class TestController {
	@Autowired
	TestService testService;
	@Autowired
	TUserInfoService userInfoService;

	@GetMapping("/testFindUser/{id}")
	@ApiOperation(value = "测试查找用户",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
	})
	public ResultBuilder<TUser> testFindUser(@PathVariable("id") Integer id){
		TUserInfo userInfo = userInfoService.selectByPrimaryKey(id);
		log.info(FastJsonUtils.getBeanToJson(userInfo));
		return ResultBuilder.success(testService.findUserById(id));
	}

	@GetMapping("/testFindRole/{id}")
	@ApiOperation(value = "测试查找角色",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
	})
	public ResultBuilder<TRole> testFindRole(@PathVariable("id") Integer id, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			session.setAttribute("user", "zhangsan");
			log.info("The session does not exist");
		} else {
			log.info("The session is"+"["+session.getAttribute("user")+"]");
		}
		return ResultBuilder.success(testService.findRoleById(id));
	}

	@GetMapping("/insertRole")
	@ApiOperation(value = "测试新增角色",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "GET")
	public ResultBuilder insertRole(){
		testService.insertRole();
		return ResultBuilder.success();
	}

	@PostMapping("/insertUser")
	@ApiOperation(value = "测试新增用户",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "POST")
	public ResultBuilder insertUser(@Validated(value = GroupVaild.SaveGroup.class)@RequestBody UserDTO userDTO){
		return ResultBuilder.success(testService.insertUser(userDTO));
	}
	@PutMapping("/updatetUser")
	@ApiOperation(value = "测试修改用户",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "PUT")
	public ResultBuilder updateUser(@Validated(value = GroupVaild.UpdateGroup.class)@RequestBody UserDTO userDTO){
		return ResultBuilder.success(testService.updatetUser(userDTO));
	}

	@GetMapping("/testUrlParam/{id}")
	@ApiOperation(value = "测试url传参数",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path"),
			@ApiImplicitParam(name = "date", value = "日期", required = false, dataType = "string", paramType = "query")})
	public ResultBuilder<List> testUrlParam(@PathVariable("id") Integer id, String date){
		return ResultBuilder.success(Arrays.asList(id,date));
	}

	@PostMapping("/testJsonParam")
	@ApiOperation(value = "测试json传参数",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "POST")
	public ResultBuilder<List<TUser>> testJsonParam(@Validated(value = GroupVaild.SaveGroup.class)@RequestBody UserDTO userDTO){
		TUser user = TUser.builder().build();
		BeanUtils.copyProperties(userDTO, user);
		return ResultBuilder.success(Arrays.asList(user));
	}
	
	@GetMapping("/testOkhttp3Get")
	@ApiOperation(value = "测试Okhttp3Get",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "GET")
	public ResultBuilder testOdetta3Get(String id1,String name){

		String result = OkHttpUtil.getStringFromServer("http://localhost:8080/dynamic_db/testFindRole/1");
		Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
		log.info(FastJsonUtils.getBeanToJson(resultMap));
		return ResultBuilder.success(resultMap.get("result"));
	}

	@PostMapping("/testOkhttp3Post")
	@ApiOperation(value = "测试Okhttp3Post",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "POST")
	public ResultBuilder testOkhttp3Post(@Validated(value = GroupVaild.SaveGroup.class)@RequestBody UserDTO parameters){
//		Map<String, String> parameters = new HashMap<String, String>() {
//			{
//				put("account", "joe");
//				put("password", "123456");
//				put("username", "zxl");
//			}
//		};
		String result = OkHttpUtil.getStringFromServerByPost("http://localhost:8080/dynamic_db/testJsonParam", FastJsonUtils.getBeanToJson(parameters));
		Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
		log.info(FastJsonUtils.getBeanToJson(resultMap));
		return ResultBuilder.success(resultMap.get("result"));
	}
}
