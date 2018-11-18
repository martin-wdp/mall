/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.dao;

import java.util.List;

import com.ningpai.mobile.bean.MobHomeSinglePage;

/**
 * 移动版单页可视化配置 dao层接口
 * 
 * @author zhangsl
 * @since 2014年12月5日 下午2:25:41
 * @version 0.0.1
 */
public interface MobHomeSinglePageMapper {
    /**
     * @Title: deleteByPrimaryKey
     * @Description: 根据ID删除
     * @param homepageId
     * @return
     */
    int deleteByPrimaryKey(Long homepageId);

    /**
     * @Title: insert
     * @Description: 添加
     * @param record
     * @return
     */
    int insert(MobHomeSinglePage record);

    /**
     * @Title: insertSelective
     * @Description: 添加-字段可选
     * @param record
     * @return
     */
    int insertSelective(MobHomeSinglePage record);

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 修改-字段可选
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobHomeSinglePage record);

    /**
     * @Title: updateByPrimaryKeyWithBLOBs
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(MobHomeSinglePage record);

    /**
     * @Title: updateByPrimaryKey
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobHomeSinglePage record);

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据ID获取模板信息
     * @param homepageId
     * @return
     */
    MobHomeSinglePage selectByPrimaryKey(Long homepageId);

    /**
     * @Title:queryInfoBySinglepageId
     * @Description: 根据移动版单页ID获取移动版模板信息
     * @param singlepageId
     * @return
     */
    MobHomeSinglePage queryInfoBySinglepageId(Long singlepageId);

    /**
     * @Title:queryinitInfoBySinglepageId
     * @Description:查询SinglepageId=-1的移动版模板信息的集合 即查询用于初始化的模板信息
     * @return List<MobHomeSinglePage>
     */
    List<MobHomeSinglePage> queryinitInfoBySinglepageId();

    /**
     * @Title:queryinitInfoCount
     * @Description:查询SinglepageId=-1的移动版模板信息 即查询用于初始化的模板信息的总条数
     * @return
     */
    int queryinitInfoCount();

}
