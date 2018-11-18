/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.dataanalysis.util;

import java.math.BigDecimal;
/**
 * 数据分析辅助类
 * @author zhangsl
 * @since 2014年12月16日 下午1:34:51
 * @version
 */
public class DataAnalysisUtil {
    
    //商家Id
    private Long thirdId;
    //商铺名称
    private String storeName;    
    //交易额
    private BigDecimal sumPrice;    
    //成功订单数量
    private Long orderCount;
    //收藏量
    private Long followCount;
    //总订单量
    private Long allCount;
    //转化率
    private BigDecimal takeRates;
    //商品销售数量
    private Long sumPro;
    public Long getSumPro() {
        return sumPro;
    }
    public void setSumPro(Long sumPro) {
        this.sumPro = sumPro;
    }
    public BigDecimal getTakeRates() {
        return takeRates;
    }
    public void setTakeRates(BigDecimal takeRates) {
        this.takeRates = takeRates;
    }
    public Long getAllCount() {
        return allCount;
    }
    public void setAllCount(Long allCount) {
        this.allCount = allCount;
    }
    public Long getThirdId() {
        return thirdId;
    }
    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
    public Long getFollowCount() {
        return followCount;
    }
    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public BigDecimal getSumPrice() {
        return sumPrice;
    }
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
    public Long getOrderCount() {
        return orderCount;
    }
    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
    
    
    
 
 
    
    
    

}
