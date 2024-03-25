package com.yupi.maker.generator.main;

import java.io.IOException;

public class MainGenerator extends GenerateTemplate{

    @Override
    protected String buildDist(String outputPath, String ShellOutputPath, String jarPath, String sourceCopyDestPath) {
        System.out.println("不要给我输出 dist 啦?");
        return "";
    }

}
