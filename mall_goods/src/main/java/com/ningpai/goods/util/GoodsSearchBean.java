
package com.ningpai.goods.util;

import java.math.BigDecimal;

/**
 * 商品列表的高级搜索Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月6日 下午2:38:34
 * @version
 */
public class GoodsSearchBean {
    /*
     *商品名称
      */
    private String goodsName = "";
    /*
    *查询标记
     */
    private String condition = "";
    /*
    *查询文本
     */
    private String searchText = "";
    /*
    *商品编号
     */
    private String goodsNo = "";
    /*
     *商品关键字
      */
    private String goodsKeyword = "";
    /*
     *商品分类ID
      */
    private String goodsCateId = "-1";
    /*
     *商品品牌ID
      */
    private String goodsBrandId = "-1";
    /*
     *商品状态 0：未上架 1：已上架
      */
    private String status = "-1";
    /*
     *商品审核状态
      */
    private String auditStatus;
    /*
     *搜索框展示标记
      */
    private String showFlag = "0";

    /*
     *是否是第三方标记
      */
    private String isThird = "0";

    /*
     *第三方商家名称
      */
    private String thirdName = "";

    /*
     *货品编号
      */
    private String goodsInfoItemNo;

    /*
     *货品状态 是否上架
      */
    private String goodsInfoAdded;

    /*
     *货品价格区间 低价
      */
    private BigDecimal lowGoodsInfoPrice;

    /*
     *货品价格区间高价
      */
    private BigDecimal highGoodsInfoPrice;

    /*
     *第三方商家ID
      */
    private String thirdId;

    /*
     * 查询类型
      */
    private String queryStatus;

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsKeyword() {
        return goodsKeyword;
    }

    public void setGoodsKeyword(String goodsKeyword) {
        this.goodsKeyword = goodsKeyword;
    }

    public String getGoodsCateId() {
        return goodsCateId;
    }

    public void setGoodsCateId(String goodsCateId) {
        this.goodsCateId = goodsCateId;
    }

    public String getGoodsBrandId() {
        return goodsBrandId;
    }

    public void setGoodsBrandId(String goodsBrandId) {
        this.goodsBrandId = goodsBrandId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded;
    }

    public BigDecimal getLowGoodsInfoPrice() {
        return lowGoodsInfoPrice;
    }

    public void setLowGoodsInfoPrice(BigDecimal lowGoodsInfoPrice) {
        this.lowGoodsInfoPrice = lowGoodsInfoPrice;
    }

    public BigDecimal getHighGoodsInfoPrice() {
        return highGoodsInfoPrice;
    }

    public void setHighGoodsInfoPrice(BigDecimal highGoodsInfoPrice) {
        this.highGoodsInfoPrice = highGoodsInfoPrice;
    }

    public String getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }
}
