/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

/**
 * 默认地址
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月10日09:57:07
 *
 */
public class ProvinceDefault {
    /*
     * 主键
     */
    private Long dId;

    /*
     * 默认的地区id
     */
    private Long districtId;

    /*
     * 创建时间
     */
    private Date createTime;

    /*
     * 删除标记
     */
    private String deFalg;

    /*
     * 修改时间
     */
    private Date modifyTime;

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeFalg() {
        return deFalg;
    }

    public void setDeFalg(String deFalg) {
        this.deFalg = deFalg;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
