/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.util.Date;

/**
 * 商品关注Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月19日 上午9:35:13
 * @version 1.0
 */
public class GoodsAtteVo {
    /*
     *主键ID
      */
    private Long atteId;
    /*
    *会员ID
     */
    private Long custId;
    /*
    *商品ID
     */
    private Long goodsId;
    /*
    *货品ID
     */
    private Long productId;
    /*
    *创建时间
     */
    private Date createTime;
    /*
    *修改时间
     */
    private Date modifyTime;
    /*
    *删除标记
     */
    private String flag;
    /*
    *会员的用户名
     */
    private String customerUsername;
    /*
    *会员手机
     */
    private String infoMobile;
    /*
    *会员邮箱
     */
    private String infoEmail;
    /*
    *库存状态
     */
    private Long goodsInfoStock;
    /*
    *商品名称
     */
    private String goodsName;
    /*
    *货号
     */
    private String goodsInfoItemNo;

    public Long getAtteId() {
        return atteId;
    }

    public void setAtteId(Long atteId) {
        this.atteId = atteId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 获取修改时间
     * */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getInfoMobile() {
        return infoMobile;
    }

    public void setInfoMobile(String infoMobile) {
        this.infoMobile = infoMobile;
    }

    public String getInfoEmail() {
        return infoEmail;
    }

    public void setInfoEmail(String infoEmail) {
        this.infoEmail = infoEmail;
    }

    public Long getGoodsInfoStock() {
        return goodsInfoStock;
    }

    public void setGoodsInfoStock(Long goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

}
