/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.main.vo;

import java.util.List;

import com.ningpai.mobile.bean.MobAdver;
import com.ningpai.mobile.bean.MobStorey;

/**
 * VO类-移动版楼层数据封装类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月22日下午2:03:15
 */
public class MobStoreyInfo {
    /** 楼层对象 */
    private MobStorey mobStorey;

    /** 楼层广告集合 */
    private List<MobAdver> storeyAdvers;

    public MobStorey getMobStorey() {
        return mobStorey;
    }

    public void setMobStorey(MobStorey mobStorey) {
        this.mobStorey = mobStorey;
    }

    public List<MobAdver> getStoreyAdvers() {
        return storeyAdvers;
    }

    public void setStoreyAdvers(List<MobAdver> storeyAdvers) {
        this.storeyAdvers = storeyAdvers;
    }

}
