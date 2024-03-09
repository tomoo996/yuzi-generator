package ${basePackage}.generator;

import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

<#macro generateFile indent fileInfo>
${indent}intputPath = new File(inputRootPath , "${fileInfo.inputPath}").getAbsolutePath();
${indent}outputPath = new File(outputRootPath , "${fileInfo.outputPath}").getAbsolutePath();
<#if fileInfo.generateType == "static">
${indent}StaticGenerator.copyFilesByHutool(intputPath, outputPath);
<#else>
${indent}DynamicGenerator.doGenerate(intputPath, outputPath, model);
</#if>
</#macro>

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
    public static void doGenerate(DataModel model) throws Exception {
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String intputPath;
        String outputPath;

<#list modelConfig.models as modelInfo>
<#if modelInfo.groupKey??>
    <#list modelInfo.models as subModelInfo>
        ${subModelInfo.type} ${subModelInfo.fieldName} = model.${modelInfo.groupKey}.${subModelInfo.fieldName};
    </#list>
<#else>
        ${modelInfo.type} ${modelInfo.fieldName} = model.${modelInfo.fieldName};
</#if>

</#list>

<#list fileConfig.files as fileInfo>
    <#if fileInfo.groupKey??>
        // groupKey = ${fileInfo.groupKey}
        <#if fileInfo.condition??>
        if (${fileInfo.condition}) {
        <#list fileInfo.files as fileInfo>
            <@generateFile indent="            " fileInfo=fileInfo />

        </#list>
        }
        <#else>
        <#list fileInfo.files as fileInfo>
            <@generateFile indent="        " fileInfo=fileInfo />
        </#list>

        </#if>
    <#else>

        <#if fileInfo.condition??>
        if (${fileInfo.condition}) {
            <@generateFile indent="            " fileInfo=fileInfo />
        }
        <#else>
            <@generateFile indent="        " fileInfo=fileInfo />
        </#if>
    </#if>
</#list>
    }
}
