package com.yupi.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * 动态文件生成器
 */
public class DynamicFileGenerator {

    /**
     * 生成文件
     *
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws Exception
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws Exception {
        //new出Configuration 对象，参数为FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        //设置模板文件所在目录
        configuration.setDirectoryForTemplateLoading(templateDir);

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        configuration.setNumberFormat("0.######");

        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        //如果文件不存在，则创建目录
        if (!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        //生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        //生成文件后别忘了关闭
        out.close();
    }

}
