/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.service;

import java.util.List;

import com.ningpai.mobile.bean.MobStorey;
import com.ningpai.util.PageBean;

/**
 * SERVICE-移动版楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午7:19:25
 */
public interface MobStoreyService {
    /**
     * 删除楼层
     * 
     * @param mobStoreyId
     * @return
     */
    int deleteMobStorey(Long mobStoreyId);

    /**
     * 添加楼层
     * 
     * @param mobStorey
     * @return
     */
    int createMobStorey(MobStorey mobStorey);

    /**
     * 修改楼层
     * 
     * @param mobStorey
     * @return
     */
    int updateMobStorey(MobStorey mobStorey);

    /**
     * 查看楼层
     * 
     * @param mobStoreyId
     * @return
     */
    MobStorey getMobStorey(Long mobStoreyId);

    /**
     * 分页查询楼层
     * 
     * @param pb
     * @return
     */
    PageBean selectMobStoreyByPb(PageBean pb);

    /**
     * 查询所有楼层
     * 
     * @return
     */
    List<MobStorey> selectMobStoreyForSite();

    /**
     * 修改楼层启用状态
     * 
     * @param mobStoreyId
     * @return
     */
    int changeMobStoreyUserdStatus(Long mobStoreyId);
}
