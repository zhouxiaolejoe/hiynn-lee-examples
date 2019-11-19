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
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Author ZhouXiaoLe
 * @Date 2019/7/17  15:14
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
    @ApiOperation(value = "测试查找用户", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    })
    public ResultBuilder<TUser> testFindUser(@PathVariable("id") Integer id) {
        TUserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        log.info(FastJsonUtils.getBeanToJson(userInfo));
        return ResultBuilder.success(testService.findUserById(id));
    }

    @GetMapping("/testFindRole/{id}")
    @ApiOperation(value = "测试查找角色", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    })
    public ResultBuilder<TRole> testFindRole(@PathVariable("id") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", "zhangsan");
            log.info("The session does not exist");
        } else {
            log.info("The session is" + "[" + session.getAttribute("user") + "]");
        }
        return ResultBuilder.success(testService.findRoleById(id));
    }

    @GetMapping("/insertRole")
    @ApiOperation(value = "测试新增角色", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    public ResultBuilder insertRole() {
        testService.insertRole();
        return ResultBuilder.success();
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "测试新增用户", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    public ResultBuilder insertUser(@Validated(value = GroupVaild.SaveGroup.class) @RequestBody UserDTO userDTO) {
        return ResultBuilder.success(testService.insertUser(userDTO));
    }

    @PutMapping("/updatetUser")
    @ApiOperation(value = "测试修改用户", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT")
    public ResultBuilder updateUser(@Validated(value = GroupVaild.UpdateGroup.class) @RequestBody UserDTO userDTO) {
        return ResultBuilder.success(testService.updatetUser(userDTO));
    }

    @GetMapping("/testUrlParam/{id}")
    @ApiOperation(value = "测试url传参数", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "date", value = "日期", required = false, dataType = "string", paramType = "query")})
    public ResultBuilder<List> testUrlParam(@PathVariable("id") Integer id, String date) {
        return ResultBuilder.success(Arrays.asList(id, date));
    }

    @PostMapping(value = "/testJsonParam")
    @ApiOperation(value = "测试json传参数", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    public ResultBuilder<List<TUser>> testJsonParam(@Validated(value = GroupVaild.SaveGroup.class) @RequestBody UserDTO userDTO) {
        TUser user = TUser.builder().build();
        BeanUtils.copyProperties(userDTO, user);
        return ResultBuilder.success(Arrays.asList(user));
    }

    @GetMapping("/testOkhttp3Get")
    @ApiOperation(value = "测试Okhttp3Get", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    public ResultBuilder testOdetta3Get(String id1, String name) {

        String result = OkHttpUtil.getStringFromServer("http://localhost:8080/dynamic_db/testFindRole/1");
        Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
        log.info(FastJsonUtils.getBeanToJson(resultMap));
        return ResultBuilder.success(resultMap.get("result"));
    }

    @PostMapping("/testOkhttp3Post")
    @ApiOperation(value = "测试Okhttp3Post", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    public ResultBuilder testOkhttp3Post(@Validated(value = GroupVaild.SaveGroup.class) @RequestBody UserDTO parameters) {
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


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String, Object> DataxJob = new HashMap<>(2);
        List<Map<String, Object>> contentList = new ArrayList<>();
        Map<String, Object> readerDB = new HashMap<>(2);
        readerDB.put("name", "mysqlreader");
        Map<String, Object> parameter = new HashMap<>(2);
        parameter.put("username", "root");
        parameter.put("password", "123456");
        List<Object> column = Arrays.asList("id","first_name", "last_name", "sex", "score", "copy_id", "is_delete");
        List<Object> connection = new ArrayList<>();
        Map<String, Object> jdbcConnection = new HashMap<>(2);
        jdbcConnection.put("jdbcUrl",Arrays.asList("jdbc:mysql://127.0.0.1:3306/test"));
        jdbcConnection.put("table",Arrays.asList("user100w"));
        connection.add(jdbcConnection);
        parameter.put("column",column);
        parameter.put("connection",connection);
        readerDB.put("parameter",parameter);
        Map<String, Object> readerAndWriterDB = new HashMap<>(2);
        readerAndWriterDB.put("reader",readerDB);
        contentList.add(readerAndWriterDB);
        DataxJob.put("content",contentList);
        Map<String, Object> writerDB = new HashMap<>(2);
        Map<String, Object> writerParameter = new HashMap<>(2);
        List<Object> writerColumn = Arrays.asList("id","first_name", "last_name", "sex", "score", "copy_id", "is_delete");
        Map<String, Object> writerJdbcConnection = new HashMap<>(2);
        writerJdbcConnection.put("jdbcUrl","jdbc:mysql://127.0.0.1:3306/test1");
        writerJdbcConnection.put("table",Arrays.asList("user100w"));
        writerParameter.put("connection",Arrays.asList(writerJdbcConnection));
        writerParameter.put("password","123456");
        writerParameter.put("username","root");
        writerParameter.put("column",writerColumn);
        writerParameter.put("preSql",Arrays.asList("truncate table user100w"));
        writerDB.put("name","mysqlwriter");
        writerDB.put("parameter",writerParameter);
        readerAndWriterDB.put("writer",writerDB);
        Map<String, Object> all = new HashMap<>(2);

        Map<String, Object> setting = new HashMap<>(2);
        Map<String, Object> speed = new HashMap<>(2);
        Map<String, Object> errorLimit = new HashMap<>(2);
        speed.put("byte",10485760);
        errorLimit.put("record",0);
        errorLimit.put("percentage",0.02);
        setting.put("speed",speed);
        setting.put("errorLimit",errorLimit);
        DataxJob.put("setting",setting);
        all.put("job",DataxJob);
        System.err.println(FastJsonUtils.getBeanToJson(all));
        try {
            writeFile("C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax\\job\\123.json",FastJsonUtils.getBeanToJson(all));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = "C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax";
        String jsonPath = "C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax\\job\\123.json";
//        String jsonPath = FastJsonUtils.getBeanToJson(all);
        try {
            start(filePath,jsonPath);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


//        FutureTask futureTask = new FutureTask(new MyJob());
//        new Thread(futureTask,"MyJob").start();
//        System.err.println(futureTask.get());


//        try {
//            System.err.println("start");
//            String windowcmd = "cmd /c python datax.py C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax\\job\\myjob.json";
//            System.err.println(windowcmd);
//            //.exec("你的命令",null,new File("datax安装路径"));
//            Process pr = Runtime.getRuntime().exec(windowcmd,null,new File("C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax\\bin"));
//            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.err.println(line);
//            }
//            in.close();
//            pr.waitFor();
//            System.err.println("end");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public static void writeFile(String filePath, String sets)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        out.println();
        fw.close();
        out.close();
    }

    public static void start(String dataxPath,String jsonPath) throws Throwable {
        System.setProperty("datax.home", dataxPath);
        System.setProperty("now", new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
        // 替换job中的占位符
        String[] datxArgs = {"-job", jsonPath, "-mode", "standalone", "-jobid", "-1"};
//        Engine.entry(datxArgs);
    }
}


class MyJob implements Callable {

    @Override
    public Object call() throws Exception {
        System.err.println("start");
        String windowcmd = "cmd /c python datax.py C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax\\job\\myjob.json";
        System.err.println(windowcmd);
        //.exec("你的命令",null,new File("datax安装路径"));
        Process pr = Runtime.getRuntime().exec(windowcmd, null, new File("C:\\Users\\Lenovo\\Downloads\\Compressed\\datax\\datax\\bin"));
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.err.println(line);
        }
        in.close();
        pr.waitFor();
        System.err.println("end");
        return "end";
    }
}