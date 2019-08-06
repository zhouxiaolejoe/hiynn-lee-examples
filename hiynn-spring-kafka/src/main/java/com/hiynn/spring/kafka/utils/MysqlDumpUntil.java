package com.hiynn.spring.kafka.utils;

import java.io.*;

/**
 * @ClassName MysqlDumpUntil
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/8/5 17:26
 * @Version 1.0.0
 */
public class MysqlDumpUntil {

    private static String[] importCommand(JdbcProperties properties) {
        //第一步，获取登录命令语句
        String loginCommand = String.format("mysql -h%s -u%s -p%s -P%s",
                properties.getHost(),
                properties.getUsername(),
                properties.getPassword(),
                properties.getPort());
        //第二步，获取切换数据库到目标数据库的命令语句
        String switchCommand = String.format("use %s",properties.getImportDataBase());
        //第三步，获取导入的命令语句
        String importCommand = String.format("source %s",properties.getImportPath());
        //需要返回的命令语句数组
        String[] commands = new String[] {loginCommand, switchCommand, importCommand};
        return commands;
    }
    public static void importSql(JdbcProperties properties) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        //因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
        String cmdarray[] = importCommand(properties);//根据属性文件的配置获取数据库导入所需的命令，组成一个数组
        //runtime.exec(cmdarray);//这里也是简单的直接抛出异常
        Process process = runtime.exec(cmdarray[0]);
        //执行了第一条命令以后已经登录到mysql了，所以之后就是利用mysql的命令窗口
        //进程执行后面的代码
        OutputStream os = process.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        //命令1和命令2要放在一起执行
        writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
        writer.flush();
        writer.close();
        os.close();
    }



    private static String exportCommand(JdbcProperties properties) {
        String exportCommand = String.format("cmd /k mysqldump -u%s -p%s -h%s -P%s %s -r %s",
                properties.getUsername(),
                properties.getPassword(),
                properties.getHost(),
                properties.getPort(),
                properties.getExportDataBase(),
                properties.getExportPath());
        return exportCommand;
    }

    public static void export(JdbcProperties properties) throws IOException {
        String command = exportCommand(properties);
        Runtime runtime = Runtime.getRuntime();
//        ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
//        processBuilder.start();
        Process process = runtime.exec(command);//这里简单一点异常我就直接往上抛

    }
}
