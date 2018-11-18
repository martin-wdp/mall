package com.ningpai.marketing.bean;

import java.math.BigDecimal;

import com.ningpai.goods.vo.GoodsProductDetailVo;

/**促销
 * @author ggn
 *
 */
public class PreDiscountMarketing {
    /**
     * id
     */
    private Long discountId;
    /**
     * 促销id
     */
    private Long marketingId;
    /**
     * 促销折扣
     */
    private BigDecimal discountInfo;
    /**
     * 会员促销折扣
     */
    private BigDecimal vipdiscountInfo;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 货品id
     */
    private Long goodsId;
    /**
     * 是否抹掉分角 0 未抹掉 1 抹掉分 2 抹掉角
     */
    private String discountFlag;
    /**
     * 折后价
     */
    private BigDecimal discountPrice;
    /**
     * 会员折后价
     */
    private BigDecimal vipdiscountPrice;

    /**
     * 商品
     */
    private GoodsProductDetailVo goodsProduct;

    public BigDecimal getVipdiscountInfo() {
        return vipdiscountInfo;
    }

    public void setVipdiscountInfo(BigDecimal vipdiscountInfo) {
        this.vipdiscountInfo = vipdiscountInfo;
    }

    public BigDecimal getVipdiscountPrice() {
        return vipdiscountPrice;
    }

    public void setVipdiscountPrice(BigDecimal vipdiscountPrice) {
        this.vipdiscountPrice = vipdiscountPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public BigDecimal getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(BigDecimal discountInfo) {
        this.discountInfo = discountInfo;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(String discountFlag) {
        this.discountFlag = discountFlag;
    }

    public GoodsProductDetailVo getGoodsProduct() {
        return goodsProduct;
    }

    public void setGoodsProduct(GoodsProductDetailVo goodsProduct) {
        this.goodsProduct = goodsProduct;
    }
}
