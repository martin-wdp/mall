/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

/**
 * 楼层标签实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月7日 上午9:51:17
 * @version 1.0
 */
public class Remark {
   /* 
    * 标签ID
    */
    private Long remarkId;
   /*
    *  标签名称
    */
    private String remarkName;
   /* 
    * 楼层ID
    */
    private Long seId;
   /*
    *  标签图片
    */
    private String remarkImg;
   /* 
    * 标签路径
    */
    private String remarkHref;
   /*
    *  位置
    */
    private Long pId;
   /*
    *  创建时间
    */
    private Date createTime;
   /*
    *  修改时间
    */
    private Date modifyTime;
   /*
    *  是否删除
    */
    private String delFlag;

    public Long getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(Long remarkId) {
        this.remarkId = remarkId;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public Long getSeId() {
        return seId;
    }

    public void setSeId(Long seId) {
        this.seId = seId;
    }

    public String getRemarkImg() {
        return remarkImg;
    }

    public void setRemarkImg(String remarkImg) {
        this.remarkImg = remarkImg;
    }

    public String getRemarkHref() {
        return remarkHref;
    }

    public void setRemarkHref(String remarkHref) {
        this.remarkHref = remarkHref;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
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
}
