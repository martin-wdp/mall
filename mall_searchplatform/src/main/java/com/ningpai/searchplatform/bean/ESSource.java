package com.ningpai.searchplatform.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文档数据包装
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/9 12:22
 */
public class ESSource {

    /**
     * 文档编号
     */
    private String id;

    /**
     * 数据
     */
    private Map<String, String> data;

    /**
     * 实现了获取esmappingtyupe 接口的 待索引的文档
     */
    private IESMappingType document;

    /**
     * 无参构造
     * */
    public ESSource() {
    }

    /**
     * 一个参数的构造方法
     * */
    public ESSource(String id) {
        this.id = id;
    }

    /**
     * 两个参数的构造函数
     * */
    public ESSource(String id, Map<String, String> data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取数据
     * */
    public Map<String, String> getData() {
        return data == null ? new HashMap<String, String>() : data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public IESMappingType getDocument() {
        return document;
    }

    public void setDocument(IESMappingType document) {
        this.document = document;
    }
}
