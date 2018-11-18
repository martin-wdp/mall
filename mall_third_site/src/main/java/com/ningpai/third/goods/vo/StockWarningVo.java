/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.vo;

/**
 * <p>
 * 库存警告的实体类
 * </p>
 * 
 * @Author zhanghl
 * @since 2014年5月12日 下午5:02:31
 * @version 2.0
 */
public class StockWarningVo {
    // 货品的id
    private Long infoid;
    // 货品的名称
    private String goodname;
    // 货品的编号
    private String number;
    // 货品的库存
    private Long stock;
    // 货品进价
    private String buyprice;
    // 货品销售价格
    private String salprice;
    // 第三方库存预警值
    private Long swvalue;
    // 第三方店铺id
    private Long storeid;
    // 第三方id
    private Long thirdid;
    // 货品图片
    private String goodsInfoImgId;

    public Long getThirdid() {
        return thirdid;
    }

    public void setThirdid(Long thirdid) {
        this.thirdid = thirdid;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public Long getSwvalue() {
        return swvalue;
    }

    public void setSwvalue(Long swvalue) {
        this.swvalue = swvalue;
    }

    public Long getInfoid() {
        return infoid;
    }

    public void setInfoid(Long infoid) {
        this.infoid = infoid;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getSalprice() {
        return salprice;
    }

    public void setSalprice(String salprice) {
        this.salprice = salprice;
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

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
    }

}
