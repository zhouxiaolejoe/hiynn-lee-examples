package com.hiynn.spring.quartz.mapper.other;

import com.hiynn.spring.quartz.untils.FTPUtil;
import com.hiynn.spring.quartz.untils.Zip4JUtils;
import com.hiynn.spring.quartz.untils.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;

/**
 * @ClassName TestZipPackage
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/21 14:04
 * @Version 1.0.0
 */

//@SpringBootTest
//@RunWith(SpringRunner.class)
@Slf4j
public class TestZipPackage {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test03() throws Exception {
        redisTemplate.opsForValue().set("11111","232323123");
    }


    @Test
    public void test01() throws Exception {
        ZipUtil.toZip("F:\\activemq-ftp\\import\\76db52eef8bb452d94b7b67c72aef0de.sql","F:\\activemq-ftp\\export\\76db52eef8bb452d94b7b67c72aef0de.zip",false);
        ZipUtil.unZipFiles("F:\\activemq-ftp\\export\\76db52eef8bb452d94b7b67c72aef0de.zip","F:/activemq-ftp/export",true);

    }

    @Test
    public void test02() throws Exception {
        Zip4JUtils.zip("F:\\activemq-ftp\\import\\","F:/activemq-ftp/export/test.zip",false,"123456");
        Zip4JUtils.unZip(new File("F:/activemq-ftp/export/test.zip"),"F:/activemq-ftp/export","123456");
    }

    @Test
    public void test04() {
        File file = new File("D:\\database.sql");
        FTPUtil ftpUtil = new FTPUtil();
        ftpUtil.uploadLocalFile("dinging/","D:\\2019-08-23_090612.jpg","2019-08-23_090612.jpg");
    }
    @Test
    public void test05() {

    }
}
