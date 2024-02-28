package ${basePackage}.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws Exception {
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String intputPath;
        String outputPath;

<#list fileConfig.files as fileInfo>
        intputPath = new File(inputRootPath , "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath , "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        StaticGenerator.copyFilesByHutool(intputPath, outputPath);
    <#else>
        DynamicGenerator.doGenerate(intputPath, outputPath, model);
    </#if>

</#list>
    }
}
