/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.ningpai.site.customer.vo.GoodsBean;
import com.ningpai.site.goods.util.ProductCommentUtilBean;

/**
 * 关注信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月17日 下午4:23:28
 * @version 0.0.1
 */
public class CustomerFollow {
    /**
     * 关注编号
     * 
     * @see #setFollowId(Long)
     * @see #getFollowId()
     */
    private Long followId;
    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 商品编号
     * 
     * @see #getGoodsId()
     * @see #setGoodsId(Long)
     */
    private Long goodsId;
    /**
     * 关注时价格
     * 
     * @see #getFollowPrice()
     * @see #setFollowPrice(BigDecimal)
     */
    private BigDecimal followPrice;
    /**
     * 关注标签
     * 
     * @see #getFollowTag()
     * @see #setFollowTag(String)
     */
    private String followTag;
    /**
     * 创建时间
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 删除标记
     * 
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;

    private GoodsBean good;

    /**
     * 保存关注商品的评分情况
     */
    private ProductCommentUtilBean utilBean;

    public GoodsBean getGood() {
        return good;
    }

    public void setGood(GoodsBean good) {
        this.good = good;
    }

    /**
     * 获取followId的值
     *
     * @return {@link com.ningpai.customer.bean.CustomerFollow#followId}
     */
    public ProductCommentUtilBean getUtilBean() {
        return utilBean;
    }

    /**
     * 设置followId的值
     *
     * {@link com.ningpai.customer.bean.CustomerFollow#followId}
     */
    public void setUtilBean(ProductCommentUtilBean utilBean) {
        this.utilBean = utilBean;
    }

    /**
     * 获取followId的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#followId}
     */
    public Long getFollowId() {
        return followId;
    }

    /**
     * 设置followId的值
     * 
     * @param followId
     *            {@link com.ningpai.customer.bean.CustomerFollow#followId}
     */
    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    /**
     * 获取customerId的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#customerId}
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置customerId的值
     * 
     * @param customerId
     *            {@link com.ningpai.customer.bean.CustomerFollow#customerId}
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取goodsId的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#goodsId}
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置goodsId的值
     * 
     * @param goodsId
     *            {@link com.ningpai.customer.bean.CustomerFollow#goodsId}
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取followPrice的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#followPrice}
     */
    public BigDecimal getFollowPrice() {
        return followPrice;
    }

    /**
     * 设置followPrice的值
     * 
     * @param followPrice
     *            {@link com.ningpai.customer.bean.CustomerFollow#followPrice}
     */
    public void setFollowPrice(BigDecimal followPrice) {
        this.followPrice = followPrice;
    }

    /**
     * 获取followTag的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#followTag}
     */
    public String getFollowTag() {
        return followTag;
    }

    /**
     * 设置followTag的值
     * 
     * @param followTag
     *            {@link com.ningpai.customer.bean.CustomerFollow#followTag}
     */
    public void setFollowTag(String followTag) {
        this.followTag = followTag;
    }

    /**
     * 获取createTime的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 获取createTime的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#createTime}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 获取delFlag的值
     * 
     * @return {@link com.ningpai.customer.bean.CustomerFollow#delFlag}
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     * 
     * @param delFlag
     *            {@link com.ningpai.customer.bean.CustomerFollow#delFlag}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
