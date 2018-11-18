/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service;

import java.util.List;

import com.ningpai.group.bean.GroupType;
import com.ningpai.util.PageBean;

/**
 * 小组分类Service 接口
 * 
 * @version 2014年5月30日 上午10:18:47
 * @author qiyuanyuan
 */

public interface GroupTypeService {

    /**
     * 添加小组分类
     * 
     * @param groupType
     *            小组分类对象 {@link com.ningpai.group.bean.GroupType}
     * @return int
     */
    int addGroupTyope(GroupType groupType);

    /**
     * 删除小组分类
     * 
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return int
     */
    int deleteGroupType(Long groupTypeId);

    /**
     * 更新小组分类
     * 
     * @param groupType
     *            小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @return int
     */
    int updateGroupType(GroupType groupType);

    /**
     * 停用小组分类
     * 
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return int
     */
    int updateDisGroupByid(Long groupTypeId);

    /**
     * 恢复小组分类
     * 
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return int
     */
    int updateGroupById(Long groupTypeId);

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

    /**
     * 按条件查询小组分类
     * 
     * @param groupType
     *            小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @param pageBean
     *            分页
     * @return PageBean
     */
    PageBean selectGroupTypeList(GroupType groupType, PageBean pageBean);

    /**
     * 根据分类名称查询小组分类
     * 
     * @param typeName
     *            分类名称 {@link java.lang.String}
     * @return 对象
     */
    GroupType queryTypeByTypeName(String typeName);
}
