package com.ningpai.site.thirdseller.bean;

import java.math.BigDecimal;

/**
 * 第三方商品搜索bean
 * @author NingPai-liangck
 * @since 2014年9月1日 2014年13:59
 * @version 1.0
 */
public class ThirdGoodsSearchBean {
    /*搜索标题*/
    private String title="";
    private String showMode="0";
    /*排序字段*/
    private String sort="";
    /*仓库库存判断*/
    private String showStock="";
    /*地区ID*/
    private Long distinctId;
    /*分类ID*/
    private Long cateId;
    /*几个区间标记*/
    private long priceFlag;
    /*查询价格区间开始价格值*/
    private BigDecimal startPrice;
    /*查询价格区间结束价格值*/
    private BigDecimal endPrice;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShowMode() {
        return showMode;
    }
    public void setShowMode(String showMode) {
        this.showMode = showMode;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getShowStock() {
        return showStock;
    }
    public void setShowStock(String showStock) {
        this.showStock = showStock;
    }
    public Long getDistinctId() {
        return distinctId;
    }
    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }
    public Long getCateId() {
        return cateId;
    }
    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }
    public long getPriceFlag() {
        return priceFlag;
    }
    public void setPriceFlag(long priceFlag) {
        this.priceFlag = priceFlag;
    }
    public BigDecimal getStartPrice() {
        return startPrice;
    }
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }
    public BigDecimal getEndPrice() {
        return endPrice;
    }
    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }
    
}
