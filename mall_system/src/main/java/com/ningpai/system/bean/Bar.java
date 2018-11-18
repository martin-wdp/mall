/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

/**
 * 前台导航设置实体类
 * @author NINGPAI-LiHaoZe 
 * @since 2013年12月19日 下午4:01:32 
 * @version 1.0
 */
public class Bar {
    /*
     * 导航ID
     */
    private Long barId;
    /*
     * 导航名称
     */
    private String barName;
    /*
     * 广告方位
     */
    private Integer barPosition;
    /*
     * 导航地址
     */
    private String barUrl;
    /*
     * 导航排序
     */
    private Integer barSort;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifyTime;
    /*
     * 删除标记
     */
    private String delFlag;

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getBarUrl() {
        return barUrl;
    }

    public void setBarUrl(String barUrl) {
        this.barUrl = barUrl;
    }

    public Integer getBarSort() {
        return barSort;
    }

    public void setBarSort(Integer barSort) {
        this.barSort = barSort;
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
    
    public Integer getBarPosition() {
        return barPosition;
    }

    public void setBarPosition(Integer barPosition) {
        this.barPosition = barPosition;
    }

}
