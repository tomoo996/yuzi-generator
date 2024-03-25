package com.yupi.maker;

import com.yupi.maker.generator.main.GenerateTemplate;
import com.yupi.maker.generator.main.MainGenerator;
import com.yupi.maker.generator.main.ZipGenerator;

public class Main {
     public static void main(String[] args) throws Exception {
         GenerateTemplate generateTemplate = new ZipGenerator();
         generateTemplate.doGenerate();
    }
}