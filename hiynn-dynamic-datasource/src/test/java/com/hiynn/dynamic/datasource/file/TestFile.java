package com.hiynn.dynamic.datasource.file;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * Created by ZhouXiaoLe on 2019/7/20
 */
@SpringBootTest
public class TestFile {
    @Test
    public void test1() throws IOException {
        String a = "a";
        String b = "b";
        String c = "c";
        String format = String.format("%s%s%s", a, b, c);
        System.err.println(format);
        String fileName = "1.json";
        String substring = fileName.substring(fileName.lastIndexOf("."));
        System.err.println(substring);
    }
}
