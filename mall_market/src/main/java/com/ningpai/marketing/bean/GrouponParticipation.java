/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.util.Date;

/**
 * 团购进度
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月31日10:19:07
 * 
 */
public class GrouponParticipation {
    /*
     * 进度id
     */
    private Long participationId;

    /*
     * 客户id
     */
    private Long custId;

    /*
     * 团购id
     */
    private Long grouponId;

    /*
     * 创建时间
     */
    private Date createTime;

    /*
     * 删除标记
     */
    private String flag;

    /*
     * 备用字段1
     */
    private String temp1;

    /*
     * 备用字段2
     */
    private String temp2;

    /*
     * 　备用字段3
     */
    private String temp3;

    public Long getParticipationId() {
        return participationId;
    }

    public void setParticipationId(Long participationId) {
        this.participationId = participationId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(Long grouponId) {
        this.grouponId = grouponId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }
}
