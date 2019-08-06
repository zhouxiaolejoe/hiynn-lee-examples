package com.hiynn.spring.kafka.test;

import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName TestProcessBuilder
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/6 8:54
 * @Version 1.0.0
 */
public class TestProcessBuilder {
    @Test
    public void test1() throws IOException {
        ProcessBuilder proc = new ProcessBuilder();
        proc.command("cmd", "dir");
        proc.start();

    }
}
