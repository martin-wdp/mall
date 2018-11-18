/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品VO
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月24日 下午3:54:29
 */
public class GoodsListVo {
    /*
    *商品ID
     */
    private Long goodsId;
    /*
    *商品编号
     */
    private String goodsNo;
    /*
    *默认的商品图片
     */
    private String goodsImg;
    /*
    *商品名称
     */
    private String goodsName;
    /*
    *商品标签
     */
    private List<GoodsReleTagVo> tags;
    /*
    *库存
     */
    private Long stock;
    /*
    *商品类型
     */
    private GoodsTypeVo goodsType;
    /*
    *商品品牌
     */
    private GoodsBrand goodsBrand;
    /*
    *是否上架0不上架 1上架
     */
    private String goodsAdded;
    /*
     *销售价格
      */
    private BigDecimal goodsPrice;
    /*
     * 会员价格
     * 2015.10.22 wuyanbo 添加
     */
    private BigDecimal goodsVipPrice;
    /*
    *商品分类
     */
    private GoodsCate goodsCate;
    /*
    *商品所属的商家Id
     */
    private Long thirdId;
    /*
    *商品所属的商家名称
     */
    private String thirdName;
    /*
    *标记是否是第三方商品
     */
    private String isThird;
    /*
    *限购数量 for 众彩项目
     */
    private String maxBuyNum;
    /*
     *创建时间
      */
    private String goodsCreateTime;
    /*
    *第三方审核是否开启 0 不开启 1 开启
     */
    private String isThirdAuditUsed;
    /*
    *审核状态  0'未审核、审核通过' 1'审核中' 2'审核不通过'
     */
    private String auditStatus;
    /*
    *商品审核拒绝原因
     */
    private String refuseReason;
    /*
    *货品编号
     */
    private Long goodsInfoId;


    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(String goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<GoodsReleTagVo> getTags() {
        return tags;
    }

    public void setTags(List<GoodsReleTagVo> tags) {
        this.tags = tags;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public GoodsTypeVo getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsTypeVo goodsType) {
        this.goodsType = goodsType;
    }

    public GoodsBrand getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(GoodsBrand goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsAdded() {
        return goodsAdded;
    }

    public void setGoodsAdded(String goodsAdded) {
        this.goodsAdded = goodsAdded;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public GoodsCate getGoodsCate() {
        return goodsCate;
    }

    public void setGoodsCate(GoodsCate goodsCate) {
        this.goodsCate = goodsCate;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    public String getMaxBuyNum() {
        return maxBuyNum;
    }

    public void setMaxBuyNum(String maxBuyNum) {
        this.maxBuyNum = maxBuyNum;
    }

    public String getIsThirdAuditUsed() {
        return isThirdAuditUsed;
    }

    public void setIsThirdAuditUsed(String isThirdAuditUsed) {
        this.isThirdAuditUsed = isThirdAuditUsed;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
