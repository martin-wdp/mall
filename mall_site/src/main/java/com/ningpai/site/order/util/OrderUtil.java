/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.order.util;

import java.util.List;

import com.ningpai.marketing.bean.Marketing;

/**
 * 用来提交订单是存放订单优惠列表
 * 
 * @author NINGPAI-LIH
 * 
 */
public class OrderUtil {

    // 第三方标示
    private long thirdId;

    // 订单优惠
    private Marketing marketing;

    // 全部订单信息
    private List<Marketing> marketings;

    // 商家名称
    private String infoRealname;

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname;
    }

    public long getThirdId() {
        return thirdId;
    }

    public void setThirdId(long thirdId) {
        this.thirdId = thirdId;
    }

    public Marketing getMarketing() {
        return marketing;
    }

    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
    }

    public List<Marketing> getMarketings() {
        return marketings;
    }

    public void setMarketings(List<Marketing> marketings) {
        this.marketings = marketings;
    }

}
