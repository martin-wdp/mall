/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.vo;

import java.util.Date;

/**
 *  实体类--移动版单页vo
 *
 * @Title: Entity
 * @Description:
 * @author NINGPAI_fengal
 * @since 2015-08-26 17:43:53
 * @version V1.0
 */
public class MobSinglepageVo {
    /**
     * 编号
     * 
     * @see #getSinglepageId()
     * @see #setSinglepageId(Long)
     */
    private Long singlepageId;

    /**
     * 标题
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    private String title;

    /**
     * 是否显示 0:不显示 1：显示
     * 
     * @see #getIsShow()
     * @see #setIsShow(String)
     */
    private String isShow;

    /**
     * 更新时间
     * 
     * @see #getUpdateDate()
     * @see #setUpdateDate(java.util.Date)
     */
    private Date updateDate;

    /**
     * 标示名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    private String name;

    public Long getSinglepageId() {
        return singlepageId;
    }

    public void setSinglepageId(Long singlepageId) {
        this.singlepageId = singlepageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    /**
     * 获取更新时间
     *
     * @return
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置更新时间
     *
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
