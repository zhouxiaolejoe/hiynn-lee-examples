package com.hiynn.spring.quartz.activemq;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Export
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/17 16:33
 * @Version 1.0.0
 */
public class TestExport {

    public static void main(String[] args) throws IOException {
        /**
         * 导出语句
         */
        List<String> list = Arrays.asList(
                "mysqldump",
                "-hlocalhost",
                "-P3306",
                "-uroot",
                "-p123456",
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long startTime = System.currentTimeMillis();
                    int errCode = process.waitFor();
                    long endTime = System.currentTimeMillis();
                    String time = "耗时: "+((endTime-startTime)/1000)+"秒";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
