package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态代码生成器
 * @author zli32
 */

public class StaticGenerator {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        //输入路径
        String inputPath = projectPath + File.separator + "yuzi-generator-demo-projects" + File.separator + "acm-template";
        //输出路径
        String outputPath = projectPath;
        //复制
//        copyFilesByHutool(inputPath , outputPath);
        copyFilesByRecursive(inputPath , outputPath);
    }

    /**
     * 拷贝文件（Hutool实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath , false);
    }

    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFilesByRecursive(inputFile, outputFile);
        } catch (IOException e) {
            System.out.println("拷贝失败");
            e.printStackTrace();
        }
    }

    /**
     * 文件A => 目录 B,则文件 A 放在目录 B 下
     * 文件A => 文件 B,则文件 A 覆盖文件 B
     * 目录A => 目录 B,则目录 A 放在目录 B 下
     *
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    public static void copyFilesByRecursive(File inputFile, File outputFile) throws IOException {
        //区分是文件还是目录
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            if (!destOutputFile.exists()){
                destOutputFile.mkdir();
            }
            //获取目录下的所有文件和子目录
            File [] files = inputFile.listFiles();
            //无子文件，直接结束
            if (ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                //递归拷贝下一层文件
                copyFilesByRecursive(file , destOutputFile);
            }
        } else {
            //是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName()); // 目标路径
            FileUtil.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
