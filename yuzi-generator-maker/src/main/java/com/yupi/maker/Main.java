package com.yupi.maker;

import com.yupi.maker.generator.main.MainGenerator;

public class Main {
     public static void main(String[] args) throws Exception {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}