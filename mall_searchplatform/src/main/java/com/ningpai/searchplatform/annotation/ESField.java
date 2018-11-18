package com.ningpai.searchplatform.annotation;

import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * es doc field 注解
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/9 12:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ESField {
    /**
     * 字段名称
     * 
     * @return
     */
    public String fieldName() default "";

    /**
     * 字段类型,默认为string
     * 
     * @return
     */
    public ESFieldType fieldType() default ESFieldType.STRING;

    /**
     * 分词器,默认不进行分词
     * 
     * @return
     */
    ESAnalyzer analyzerType() default ESAnalyzer.not_analyzed;

    /**
     * 是否存储,默认为是
     * 
     * @return
     */
    public boolean isStore() default true;
}
