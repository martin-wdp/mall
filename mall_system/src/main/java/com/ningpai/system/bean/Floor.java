package com.ningpai.system.bean;

import java.util.Date;

/**
 * 楼层层数实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午11:09:36
 * @version 1.0
 */
public class Floor {
    /* 
     * 楼层层数ID
     */
    private Long floorId;
    /*
     *  楼层层数名称
     */
    private String floorName;
    /* 
     * 创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记
     */
    private String delFlag;

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
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
