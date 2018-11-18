/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.service;

import java.util.List;

import com.ningpai.mobile.bean.MobHomeSinglePage;

/**
 * 移动版单页可视化配置 service层接口
 * 
 * @author zhangsl
 * @since 2014年12月5日 下午2:30:55
 * @version
 */
public interface MobHomeSinglePageService {
    /**
     * 添加移动版可视化配置首页
     * 
     * @Title: createHomePage
     * @Description: 添加移动版可视化配置首页
     * @param homePage
     */
    void createHomePage(MobHomeSinglePage homePage);

    /**
     * 修改移动版可视化配置首页
     * 
     * @Title: updateHomePage
     * @Description: 修改移动版可视化配置首页
     * @param homePage
     */
    void updateHomePage(MobHomeSinglePage homePage);

    /**
     * 初始化商家的HomePage
     * 
     * @Title: initHomePage
     * @Description: 初始化商家的HomePage
     */
    MobHomeSinglePage initHomePage();

    /**
     * 生成HTML
     * 
     * @Title: makeHtml
     * @Description: 生成HTML
     */
    void makeHtml();

    /**
     * 根据ID获取模板信息
     * 
     * @Title: getHomePageById
     * @Description: 根据ID获取模板信息
     * @param homepageId
     * @return
     */
    MobHomeSinglePage getHomePageById(Long homepageId);

    /**
     * 根据ID删除模板信息
     * 
     * @Title: deleteHomePageById
     * @Description: 根据ID删除模板信息
     * @param homepageId
     */
    void deleteHomePageById(Long homepageId);

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
