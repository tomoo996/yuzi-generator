package com.yupi.model;

import lombok.Data;

/**
 * 静态模板配置
 * @author zli32
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否循环（开关）
     */
    private boolean loop;

    /**
     * 作者（字符串，填充值）
     */
    private String author = "yupi";

    private String outputText = "输出结果";

}
