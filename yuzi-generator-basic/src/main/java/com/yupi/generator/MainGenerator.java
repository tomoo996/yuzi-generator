package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author yupi
 */
public class MainGenerator {

    public static void main(String[] args) throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws Exception {
        String inputRootPath = "D:\\yiziCode\\yuzi-generator\\yuzi-generator-demo-projects\\acm-template-pro";
        String outputRootPath = "D:\\yiziCode\\yuzi-generator";

        String intputPath;
        String outputPath;

        intputPath = new File(inputRootPath , "src/com/yupi/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath , "src/com/yupi/acm/MainTemplate.java").getAbsolutePath();
        // 生成动态文件
        DynamicGenerator.doGenerate(intputPath, outputPath, model);

        intputPath = new File(inputRootPath , ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath , ".gitignore").getAbsolutePath();
        // 生成静态文件
        StaticGenerator.copyFilesByHutool(intputPath, outputPath);

        intputPath = new File(inputRootPath , "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath , "README.md").getAbsolutePath();
        // 生成静态文件
        StaticGenerator.copyFilesByHutool(intputPath, outputPath);
    }
}
