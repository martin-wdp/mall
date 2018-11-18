/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.service;

import java.util.List;

import com.ningpai.mobile.bean.MobAdver;
import com.ningpai.util.PageBean;

/**
 * SERVICE-移动版广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日上午10:32:19
 */
public interface MobAdverService {
    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    int batchDelMobAdver(List<Long> ids);

    /**
     * 删除
     * 
     * @param mobAdverId
     * @return
     */
    int deleteMobAdver(Long mobAdverId);

    /**
     * 修改
     * 
     * @param mobAdver
     * @return
     */
    int updateMobAdver(MobAdver mobAdver);

    /**
     * 添加
     * 
     * @param mobAdver
     * @return
     */
    int createMobAdver(MobAdver mobAdver);

    /**
     * 根据ID查询
     * 
     * @param mobAdverId
     * @return
     */
    MobAdver getMobAdver(Long mobAdverId);

    /**
     * 根据楼层ID分页查询未删除的广告列表,首页广告楼层ID为-1
     * 
     * @param pb
     * @param storeyId
     * @return
     */
    PageBean selectByStoreyIdAndPb(PageBean pb, Long storeyId);

    /**
     * 根据楼层ID查询未删除的广告数量,验证是否可删除楼层<br/>
     * 数量大于0返回false，不大于0返回true
     * 
     * @param storeyId
     * @return
     */
    boolean selectCountByStoreyId(Long storeyId);

    /**
     * 根据楼层ID查询未删除、已启用的广告,前台展示用,首页广告楼层ID为-1
     * 
     * @param storeyId
     * @return
     */
    List<MobAdver> selectByStoreyIdForSite(Long storeyId);

    /**
     * 修改移动版广告启用状态
     * 
     * @param mobAdverId
     * @return
     */
    int changeMobAdverUserdStatus(Long mobAdverId);
}
