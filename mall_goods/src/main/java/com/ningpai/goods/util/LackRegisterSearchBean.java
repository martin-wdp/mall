

package com.ningpai.goods.util;

/**
 * 到货通知高级查询Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月6日 下午6:16:18
 * @version 1.0
 */
public class LackRegisterSearchBean {
    /*
     *商品名称
      */
    private String goodsName;
    /*
     *货号
      */
    private String productNo;
    /*
     *通知装填
      */
    private String status;
    /*
     *登记开始时间
      */
    private String registerStartTime;
    /*
     *登记结束时间
      */
    private String registerEndTime;
    /*
     *标题查询
      */
    private String condition;
    /*
     *搜索值
      */
    private String searchText;
    /*
     *显示标记
      */
    private String showFlag;

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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterStartTime() {
        return registerStartTime;
    }

    public void setRegisterStartTime(String registerStartTime) {
        this.registerStartTime = registerStartTime;
    }

    public String getRegisterEndTime() {
        return registerEndTime;
    }
    /**
     * 设置登记结束时间
     * */
    public void setRegisterEndTime(String registerEndTime) {
        if (null != registerEndTime && registerEndTime.length() > 0) {
            this.registerEndTime = registerEndTime + " 23:59:59";
        } else {
            this.registerEndTime = registerEndTime;
        }
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

}
