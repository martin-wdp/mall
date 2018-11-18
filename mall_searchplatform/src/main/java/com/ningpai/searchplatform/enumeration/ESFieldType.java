package com.ningpai.searchplatform.enumeration;

/**
 * of1081_yxd on 2015/1/6. Description：索引-映射结构中字段类型.
 */
public enum ESFieldType {

    /**
     * string 数据类型
     */
    STRING("string"),

    /**
     * double 数据类型
     */
    DOUBLE("double"),
    /**
     * nested类型
     */
    NESTED("nested"),

    /**
     * nested类型
     */
    OBJECT("object"),

    /**
     * boolean 数据类型
     */
    BOOLEAN("boolean"),

    /**
     * integer 数据类型
     */
    INTEGER("integer"),

    /**
     * date 数据类型
     */
    DATE("date"),

    /**
     * long 数据类型
     */
    LONG("long");

    /**
     * ES中数据类型标识
     */
    private String typeValue;

    ESFieldType(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeValue() {
        return typeValue;
    }
}
