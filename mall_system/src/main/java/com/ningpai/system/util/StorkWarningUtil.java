/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.util;

import java.util.List;

/**
 * 查询货品辅助bean
 * 
 * @author xiaogu
 *
 */
public class StorkWarningUtil {
    // 货品的id
    private Long id;
    // 货品的名称
    private String goodname;
    // 货品的价格
    private String preferPrice;
    // 货品的图片
    private String imgId;
    // 货品的编号
    private String number;
    // 货品的库存
    private Long stock;
    // 商家
    private String storename;
    // 地区
    private String warename;
    // 仓库的id
    private Long wareid;
    // 第三方id
    private Long thirdid;
    // 仓库的主键
    private Long stockid;
    // 规格类
    List<StockWarnSpec> stockWarnSpec;
    //商品名字
    private String goodsName;
    //商品no
    private String goodsNo;
    //是否是第三方
    private String isThird;

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
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

    /**
     * 规格类getter
     * @return
     */
    public List<StockWarnSpec> getStockWarnSpec() {
        return stockWarnSpec;
    }

    /**
     * 规格类setter
     * @param stockWarnSpec
     */
    public void setStockWarnSpec(List<StockWarnSpec> stockWarnSpec) {
        this.stockWarnSpec = stockWarnSpec;
    }

    public Long getStockid() {
        return stockid;
    }

    public void setStockid(Long stockid) {
        this.stockid = stockid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getWarename() {
        return warename;
    }

    public void setWarename(String warename) {
        this.warename = warename;
    }

    public Long getWareid() {
        return wareid;
    }

    public void setWareid(Long wareid) {
        this.wareid = wareid;
    }

    public Long getThirdid() {
        return thirdid;
    }

    public void setThirdid(Long thirdid) {
        this.thirdid = thirdid;
    }

    public String getPreferPrice() {
        return preferPrice;
    }

    public void setPreferPrice(String preferPrice) {
        this.preferPrice = preferPrice;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
