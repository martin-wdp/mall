
package com.ningpai.marketing.bean;


public class RushCustomer {
    /**
     *  id
     */
    private Long rushCustomerId;

    /**
     *  抢购id
     */
    private Long rushId;

    /**
     *  会员账号id
     */
    private Long customerId;

    /**
     * 货品id
     */
    private Long goodsInfoId;

    /**
     *  商品数量
     */
    private int goodsNum;


    /**
     *  0:没有删除;1:删除
     */
    private String delFlag;


    public Long getRushCustomerId() {
        return rushCustomerId;
    }

    public void setRushCustomerId(Long rushCustomerId) {
        this.rushCustomerId = rushCustomerId;
    }

    public Long getRushId() {
        return rushId;
    }

    public void setRushId(Long rushId) {
        this.rushId = rushId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
