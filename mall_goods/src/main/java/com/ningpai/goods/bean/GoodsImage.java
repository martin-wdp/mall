package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 货品关联图片表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月3日 下午3:58:30
 * @version 1.0
 */
public class GoodsImage {
    /*
     * 主键ID
     */
    private Long goodsImgId;
    /*
     * 货品ID
     */
    private Long goodsInfoId;
    /*
     * 中图图片路径
     */
    private String imageInName;
    /*
     * 小图图片路径
     */
    private String imageThumName;
    /*
     * 大图图片路径
     */
    private String imageBigName;
    /*
     * 原图图片路径
     */
    private String imageArtworkName;
    /*
     * 排序
     */
    @NotNull(message = "排序必须为正整数.")
    private Integer goodsImgSort;
    /*
     * 删除标记 0：不删除 1：删除
     */
    private String goodsImgDelflag;
    /*
     * 创建人名称
     */
    private String goodsImgCreateName;
    /*
     * 创建时间
     */
    private Date goodsImgCreateTime;
    /*
     * 修改人名称
     */
    private String goodsImgModifiedName;
    /*
     * 修改时间
     */
    private Date goodsImgModifiedTime;
    /*
     * 删除人名称
     */
    private String goodsImgDelName;
    /*
     * 删除时间
     */
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
        if (this.goodsImgCreateTime != null) {
            return new Date(this.goodsImgCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setGoodsImgCreateTime(Date goodsImgCreateTime) {
        if (goodsImgCreateTime != null) {
            Date tEmp = (Date) goodsImgCreateTime.clone();
            if (tEmp != null) {
                this.goodsImgCreateTime = tEmp;
            }
        }
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
        if (this.goodsImgModifiedTime != null) {
            return new Date(this.goodsImgModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setGoodsImgModifiedTime(Date goodsImgModifiedTime) {
        if (goodsImgModifiedTime != null) {
            Date tEmp = (Date) goodsImgModifiedTime.clone();
            if (tEmp != null) {
                this.goodsImgModifiedTime = tEmp;
            }
        }
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
        if (this.goodsImgDelTime != null) {
            return new Date(this.goodsImgDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setGoodsImgDelTime(Date goodsImgDelTime) {
        if (goodsImgDelTime != null) {
            Date tEmp = (Date) goodsImgDelTime.clone();
            if (tEmp != null) {
                this.goodsImgDelTime = tEmp;
            }
        }
    }
}
