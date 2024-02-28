package com.yupi.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.yupi.model.MainTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "config", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @Override
    public void run() {
        /*Class<MainTemplateConfig> myClass = MainTemplateConfig.class;
        //获取类的所有字段
        Field[] fields = myClass.getDeclaredFields();*/

        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field : fields) {
            System.out.println("字段类型:" + field.getType() + ",字段名称:" + field.getName());
        }

    }
}
