package com.ningpai.goods.bean;

import java.util.Date;

/**
 * 商品关联商品表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午4:28:28
 * @version 1.0
 */
public class GoodsRelatedGoods {
    /*
     * 主键ID
     */
    private Long relatedId;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 关联的商品ID
     */
    private Long relaGoodsId;
    /*
     * 创建人名称
     */
    private String relaCreateName;
    /*
     * 创建时间
     */
    private Date relaCreateTime;
    /*
     * 删除人名称
     */
    private String relaDelName;
    /*
     * 删除时间
     */
    private Date relaDelTime;
    /*
     * 删除标记
     */
    private String relaDelflag;

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getRelaGoodsId() {
        return relaGoodsId;
    }

    public void setRelaGoodsId(Long relaGoodsId) {
        this.relaGoodsId = relaGoodsId;
    }

    public String getRelaCreateName() {
        return relaCreateName;
    }

    public void setRelaCreateName(String relaCreateName) {
        this.relaCreateName = relaCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getRelaCreateTime() {
        if (this.relaCreateTime != null) {
            return new Date(this.relaCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setRelaCreateTime(Date relaCreateTime) {
        if (relaCreateTime != null) {
            Date tEmp = (Date) relaCreateTime.clone();
            if (tEmp != null) {
                this.relaCreateTime = tEmp;
            }
        }
    }

    public String getRelaDelName() {
        return relaDelName;
    }

    public void setRelaDelName(String relaDelName) {
        this.relaDelName = relaDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getRelaDelTime() {
        if (this.relaDelTime != null) {
            return new Date(this.relaDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setRelaDelTime(Date relaDelTime) {
        if (relaDelTime != null) {
            Date tEmp = (Date) relaDelTime.clone();
            if (tEmp != null) {
                this.relaDelTime = tEmp;
            }
        }
    }

    public String getRelaDelflag() {
        return relaDelflag;
    }

    public void setRelaDelflag(String relaDelflag) {
        this.relaDelflag = relaDelflag;
    }
}
