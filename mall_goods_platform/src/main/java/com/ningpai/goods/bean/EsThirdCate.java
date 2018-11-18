package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;

/**
 * 第三方分类 Created by guoguangnan on 2015/8/28.
 */
@ESDocObject
public class EsThirdCate implements IESMappingType {

    /**
     * 分类ID
     */
    @ESField(fieldType = ESFieldType.LONG, analyzerType = ESAnalyzer.not_analyzed)
    private Long catId;

    /**
     * 父亲分类ID
     */
    @ESField(fieldType = ESFieldType.LONG, analyzerType = ESAnalyzer.not_analyzed)
    private Long catParentId;

    /**
     * 分类名称
     */
    @ESField(analyzerType = ESAnalyzer.not_analyzed)
    private String catName;

    public Long getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Long catParentId) {
        this.catParentId = catParentId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * 返回该文档对象进行索引的field, 用于反射调用生成MapType
     *
     * @return {@link Field}
     */
    @Override
    public Field[] foundTypeField() {
        return this.getClass().getDeclaredFields();
    }

    /**
     * 文档ID
     *
     * @see com.ningpai.searchplatform.bean.IESMappingType#generateDocId()
     */
    @Override
    public String generateDocId() {
        return this.getCatId() == null ? "" : this.getCatId().toString();
    }
}
