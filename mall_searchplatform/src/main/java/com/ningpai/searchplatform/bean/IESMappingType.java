package com.ningpai.searchplatform.bean;

import java.lang.reflect.Field;

/**
 * <p>
 * es 文档数据类型,所有定义为es文档的bean 必须实现该接口
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/9 12:20
 */
public interface IESMappingType {

    /**
     * 生成文档ID
     * @return 文档ID
     */
    String generateDocId();

    /**
     * 返回该文档对象进行索引的field, 用于反射调用生成MapType
     * 
     * @return {@link Field}
     */
    public Field[] foundTypeField();
}
