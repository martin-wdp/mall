package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分类信息
 * 
 * @author ggn
 *
 */
@ESDocObject
public class EsGoodsCategory implements IESMappingType {
    /**
     * 分类ID
     */
    @ESField(fieldType = ESFieldType.LONG, analyzerType = ESAnalyzer.not_analyzed)
    private Long catId;
    /**
     * 分类名称
     */
    @ESField(analyzerType = ESAnalyzer.not_analyzed)
    private String catName;
    /**
     * 父分类ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long catParentId;
    /**
     * 类型ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long typeId;
    /**
     * 排序
     */
    @ESField(fieldType = ESFieldType.INTEGER)
    private Integer catSort;
    /**
     * 层级
     */
    @ESField(fieldType = ESFieldType.INTEGER)
    private Integer catGrade;
    /**
     * SEO title
     */
    @ESField()
    private String catSeoTitle;
    /**
     * SEO keyword
     */
    @ESField()
    private String catSeoKeyword;
    /**
     * SEO desc
     */
    @ESField()
    private String catSeoDesc;
    /**
     * 是否显示
     */
    @ESField()
    private String catIsShow;
    /**
     * 是否删除
     */
    @ESField()
    private String catDelflag;
    /**
     * 创建时间
     */
    @ESField()
    private String catCreateName;
    /**
     * 创建时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date catCreateTime;
    /**
     * 修改时间
     */
    @ESField()
    private String catModifiedName;
    /**
     * 修改时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date catModifiedTime;
    /**
     * 删除时间
     */
    @ESField()
    private String catDelName;
    /**
     * 删除时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date catDelTime;
    /**
     * 扣率
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal catRate;
    /**
     * MODEL
     */
    @ESField()
    private String catModel;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Long catParentId) {
        this.catParentId = catParentId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getCatSort() {
        return catSort;
    }

    public void setCatSort(Integer catSort) {
        this.catSort = catSort;
    }

    public Integer getCatGrade() {
        return catGrade;
    }

    public void setCatGrade(Integer catGrade) {
        this.catGrade = catGrade;
    }

    public String getCatSeoTitle() {
        return catSeoTitle;
    }

    public void setCatSeoTitle(String catSeoTitle) {
        this.catSeoTitle = catSeoTitle;
    }

    public String getCatSeoKeyword() {
        return catSeoKeyword;
    }

    public void setCatSeoKeyword(String catSeoKeyword) {
        this.catSeoKeyword = catSeoKeyword;
    }

    public String getCatSeoDesc() {
        return catSeoDesc;
    }

    public void setCatSeoDesc(String catSeoDesc) {
        this.catSeoDesc = catSeoDesc;
    }

    public String getCatIsShow() {
        return catIsShow;
    }

    public void setCatIsShow(String catIsShow) {
        this.catIsShow = catIsShow;
    }

    public String getCatDelflag() {
        return catDelflag;
    }

    public void setCatDelflag(String catDelflag) {
        this.catDelflag = catDelflag;
    }

    public String getCatCreateName() {
        return catCreateName;
    }

    public void setCatCreateName(String catCreateName) {
        this.catCreateName = catCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getCatCreateTime() {
        return catCreateTime==null?null:(Date) catCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCatCreateTime(Date catCreateTime) {
        this.catCreateTime = catCreateTime == null ? null : (Date) catCreateTime.clone();
    }

    public String getCatModifiedName() {
        return catModifiedName;
    }

    public void setCatModifiedName(String catModifiedName) {
        this.catModifiedName = catModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getCatModifiedTime() {
        return catModifiedTime==null?null:(Date) catModifiedTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setCatModifiedTime(Date catModifiedTime) {
        this.catModifiedTime = catModifiedTime == null ? null : (Date) catModifiedTime.clone();
    }

    public String getCatDelName() {
        return catDelName;
    }

    public void setCatDelName(String catDelName) {
        this.catDelName = catDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getCatDelTime() {
        return catDelTime==null?null:(Date) catDelTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setCatDelTime(Date catDelTime) {
        this.catDelTime = catDelTime == null ? null : (Date) catDelTime.clone();
    }

    public BigDecimal getCatRate() {
        return catRate;
    }

    public void setCatRate(BigDecimal catRate) {
        this.catRate = catRate;
    }

    public String getCatModel() {
        return catModel;
    }

    public void setCatModel(String catModel) {
        this.catModel = catModel;
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