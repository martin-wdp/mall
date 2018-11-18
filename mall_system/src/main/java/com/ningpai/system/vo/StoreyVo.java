/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.vo;

import java.util.Date;

/**
 * 楼层页面实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月4日 下午6:13:54
 * @version 1.0
 */
public class StoreyVo {

    /* 
     * 楼层ID
     */
    private Long seId;
    /* 
     * 商品分类ID
     */
    private Long catId;
    /* 
     * 广告图片
     */
    private String seImg;
    /* 
     * 图片链接
     */
    private String seHref;
    /* 
     * seo优化
     */
    private String seSeo;
    /*
     *  楼层数ID
     */
    private Long floorId;
    /* 
     * 创建时间
     */
    private Date createTime;
    /* 
     * 修改时间
     */
    private Date modifyTime;
    /*
     *  是否删除
     */
    private String delFlag;
    /*
     *  分类名称
     */
    private String catName;
    /*
     *  楼层层数名称
     */
    private String floorName;

    public String getSeSeo() {
        return seSeo;
    }

    public void setSeSeo(String seSeo) {
        this.seSeo = seSeo;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Long getSeId() {
        return seId;
    }

    public void setSeId(Long seId) {
        this.seId = seId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getSeImg() {
        return seImg;
    }

    public void setSeImg(String seImg) {
        this.seImg = seImg;
    }

    public String getSeHref() {
        return seHref;
    }

    public void setSeHref(String seHref) {
        this.seHref = seHref;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
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
     * 时间
     * @return
     */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
