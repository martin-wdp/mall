/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.service;

import java.util.List;

import com.ningpai.mobile.bean.MobSinglepageMark;

/**
 * 移动版单页标签service层接口
 * 
 * @author zhangsl
 * @since 2014年11月24日 上午10:58:46
 * @version 0.0.1
 */
public interface MobSinglepageMarkService {
    /**
     * 查询所有移动版单页标签信息列表
     * 
     * @return
     */
    List<MobSinglepageMark> selectAllMarkInfo();

    /**
     * 添加移动版单页标签信息
     * 
     * @param record
     * @return
     */
    int insertSelective(MobSinglepageMark record);

    /**
     * 根据主键ID查询移动版单页标签信息
     * 
     * @param markId
     * @return
     */
    MobSinglepageMark selectMobMarkById(Long markId);

    /**
     * 根据主键ID更新移动版单页标签信息
     * 
     * @param mobSinglepageMark
     * @return
     */
    int updateMobMarkById(MobSinglepageMark mobSinglepageMark);

    /**
     * 根据主键ID逻辑删除 修改delflag的状态 0：未删除 1：已删除
     * 
     * @param markId
     * @return
     */
    int updateDelStatus(Long markId);

    /**
     * 查询删除状态为不删除的所有移动版单页标签信息列表
     * 
     * @return
     */
    List<MobSinglepageMark> queryAllMarkInfoByDel();

    /**
     * 验证name是否存在
     * 
     * @param name
     * @return 0:不存在 1：存在
     */
    int checkNameExist(String name);
}
