package com.hiynn.spring.quartz.untils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName DataBaseUtils
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/20 16:37
 * @Version 1.0.0
 */
public class DataBaseUtils {

    public static void exportDataBase(JdbcProperties jdbcProperties){
        /**
         * 导出语句
         */
        List<String> list = Arrays.asList(
                "mysqldump",
                String.format("-h%s",jdbcProperties.getHost()),
                String.format("-P%s",jdbcProperties.getPort()),
                String.format("-u%s",jdbcProperties.getUsername()),
                String.format("-p%s",jdbcProperties.getPassword()),
                "-B",
                jdbcProperties.getExportDataBase(),
                "-r",
                jdbcProperties.getExportPath()
        );
        ProcessBuilder processBuilder = new ProcessBuilder();
        /**
         * 设置导出语句
         */
        processBuilder.command(list);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void importDataBase(JdbcProperties jdbcProperties){
        List<String> list02 = Arrays.asList(
                "mysql",
                String.format("-h%s",jdbcProperties.getHost()),
                String.format("-P%s",jdbcProperties.getPort()),
                String.format("-u%s",jdbcProperties.getUsername()),
                String.format("-p%s",jdbcProperties.getPassword())
        );
        ProcessBuilder processBuilder1 = new ProcessBuilder();
        processBuilder1.command(list02);
        try {
            Process process02 = processBuilder1.start();


//            String switchCommand = String.format("use %s","test1");

            String importCommand = String.format("source %s",jdbcProperties.getImportPath());

            OutputStream outputStream = process02.getOutputStream();

            OutputStreamWriter writer = new OutputStreamWriter(outputStream);

            writer.write(importCommand);
//            writer.write(switchCommand + "\r\n" + importCommand);

            writer.flush();

            writer.close();

            process02.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
