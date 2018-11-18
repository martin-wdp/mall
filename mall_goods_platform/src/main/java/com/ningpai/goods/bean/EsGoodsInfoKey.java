package com.ningpai.goods.bean;

/**
 * 货品主键信息
 * 
 * @author ggn
 *
 */
public class EsGoodsInfoKey {
    /**
     * 货品ID
     */
    private Long goodsInfoId;
    /**
     * 默认图片
     */
    private String goodsInfoImgId;

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
    }
}