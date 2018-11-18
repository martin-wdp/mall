/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service;

import java.util.List;

import com.ningpai.group.bean.GroupType;

/**
 * 前台小组分类Service 接口
 * 
 * @version 2014年5月30日 上午10:18:47
 * @author qiyuanyuan
 */

public interface GroupTypeSiteService {

    /**
     * 根据分类ID查询小组分类
     * 
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return 对象
     */
    GroupType selectByGroupTypeId(Long groupTypeId);

    /**
     * 查询所有的小组分类
     * 
     * @return List
     */
    List<GroupType> selectAll();
}
