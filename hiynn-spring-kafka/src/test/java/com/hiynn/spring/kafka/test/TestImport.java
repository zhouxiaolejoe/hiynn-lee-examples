package com.hiynn.spring.kafka.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestImport
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/19 11:00
 * @Version 1.0.0
 */
@Slf4j
public class TestImport {
    @Test
    public void testImport() throws IOException {
        List<String> list02 = Arrays.asList(
                "mysql",
                "-h192.168.238.106",
                "-P3306",
                "-uroot",
                "-p123456"
        );
        ProcessBuilder processBuilder1 = new ProcessBuilder();
        processBuilder1.command(list02);
        Process process02 = processBuilder1.start();

        String switchCommand = String.format("use %s","test1");

        String importCommand = String.format("source %s","d:/database.sql");

        OutputStream outputStream = process02.getOutputStream();

        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        writer.write(switchCommand + "\r\n" + importCommand);

        writer.flush();

        writer.close();

        long startTime1 = System.currentTimeMillis();
        int errCode02 = 0;
        try {
            errCode02 = process02.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("command executed, any errors? " + (errCode02 == 0 ? "No" : "Yes"));
        long endTime1 = System.currentTimeMillis();
        String time1="耗时: "+((endTime1-startTime1)/1000)+"秒";

    }

    @Test
    public void testImport1() throws IOException {
        List<String> list02 = Arrays.asList(
                "mysql",
                "-h192.168.238.106",
                "-P3306",
                "-uroot",
                "-p123456"
        );
        ProcessBuilder processBuilder1 = new ProcessBuilder();
        processBuilder1.command(list02);
        Process process02 = processBuilder1.start();

//        String switchCommand = String.format("use %s","test1");

        String importCommand = String.format("source %s","d:/database.sql");

        OutputStream outputStream = process02.getOutputStream();

        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        writer.write(importCommand);

        writer.flush();

        writer.close();

        long startTime1 = System.currentTimeMillis();
        int errCode02 = 0;
        try {
            errCode02 = process02.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("command executed, any errors? " + (errCode02 == 0 ? "No" : "Yes"));
        long endTime1 = System.currentTimeMillis();
        String time1="耗时: "+((endTime1-startTime1)/1000)+"秒";

    }
}
