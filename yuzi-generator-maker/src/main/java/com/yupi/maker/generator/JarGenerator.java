package com.yupi.maker.generator;

import java.io.*;

public class JarGenerator {

    public static Integer doGenerate(String projectDir) throws IOException, InterruptedException {
        String OSName = System.getProperty("os.name");
        // 调用 process 类执行 Maven 打包命令
        String MavenCommand;
        if (OSName.contains("Windows")) {
            MavenCommand = "mvn.cmd clean package -DskipTests=true";
        } else {
            MavenCommand = "mvn clean package -DskipTests=true";
        }

        ProcessBuilder processBuilder = new ProcessBuilder(MavenCommand.split(" "));
        processBuilder.directory(new File(projectDir)); // 设置工作目录

        Process process = processBuilder.start();

        // 读取命令执行结果
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();

        System.out.println("命令执行结束,退出码:" + exitCode);

        return exitCode;
    }

}
