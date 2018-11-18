package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 货品图片信息
 * 
 * @author ggn
 *
 */
@ESDocObject
public class EsGoodsImage implements IESMappingType {
    /**
     * 图片ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long goodsImgId;
    /**
     * 货品ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long goodsInfoId;
    /**
     * 小图
     */
    @ESField()
    private String imageInName;
    /**
     * 中图
     */
    @ESField()
    private String imageThumName;
    /**
     * 大图
     */
    @ESField()
    private String imageBigName;
    /**
     * 原图
     */
    @ESField()
    private String imageArtworkName;
    /**
     * 排序
     */
    @ESField(fieldType = ESFieldType.INTEGER)
    private Integer goodsImgSort;
    /**
     * 是否删除
     */
    @ESField()
    private String goodsImgDelflag;
    /**
     * 创建时间
     */
    @ESField()
    private String goodsImgCreateName;
    /**
     * 创建时间
     */
    private Date goodsImgCreateTime;
    /**
     * 修改时间
     */
    @ESField()
    private String goodsImgModifiedName;
    /**
     * 修改时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsImgModifiedTime;
    /**
     * 删除时间
     */
    @ESField()
    private String goodsImgDelName;
    /**
     * 删除时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsImgDelTime;

    public Long getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(Long goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getImageInName() {
        return imageInName;
    }

    public void setImageInName(String imageInName) {
        this.imageInName = imageInName;
    }

    public String getImageThumName() {
        return imageThumName;
    }

    public void setImageThumName(String imageThumName) {
        this.imageThumName = imageThumName;
    }

    public String getImageBigName() {
        return imageBigName;
    }

    public void setImageBigName(String imageBigName) {
        this.imageBigName = imageBigName;
    }

    public String getImageArtworkName() {
        return imageArtworkName;
    }

    public void setImageArtworkName(String imageArtworkName) {
        this.imageArtworkName = imageArtworkName;
    }

    public Integer getGoodsImgSort() {
        return goodsImgSort;
    }

    public void setGoodsImgSort(Integer goodsImgSort) {
        this.goodsImgSort = goodsImgSort;
    }

    public String getGoodsImgDelflag() {
        return goodsImgDelflag;
    }

    public void setGoodsImgDelflag(String goodsImgDelflag) {
        this.goodsImgDelflag = goodsImgDelflag;
    }

    public String getGoodsImgCreateName() {
        return goodsImgCreateName;
    }

    public void setGoodsImgCreateName(String goodsImgCreateName) {
        this.goodsImgCreateName = goodsImgCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getGoodsImgCreateTime() {
        return goodsImgCreateTime==null?null:(Date) goodsImgCreateTime.clone();
    }

    /**
     * 设置创建时间
     * */
    public void setGoodsImgCreateTime(Date goodsImgCreateTime) {
        this.goodsImgCreateTime = goodsImgCreateTime == null ? null : (Date) goodsImgCreateTime.clone();
    }

    public String getGoodsImgModifiedName() {
        return goodsImgModifiedName;
    }

    public void setGoodsImgModifiedName(String goodsImgModifiedName) {
        this.goodsImgModifiedName = goodsImgModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getGoodsImgModifiedTime() {
        return goodsImgModifiedTime==null?null:(Date) goodsImgModifiedTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setGoodsImgModifiedTime(Date goodsImgModifiedTime) {
        this.goodsImgModifiedTime = goodsImgModifiedTime == null ? null : (Date) goodsImgModifiedTime.clone();
    }

    public String getGoodsImgDelName() {
        return goodsImgDelName;
    }

    public void setGoodsImgDelName(String goodsImgDelName) {
        this.goodsImgDelName = goodsImgDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getGoodsImgDelTime() {
        return goodsImgDelTime==null?null:(Date) goodsImgDelTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setGoodsImgDelTime(Date goodsImgDelTime) {
        this.goodsImgDelTime = goodsImgDelTime == null ? null : (Date) goodsImgDelTime.clone();
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
     * @see com.ningpai.searchplatform.bean.IESMappingType#generateDocId()
     */
    @Override
    public String generateDocId() {
        return this.getGoodsImgId() == null ? "" : this.getGoodsImgId().toString();
    }
}