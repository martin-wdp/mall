/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.util.Date;

/**
 * 
 * @author AthrunNatu 促销规则
 * @since 2014年3月19日上午10:53:47
 */

public class Codex {
    /*
     * 规则ID
     */
    private Long codexId;
    /*
     * 规则名称
     */
    private String codexName;
    /*
     * 规则秒速
     */
    private String codexDes;
    /*
     * 规则秒速
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modTime;
    /*
     * 标记
     */
    private String flag;
    /*
     * 规则类型 1直降 2买送赠品
     */
    private String codexType;
    /*
     * 图片
     */
    private String codexImg;

    /*
     * 优惠分组外键
     */
    private Long codexStatus;

    public String getCodexImg() {
        return codexImg;
    }

    public void setCodexImg(String codexImg) {
        this.codexImg = codexImg;
    }

    public Long getCodexStatus() {
        return codexStatus;
    }

    public void setCodexStatus(Long codexStatus) {
        this.codexStatus = codexStatus;
    }

    public Long getCodexId() {
        return codexId;
    }

    public void setCodexId(Long codexId) {
        this.codexId = codexId;
    }

    public String getCodexName() {
        return codexName;
    }

    public void setCodexName(String codexName) {
        this.codexName = codexName;
    }

    public String getCodexDes() {
        return codexDes;
    }

    public void setCodexDes(String codexDes) {
        this.codexDes = codexDes;
    }

    /**
     * 创建时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 设置时间
     * @param createTime
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
     * 获取修改时间
     * @return
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * @param modTime
     */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date tEmp = (Date) modTime.clone();
            if (tEmp != null) {
                this.modTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCodexType() {
        return codexType;
    }

    public void setCodexType(String codexType) {
        this.codexType = codexType;
    }

}
