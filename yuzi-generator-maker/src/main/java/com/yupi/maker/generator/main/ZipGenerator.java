package com.yupi.maker.generator.main;

/**
 * 生成代码生成器压缩包
 */
public class ZipGenerator extends GenerateTemplate{

    @Override
    protected String buildDist(String outputPath, String ShellOutputPath, String jarPath, String sourceCopyDestPath) {
        String distPath = super.buildDist(outputPath, ShellOutputPath, jarPath, sourceCopyDestPath);
        return super.buildZip(distPath);
    }

}
