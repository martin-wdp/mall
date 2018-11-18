package com.ningpai.goods.bean;

import java.util.Date;

/**
 * 商品关联商品标签
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午4:13:35
 * @version 1.0
 */
public class GoodsReleTag {
    /*
     * 主键ID
     */
    private Long relaTagId;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 标签ID
     */
    private Long tagId;
    /*
     * 删除标记
     */
    private String relaTagDelflag;
    /*
     * 创建人名称
     */
    private String relaTagCreateName;
    /*
     * 创建时间
     */
    private Date relaTagCreateTime;
    /*
     * 删除人名称
     */
    private String relaTagDelName;
    /*
     * 删除时间
     */
    private Date relaTagDelTime;

    public Long getRelaTagId() {
        return relaTagId;
    }

    public void setRelaTagId(Long relaTagId) {
        this.relaTagId = relaTagId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getRelaTagDelflag() {
        return relaTagDelflag;
    }

    public void setRelaTagDelflag(String relaTagDelflag) {
        this.relaTagDelflag = relaTagDelflag;
    }

    public String getRelaTagCreateName() {
        return relaTagCreateName;
    }

    public void setRelaTagCreateName(String relaTagCreateName) {
        this.relaTagCreateName = relaTagCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getRelaTagCreateTime() {
        if (this.relaTagCreateTime != null) {
            return new Date(this.relaTagCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setRelaTagCreateTime(Date relaTagCreateTime) {
        if (relaTagCreateTime != null) {
            Date tEmp = (Date) relaTagCreateTime.clone();
            if (tEmp != null) {
                this.relaTagCreateTime = tEmp;
            }
        }
    }

    public String getRelaTagDelName() {
        return relaTagDelName;
    }

    public void setRelaTagDelName(String relaTagDelName) {
        this.relaTagDelName = relaTagDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getRelaTagDelTime() {
        if (this.relaTagDelTime != null) {
            return new Date(this.relaTagDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setRelaTagDelTime(Date relaTagDelTime) {
        if (relaTagDelTime != null) {
            Date tEmp = (Date) relaTagDelTime.clone();
            if (tEmp != null) {
                this.relaTagDelTime = tEmp;
            }
        }
    }
}
