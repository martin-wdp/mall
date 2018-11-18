/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.util;

import com.ningpai.common.util.ConstantUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * 第三方商品查询辅助Bean
 * 
 * @since 2014年5月12日 下午5:02:31
 * @version 1.0
 */
public final class ThirdGoodsSearchBean {
    private String condition;
    //商品名称
    private String goodsName;
    //商品编号
    private String goodsNo;
    //类型ID
    private Long npCateId;
    //第三方类型ID
    private Long thirdCateId;
    //筛选的最低价格
    private BigDecimal lowPrice;
    //筛选的最高价格
    private BigDecimal highPrice;
    //筛选用的开始时间
    private String lowCreateTime;
    //筛选用的结束时间
    private String highCreateTime;
    private String showFlag = "1";
    //搜索条件
    private String searchText;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) throws UnsupportedEncodingException {
        this.goodsName = new String(goodsName.getBytes("ISO-8859-1"), ConstantUtil.UTF);
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Long getNpCateId() {
        return npCateId;
    }

    public void setNpCateId(Long npCateId) {
        this.npCateId = npCateId;
    }

    public Long getThirdCateId() {
        return thirdCateId;
    }

    public void setThirdCateId(Long thirdCateId) {
        this.thirdCateId = thirdCateId;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowCreateTime() {
        return lowCreateTime;
    }

    public void setLowCreateTime(String lowCreateTime) {
        this.lowCreateTime = lowCreateTime;
    }

    public String getHighCreateTime() {
        return highCreateTime;
    }

    public void setHighCreateTime(String highCreateTime) {
        this.highCreateTime = highCreateTime;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) throws UnsupportedEncodingException {
        this.searchText = new String(searchText.getBytes("ISO-8859-1"), ConstantUtil.UTF);
    }

}
