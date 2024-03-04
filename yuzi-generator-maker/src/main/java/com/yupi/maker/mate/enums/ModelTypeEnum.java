package com.yupi.maker.mate.enums;

/**
 * 文件生成类型枚举
 */
public enum ModelTypeEnum {

    STRING("字符串", "String"),
    BOOLEAN("布尔值", "boolean");

    private final String text;
    private final String value;

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
