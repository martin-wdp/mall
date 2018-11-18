/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.util.Date;
import java.util.List;

import com.ningpai.goods.bean.WareCity;

/**
 * 仓库信息的VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午3:31:21
 * @version 1.0
 */
public class WareHouseVo {
    /*
     *仓库ID
      */
    private Long wareId;
    /*
     *仓库名称
      */
    private String wareName;
    /*
     *仓库描述
      */
    private String wareRemark;
    /*
     *仓库管理员
      */
    private Long wareAdmin;
    /*
     *仓库地址
      */
    private String wareAddress;
    /*
     *创建时间
      */
    private Date createTime;
    /*
    *修改时间
     */
    private Date modifiedTime;
    /*
     *删除标记 0:未删除 1:已删除
      */
    private String delFlag;
    /*
     *操作人名称
      */
    private String execName;
    
    /*
    *仓库识别编号
     */
    private String identifyId;

    private List<WareCity> distincts;


    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWareRemark() {
        return wareRemark;
    }

    public void setWareRemark(String wareRemark) {
        this.wareRemark = wareRemark;
    }

    public Long getWareAdmin() {
        return wareAdmin;
    }

    public void setWareAdmin(Long wareAdmin) {
        this.wareAdmin = wareAdmin;
    }

    public String getWareAddress() {
        return wareAddress;
    }

    public void setWareAddress(String wareAddress) {
        this.wareAddress = wareAddress;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
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
     * */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setModifiedTime(Date modifiedTime) {
        if (modifiedTime != null) {
            Date tEmp = (Date) modifiedTime.clone();
            if (tEmp != null) {
                this.modifiedTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getExecName() {
        return execName;
    }

    public void setExecName(String execName) {
        this.execName = execName;
    }

    public List<WareCity> getDistincts() {
        return distincts;
    }

    public void setDistincts(List<WareCity> distincts) {
        this.distincts = distincts;
    }

}
