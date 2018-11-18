/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 配送方式实体
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午5:58:24
 * @version 1.0
 */
public class Express {
    /*
     *  配送ID
     */
    private Long expid;
    /*
     *  配送公司
     */
    private String expCompany;
    /*
     *  运费
     */
    private BigDecimal expPrice;
    /*
     *  承运公司
     */
    private String expAccept;
    /*
     *  备注
     */
    private String expComment;
    /*
     *  创建时间
     */
    private Date createTime;
    /* 
     * 修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记
     */
    private String delFlag;
    /*
     * 快递100
     */
    private String kudi100code;

    public String getKudi100code() {
        return kudi100code;
    }

    public void setKudi100code(String kudi100code) {
        this.kudi100code = kudi100code;
    }

    public Long getExpid() {
        return expid;
    }

    public void setExpid(Long expid) {
        this.expid = expid;
    }

    public String getExpCompany() {
        return expCompany;
    }

    public void setExpCompany(String expCompany) {
        this.expCompany = expCompany;
    }

    public BigDecimal getExpPrice() {
        return expPrice;
    }

    public void setExpPrice(BigDecimal expPrice) {
        this.expPrice = expPrice;
    }

    public String getExpAccept() {
        return expAccept;
    }

    public void setExpAccept(String expAccept) {
        this.expAccept = expAccept;
    }

    public String getExpComment() {
        return expComment;
    }

    public void setExpComment(String expComment) {
        this.expComment = expComment;
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
