package com.hiynn.dynamic.datasource.service;

import com.hiynn.dynamic.datasource.okhttp3.OkHttpUtil;
import com.hiynn.untils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Okhttp3Test {
	@Test
	public void testOkhttp3Get() {
		String result = OkHttpUtil.getStringFromServer("http://localhost:8080/dynamic_db/testFindRole");
		Assert.assertNotNull(result);
		Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
		log.info(FastJsonUtils.getBeanToJson(resultMap));
	}
	@Test
	public void testOkhttp3GetPathAndQuery1(){
		String path="/1";
		String param="?date=123";
		String result = OkHttpUtil.getStringFromServer("http://localhost:8080/dynamic_db/testUrlParam"+path+param);
		Assert.assertNotNull(result);
		Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
		log.info(FastJsonUtils.getBeanToJson(resultMap));
	}
	@Test
	public void testOkhttp3GetPathAndQuery2(){
		HttpUrl httpUrl = HttpUrl.get("http://localhost:8080/dynamic_db/testUrlParam");
		HttpUrl request = httpUrl.newBuilder().addPathSegments("1").addQueryParameter("date", "name").build();
		String result = OkHttpUtil.getStringFromServerHttpUrl(request);
		Assert.assertNotNull(result);
		Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
		log.info(FastJsonUtils.getBeanToJson(resultMap));
	}
	@Test
	public void testOkhttp3Post(){
		Map<String, String> parameters = new HashMap<String, String>() {
			{
				put("account", "joe");
				put("password", "123456");
				put("username", "zxl");
			}
		};
		String result = OkHttpUtil.getStringFromServerByPost("http://localhost:8080/dynamic_db/testJsonParam", FastJsonUtils.getBeanToJson(parameters));
		Assert.assertNotNull(result);
		Map<Object, Object> resultMap = FastJsonUtils.getJsonToMap(result);
		log.info(FastJsonUtils.getBeanToJson(resultMap));
	}
}
