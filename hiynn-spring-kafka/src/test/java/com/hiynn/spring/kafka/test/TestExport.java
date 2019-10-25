package com.hiynn.spring.kafka.test;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestExport
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/19 10:59
 * @Version 1.0.0
 */
public class TestExport{
    @Test
    public void testExport() throws IOException, InterruptedException {
        /**
         * 导出语句
         */
        List<String> list = Arrays.asList(
                "mysqldump",
                "-hlocalhost",
                "-P3306",
                "-uroot",
                "-p123456",
                "-B",
                "test",
                "-r",
                "d:/database.sql"
        );
        ProcessBuilder processBuilder = new ProcessBuilder();
        /**
         * 设置导出语句
         */
        processBuilder.command(list);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        long startTime = System.currentTimeMillis();
        int errCode = process.waitFor();
        long endTime = System.currentTimeMillis();
        String time = "耗时: "+((endTime-startTime)/1000)+"秒";

    }
}
