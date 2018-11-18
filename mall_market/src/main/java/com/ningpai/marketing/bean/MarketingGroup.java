/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

/**
 * 优惠分组实体
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月6日09:48:10
 */
public class MarketingGroup {
    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 分组名称
     */
    private String preferentialName;
    /**
     * 是否显示该分组
     */
    private String isShow;

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPreferentialName() {
        return preferentialName;
    }

    public void setPreferentialName(String preferentialName) {
        this.preferentialName = preferentialName;
    }
}
