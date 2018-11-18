package com.ningpai.searchplatform.request;

import com.ningpai.searchplatform.bean.ESSource;
import com.ningpai.searchplatform.bean.IESMappingType;

import java.util.List;

/**
 * <p>
 * 创建索引请求包装
 * </p>
 *
 * @author liangck
 * @version 1.0
 * @since 15/8/9 12:45
 */
public class IndexCreateRequest {

    /**
     * 索引名称
     */
    private String index;

    /**
     * 索引下的数据
     */
    private List<ESSource> sources;

    /**
     * 索引的数据类型,
     */
    private Class<? extends IESMappingType> esMapType;

    /**
     * 无参构造函数
     */
    public IndexCreateRequest() {
    }

    /**
     * 一个参数的构造函数
     */
    public IndexCreateRequest(String index) {
        this.index = index;
    }

    /**
     * 两个参数的构造函数
     */
    public IndexCreateRequest(String index, List<ESSource> sources) {
        this.index = index;
        this.sources = sources;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<ESSource> getSources() {
        return sources;
    }

    public void setSources(List<ESSource> sources) {
        this.sources = sources;
    }

    public Class getEsMapType() {
        return esMapType;
    }

    public void setEsMapType(Class<? extends IESMappingType> esMapType) {
        this.esMapType = esMapType;
    }

}
