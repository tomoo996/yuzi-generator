package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态代码生成器
 */
public class StaticGenerator {

    /**
     * 拷贝文件（Hutool实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath , false);
    }

}
