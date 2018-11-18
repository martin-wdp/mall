/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.bean;

import java.io.Serializable;

/**  
 * @Description: np_app_server表的实体bean
 * @author Ningpai-HEHU
 * @date 2015-08-06 11:34:22
 * @version V1.0  
 */
public class AppServer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键id
    */
    private Long appServerId;

    /**
    *网站跟地址
    */
    private String appServerRoot;

    /**
    *1Boss，2商家，3前台
    */
    private String appServerType;

    /**
    *删除标记，0未删除，1已删除
    */
    private String delFlag;
    /**
     * 是否主机，1主机，2非主机
     */
    private String isMain;

    public Long getAppServerId() {
        return appServerId;
    }

    public void setAppServerId(Long appServerId) {
        this.appServerId = appServerId;
    }

    public String getAppServerRoot() {
        return appServerRoot;
    }

    public void setAppServerRoot(String appServerRoot) {
        this.appServerRoot = appServerRoot;
    }

    public String getAppServerType() {
        return appServerType;
    }

    public void setAppServerType(String appServerType) {
        this.appServerType = appServerType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }
}