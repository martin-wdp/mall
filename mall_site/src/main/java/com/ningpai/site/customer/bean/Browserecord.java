/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.bean;

import com.ningpai.site.customer.vo.GoodsBean;

import java.util.Date;

/**
 * 浏览记录Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月12日 下午4:34:18
 * @version 0.0.1
 */
public class Browserecord {
    /**
     * 浏览记录编号
     * 
     * @see #getLikeId()
     * @see #setLikeId(Long)
     */
    private Long likeId;
    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 货品编号
     * 
     * @see #getGoodsId()
     * @see #setGoodsId(Integer)
     */
    private Integer goodsId;
    /**
     * 添加时间
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
    /**
     * 对应商品
     * 
     * @see #getGoods()
     * @see #setGoods(GoodsBean)
     */
    private GoodsBean goods;

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getCreateTime() {
        //创建时间不为空
        if (this.createTime != null) {
            //转换为时间格式
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        //创建时间不为空
        if (createTime != null) {
            //获取创建时间
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
