/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.bean;

import java.io.Serializable;
import java.util.Date;

/**  
 * @Description: np_app_market_key表的实体bean
 * @author Ningpai-HEHU
 * @date 2015-07-17 10:28:52
 * @version V1.0  
 */
public class AppMarketKey implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键id，自增
    */
    private Long id;

    /**
    *应用市场秘钥
    */
    private String appMarketKey;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *有效期截止时间
    */
    private Date endTime;

    /**
    *删除标记，0未删除，1已删除
    */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppMarketKey() {
        return appMarketKey;
    }

    public void setAppMarketKey(String appMarketKey) {
        this.appMarketKey = appMarketKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}