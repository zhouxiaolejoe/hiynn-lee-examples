package com.hiynn.spring.kafka.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @ClassName TestExportCommand
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/7 11:03
 * @Version 1.0.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExportCommand {
//    private static String output(InputStream inputStream) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(inputStream));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                sb.append(line + System.getProperty("line.separator"));
//            }
//        } finally {
//            br.close();
//        }
//        return sb.toString();
//    }
//    private static class IOThreadHandler extends Thread {
//        private InputStream inputStream;
//        private StringBuilder output = new StringBuilder();
//
//        IOThreadHandler(InputStream inputStream) {
//            this.inputStream = inputStream;
//        }
//
//        public void run() {
//            Scanner br = null;
//            try {
//                br = new Scanner(new InputStreamReader(inputStream));
//                String line = null;
//                while (br.hasNextLine()) {
//                    line = br.nextLine();
//                    output.append(line
//                            + System.getProperty("line.separator"));
//                }
//            } finally {
//                br.close();
//            }
//        }
//
//        public StringBuilder getOutput() {
//            return output;
//        }
//    }
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    Executor taskExecutor;

    @Test
    public void test1() throws IOException, InterruptedException {
//        processBuilder.command("cmd.exe",
//                "/k mysqldump -uroot -p123456 -hlocalhost -P3306 test -r d:/database.sql");
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
                        if (errCode==0){
                            kafkaTemplate.send("topic1","数据库导出完成  "+time);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Thread.sleep(600l * 1000L);

//        log.info("out===>"+output(process.getInputStream()));



//        List<String> list02 = Arrays.asList(
//                "mysql",
//                "-h192.168.238.101",
//                "-P3306",
//                "-uroot",
//                "-p123456"
//        );
//        ProcessBuilder processBuilder1 = new ProcessBuilder();
//        processBuilder1.command(list02);
//        Process process02 = processBuilder1.start();
//
//        String switchCommand = String.format("use %s","test1");
//
//        String importCommand = String.format("source %s","d:/database.sql");
//
//        OutputStream outputStream = process02.getOutputStream();
//
//        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//
//        writer.write(switchCommand + "\r\n" + importCommand);
//
//        writer.flush();
//
//        writer.close();
//        long startTime1 = System.currentTimeMillis();
//        int errCode02 = process02.waitFor();
//        log.info("Echo command executed, any errors? " + (errCode02 == 0 ? "No" : "Yes"));
//        long endTime1 = System.currentTimeMillis();
//        String time1="耗时: "+((endTime1-startTime1)/1000)+"秒";
//        log.info(time1);
//        kafkaTemplate.send("topic1","数据库导入完成  "+time1);





//=====================================Runtime runtime==================================================================
//        JdbcProperties properties = JdbcProperties.builder()
//                .host("192.168.238.101")
//                .port("3306")
//                .username("root")
//                .password("123456")
//                .importPath("d:/database.sql")
//                .importDataBase("test1")
//                .build();
//
//        Runtime runtime = Runtime.getRuntime();
//        //因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
//        String cmdarray[] = MysqlDumpUntil.importCommand(properties);//根据属性文件的配置获取数据库导入所需的命令，组成一个数组
//        //runtime.exec(cmdarray);//这里也是简单的直接抛出异常
//        Process process = runtime.exec(cmdarray[0]);
//        //执行了第一条命令以后已经登录到mysql了，所以之后就是利用mysql的命令窗口
//        //进程执行后面的代码
//        OutputStream os = process.getOutputStream();
//        OutputStreamWriter writer = new OutputStreamWriter(os);
//        //命令1和命令2要放在一起执行
//        writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
//        writer.flush();
//        writer.close();
//        os.close();
//

//        int errCode = start.waitFor();
//
//        output(start.getErrorStream());
//

//        IOThreadHandler outputHandler = new IOThreadHandler(
//                start.getInputStream());
//        outputHandler.start();
//        start.waitFor();
//        System.out.println(outputHandler.getOutput());


//        InputStream inputStream = start.getInputStream();
//        BufferedInputStream is = new BufferedInputStream(inputStream);
//        StringBuffer buffer = new StringBuffer();
//        int len = 0;
//        byte[] b = new byte[1024];
//        while ((len = is.read(b)) != -1) {
//            //将读取的字节转为字符串对象
//            String line = new String(b, 0, len);
//            System.err.println("读取进程中的数据"+line);
//            buffer.append(line);
//            buffer.append("\n");
//        }
//
//        InputStream errorStream = start.getErrorStream();
//        BufferedInputStream bis = new BufferedInputStream(errorStream);
//        int len1 = 0;
//        byte[] b1 = new byte[1024];
//        while ((len1 = bis.read(b1)) != -1) {
//            //将读取的字节转为字符串对象
//            String line1 = new String(b1, 0, len1);
//            System.err.println("读取进程中的数据"+line1);
//        }
    }

}
