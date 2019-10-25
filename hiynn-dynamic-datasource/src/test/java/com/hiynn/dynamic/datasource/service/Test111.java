package com.hiynn.dynamic.datasource.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/29 10:57
 * @Version 1.0.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
public class Test111 {
//    @Autowired
//    RestTemplate restTemplate;


    public static void main(String[] args) {
        final String sign = "safasdfasfaerpwqorpqwrpwqoerpqwer";
        final String token = "0123456789";
        final int dept_password = 4106694;
        final int dept_id = 8;
        final int page_size = 1;
        final String path = "http://120.25.174.18:6000/";
        JSONObject jsonObject = new JSONObject();
        Map requestMap =new HashMap();
        requestMap.put("dept_password",dept_password);
        requestMap.put("dept_id",dept_id);
        requestMap.put("page_size",page_size);
        jsonObject.put("request",requestMap);
        long time = new Date().getTime();
        jsonObject.put("timestamp",time);
        jsonObject.put("token",token);
        String signs = time + sign + token;
        String md5sign = getMD5String(signs);
        jsonObject.put("sign",md5sign);


        String url = path+"Check"+ File.separator+"v2"+File.separator+"GetTask";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObject.toString(), headers);
        JSONObject jb = restTemplate.postForEntity(url,formEntity,JSONObject.class).getBody();
        System.err.println(jb.toString());
    }
    @Test
    public void Test01(){
        final String sign = "safasdfasfaerpwqorpqwrpwqoerpqwer";
         final String token = "0123456789";
         final int dept_password = 4106694;
         final int dept_id = 8;
         final int page_size = 1;
         final String path = "http://120.25.174.18:6000/";
        JSONObject jsonObject = new JSONObject();
        Map requestMap =new HashMap();
        requestMap.put("dept_password",dept_password);
        requestMap.put("dept_id",dept_id);
        requestMap.put("page_size",page_size);
        jsonObject.put("request",requestMap);
        long time = new Date().getTime();
        jsonObject.put("timestamp",time);
        jsonObject.put("token",token);
        String signs = time + sign + token;
        String md5sign = getMD5String(signs);
        jsonObject.put("sign",md5sign);


        String url = path+"Check"+ File.separator+"v2"+File.separator+"GetTask";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObject.toString(), headers);
        JSONObject jb = restTemplate.postForEntity(url,formEntity,JSONObject.class).getBody();
        System.err.println(jb.toString());

    }
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
