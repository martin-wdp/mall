package com.ningpai.api.bean;

/**
 *
 * 开发接口－－商品详情
 * @author  lih
 * @since 2015年08月25日13:53:38
 * @version 2.0
 *
 */
public class OGoodsProduct {
    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 货品名称
     */
    private String goodsInfoName;

    /**
     * 货品编号
     */
    private String goodsInfoItemNo;

    /**
     * 货品副标题
     */
    private String goodsInfoSubtitle;

    /**
     * 货品id
     */
    private Long goodsInfoId;

    /**
     * 货品重量
     */
    private String goodsInfoWeight;

    /**
     * 删除标记
     */
    private String goodsInfoDelflag;

    /**
     * 第三方标识
     */
    private Long thirdId;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 商品id
     */
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * 货品名称
     */
    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }
    /**
     * 货品编号
     */
    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }
    /**
     * 货品副标题
     */
    public String getGoodsInfoSubtitle() {
        return goodsInfoSubtitle;
    }

    public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
        this.goodsInfoSubtitle = goodsInfoSubtitle;
    }
    /**
     * 货品id
     */
    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }
    /**
     * 货品重量
     */
    public String getGoodsInfoWeight() {
        return goodsInfoWeight;
    }

    public void setGoodsInfoWeight(String goodsInfoWeight) {
        this.goodsInfoWeight = goodsInfoWeight;
    }
    /**
     * 删除标记
     */
    public String getGoodsInfoDelflag() {
        return goodsInfoDelflag;
    }

    public void setGoodsInfoDelflag(String goodsInfoDelflag) {
        this.goodsInfoDelflag = goodsInfoDelflag;
    }
    /**
     * 第三方标识
     */
    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
    /**
     * 分类名称
     */
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    /**
     * 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
