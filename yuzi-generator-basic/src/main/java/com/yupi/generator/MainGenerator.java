package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;

import java.io.File;

/**
 * @author yupi
 */
public class MainGenerator {

    public static void main(String[] args) throws Exception {
        String projectPath = System.getProperty("user.dir");
        //输入路径
        String inputPath = projectPath + File.separator + "yuzi-generator-demo-projects" + File.separator + "acm-template";
        //输出路径
        String outputPath = projectPath;
        //复制
        StaticGenerator.copyFilesByRecursive(inputPath , outputPath);

        //动态文件生成
        String dynamicinputPath = projectPath + File.separator + "yuzi-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicoutputPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果:");
        DynamicGenerator.doGenerate(dynamicinputPath,dynamicoutputPath, mainTemplateConfig);
    }
}
