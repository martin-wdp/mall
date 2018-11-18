/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

/**
 * 图片分类实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:21:00
 * @version 1.0
 */
public class ImageCate {
    /* 
     * 图片分类ID
     */
    private Long imgcateId;
    /*
     *  图片分类名称
     */
    private String imgcateName;
    /*
     *  创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;

    public Long getImgcateId() {
        return imgcateId;
    }

    public void setImgcateId(Long imgcateId) {
        this.imgcateId = imgcateId;
    }

    public String getImgcateName() {
        return imgcateName;
    }

    public void setImgcateName(String imgcateName) {
        this.imgcateName = imgcateName;
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
}
