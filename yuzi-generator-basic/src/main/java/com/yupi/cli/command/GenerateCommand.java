package com.yupi.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable {

    /**
     * 是否循环（开关）
     */
    @CommandLine.Option(names = {"-l", "--loop"}, description = "是否循环", interactive = true, arity = "0..1", echo = true)
    private boolean loop;

    /**
     * 作者（字符串，填充值）
     */
    @CommandLine.Option(names = {"-a", "--author"}, description = "作者名称", interactive = true, arity = "0..1", echo = true)
    private String author = "yupi";

    /**
     * 输出信息
     */
    @CommandLine.Option(names = {"-o", "--output"}, description = "输出文本", interactive = true, arity = "0..1",echo = true)
    private String outputText = "输出结果";

    @Override
    public Object call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
