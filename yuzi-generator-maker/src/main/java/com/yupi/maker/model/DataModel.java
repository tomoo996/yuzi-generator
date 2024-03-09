package com.yupi.maker.model;

import lombok.Data;

/**
 * 静态模板配置
 * @author zli32
 */
@Data
public class DataModel {

    /**
     * 是否需要生成.gitignore文件
     */
    private boolean needGit = true;

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
