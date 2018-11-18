package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 品牌信息
 * 
 * @author ggn
 *
 */
@ESDocObject
public class EsGoodsBrand implements IESMappingType {
    /**
     * 品牌ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long brandId;
    /**
     * 品牌名称
     */
    @ESField()
    private String brandNickname;
    /**
     * 品牌名称
     */
    @ESField(analyzerType = ESAnalyzer.not_analyzed)
    private String brandName;
    /**
     * 
     * 品牌URl
     */
    @ESField()
    private String brandUrl;
    /**
     * LOGO
     */
    @ESField()
    private String brandLogo;
    /**
     * INDEX
     */
    @ESField()
    private String brandPromIndex;
    /**
     * 排序
     */
    @ESField(fieldType = ESFieldType.INTEGER)
    private Integer brandSort;
    /**
     * SEOtitle
     */
    @ESField()
    private String brandSeoTitle;
    /**
     * SEOKeyWord
     */
    @ESField()
    private String brandSeoKeyword;
    /**
     * SEO DESC
     */
    @ESField()
    private String brandSeoDesc;
    /**
     * 是否删除
     */
    @ESField()
    private String brandDelflag;
    /**
     * 创建�?
     */
    @ESField()
    private String brandCreateName;
    /**
     * 创建时间
     */
    @ESField()
    private Date brandCreateTime;
    /**
     * 修改时间
     */
    @ESField()
    private String brandModifiedName;
    /**
     * 修改时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date brandModifiedTime;
    /**
     * 删除时间
     */
    @ESField()
    private String brandDelName;
    /**
     * 删除时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date brandDelTime;
    /**
     * 品牌详细
     */
    @ESField()
    private String brandDesc;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandNickname() {
        return brandNickname;
    }

    public void setBrandNickname(String brandNickname) {
        this.brandNickname = brandNickname;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandPromIndex() {
        return brandPromIndex;
    }

    public void setBrandPromIndex(String brandPromIndex) {
        this.brandPromIndex = brandPromIndex;
    }

    public Integer getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(Integer brandSort) {
        this.brandSort = brandSort;
    }

    public String getBrandSeoTitle() {
        return brandSeoTitle;
    }

    public void setBrandSeoTitle(String brandSeoTitle) {
        this.brandSeoTitle = brandSeoTitle;
    }

    public String getBrandSeoKeyword() {
        return brandSeoKeyword;
    }

    public void setBrandSeoKeyword(String brandSeoKeyword) {
        this.brandSeoKeyword = brandSeoKeyword;
    }

    public String getBrandSeoDesc() {
        return brandSeoDesc;
    }

    public void setBrandSeoDesc(String brandSeoDesc) {
        this.brandSeoDesc = brandSeoDesc;
    }

    public String getBrandDelflag() {
        return brandDelflag;
    }

    public void setBrandDelflag(String brandDelflag) {
        this.brandDelflag = brandDelflag;
    }

    public String getBrandCreateName() {
        return brandCreateName;
    }

    public void setBrandCreateName(String brandCreateName) {
        this.brandCreateName = brandCreateName;
    }

    /**
     * 获取创建时间
     * */
    public Date getBrandCreateTime() {
        return brandCreateTime==null?null:(Date) brandCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setBrandCreateTime(Date brandCreateTime) {
        this.brandCreateTime = brandCreateTime == null ? null : (Date) brandCreateTime.clone();
    }

    public String getBrandModifiedName() {
        return brandModifiedName;
    }

    public void setBrandModifiedName(String brandModifiedName) {
        this.brandModifiedName = brandModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getBrandModifiedTime() {
        return brandModifiedTime==null?null:(Date) brandModifiedTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setBrandModifiedTime(Date brandModifiedTime) {
        this.brandModifiedTime = brandModifiedTime == null ? null : (Date) brandModifiedTime.clone();
    }

    public String getBrandDelName() {
        return brandDelName;
    }

    public void setBrandDelName(String brandDelName) {
        this.brandDelName = brandDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getBrandDelTime() {
        return brandDelTime==null?null:(Date) brandDelTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setBrandDelTime(Date brandDelTime) {
        this.brandDelTime = brandDelTime == null ? null : (Date) brandDelTime.clone();
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
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
        return this.getBrandId() == null ? "" : this.getBrandId().toString();
    }
}