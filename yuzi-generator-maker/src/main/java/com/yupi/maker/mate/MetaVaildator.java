package com.yupi.maker.mate;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.yupi.maker.mate.enums.FileGenerateTypeEnum;
import com.yupi.maker.mate.enums.FileTypeEnum;
import com.yupi.maker.mate.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MetaVaildator {

    public static void doVailAndFill(Meta meta) {

        validAndFillMetaRoot(meta);
        vaildAndFillFileConfig(meta);
        vaildFillModelConfig(meta);

    }

    private static void vaildFillModelConfig(Meta meta) {
        // modelConfig 校验和默认值
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (CollUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("未填写 fieldName");
            }

            String type = modelInfo.getType();
            if (StrUtil.isBlank(type)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }

        }
    }

    private static void vaildAndFillFileConfig(Meta meta) {
        // fileConfig 校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        // sourceRootPath 必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }

        // inputRootPath .source + sourceRootPath 的最后一个层级目录
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source/" + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isBlank(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }

        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        if (StrUtil.isBlank(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }

        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isBlank(fileConfigType)) {
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.FileInfo> files = fileConfig.getFiles();
        if (CollUtil.isEmpty(files)) {
            return;
        }
        for (Meta.FileConfig.FileInfo file : files) {
            // inputPath 必填
            String inputPath = file.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写 inputPath");
            }

            // outputPath 默认等于 inputPath
            String outputPath = file.getOutputPath();
            if (StrUtil.isBlank(outputPath)) {
                file.setOutputPath(inputPath);
            }

            // type: 默认 inputPath 有文件后缀 （比如 .java） 默认为 file，否则为 dir
            String type = file.getType();
            if (StrUtil.isBlank(type)) {
                // 无文件后缀
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    file.setType(FileTypeEnum.DIR.getValue());
                } else {
                    file.setType(FileTypeEnum.FILE.getValue());
                }
            }

            // generateType:文件结尾不为 ftl, generateType 为 static, 否则为 dynamic
            String generateType = file.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                if (inputPath.endsWith(".ftl")) {
                    file.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    file.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }

    }

    private static void validAndFillMetaRoot(Meta meta) {
        // 基础信息校验和默认值
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        meta.setName(name);

        String description = StrUtil.blankToDefault(meta.getDescription(), "我的模板代码生成器");
        meta.setDescription(description);

        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.yupi");
        meta.setBasePackage(basePackage);

        String version = StrUtil.blankToDefault(meta.getVersion(), "1.0");
        meta.setVersion(version);

        String author = StrUtil.blankToDefault(meta.getAuthor(), "yupi");
        meta.setAuthor(author);

        String createTime = StrUtil.blankToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setCreateTime(createTime);

        Boolean gitInit = meta.getGitInit();
        if (gitInit == null) {
            gitInit = true;
            meta.setGitInit(gitInit);
        }
    }

}
