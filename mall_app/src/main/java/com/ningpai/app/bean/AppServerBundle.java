/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.bean;

import java.io.Serializable;

/**  
 * @Description: np_app_server_bundle表的实体bean
 * @author Ningpai-Heh
 * @date 2015-08-18 13:14:42
 * @version V1.0  
 */
public class AppServerBundle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键id
    */
    private Long serverBundleId;

    /**
    *服务器ID
    */
    private Long serverId;

    /**
    *BundleId
    */
    private Long bundleId;

    /**
    *应用ID
    */
    private Long appId;

    public Long getServerBundleId() {
        return serverBundleId;
    }

    public void setServerBundleId(Long serverBundleId) {
        this.serverBundleId = serverBundleId;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}