package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;

/**
 * 属性参数
 * 
 * @author ggn
 *
 */
@ESDocObject
public class EsExpandparam implements IESMappingType {

    /**
     * 主键ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long releExpandparamId;
    /**
     * 商品ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long goodsId;
    /**
     * 属性ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long expandparamId;
    /**
     * 属性名称
     */
    @ESField(analyzerType = ESAnalyzer.not_analyzed)
    private String expandparamName;
    /**
     * 属性值ID
     */
    @ESField()
    private String expandparamValueId;
    /**
     * 属性值Name
     */
    @ESField(analyzerType = ESAnalyzer.not_analyzed)
    private String expandparamValueName;

    public Long getReleExpandparamId() {
        return releExpandparamId;
    }

    public void setReleExpandparamId(Long releExpandparamId) {
        this.releExpandparamId = releExpandparamId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getExpandparamId() {
        return expandparamId;
    }

    public void setExpandparamId(Long expandparamId) {
        this.expandparamId = expandparamId;
    }

    public String getExpandparamName() {
        return expandparamName;
    }

    public void setExpandparamName(String expandparamName) {
        this.expandparamName = expandparamName;
    }

    public String getExpandparamValueId() {
        return expandparamValueId;
    }

    public void setExpandparamValueId(String expandparamValueId) {
        this.expandparamValueId = expandparamValueId;
    }

    public String getExpandparamValueName() {
        return expandparamValueName;
    }

    public void setExpandparamValueName(String expandparamValueName) {
        this.expandparamValueName = expandparamValueName;
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
     */
    @Override
    public String generateDocId() {
        return this.getReleExpandparamId() == null ? "" : this
                .getReleExpandparamId().toString();
    }
}