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

/**
 * VO类-首页数据封装类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月22日下午2:01:13
 */
public class MainInfo {
    /** 轮播大广告集合 */
    private List<MobAdver> bigMobAdvers;
    /** 楼层数据封装类集合 */
    private List<MobStoreyInfo> mobStoreyInfos;

    public List<MobAdver> getBigMobAdvers() {
        return bigMobAdvers;
    }

    public void setBigMobAdvers(List<MobAdver> bigMobAdvers) {
        this.bigMobAdvers = bigMobAdvers;
    }

    public List<MobStoreyInfo> getMobStoreyInfos() {
        return mobStoreyInfos;
    }

    public void setMobStoreyInfos(List<MobStoreyInfo> mobStoreyInfos) {
        this.mobStoreyInfos = mobStoreyInfos;
    }

}
