/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service.impl;

import com.ningpai.group.bean.GroupType;
import com.ningpai.group.dao.GroupTypeMapper;
import com.ningpai.group.service.GroupTypeSiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台小组分类Service接口实现类
 * 
 * @version 2014年5月30日 上午10:19:07
 * @author qiyuanyuan
 */

@Service("GroupTypeSiteService")
public class GroupTypeSiteServiceImpl implements GroupTypeSiteService {

    /**
     * 小组类型DAO
     */
    private GroupTypeMapper groupTypeMapper;

    /**
     * 根据分类ID查询小组分类
     *
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return 对象
     */
    public GroupType selectByGroupTypeId(Long groupTypeId) {
        return this.groupTypeMapper.selectByPrimaryKey(groupTypeId);
    }

    /**
     * 查询所有的小组分类
     *
     * @return List
     */
    public List<GroupType> selectAll() {
        return this.groupTypeMapper.selectAll();
    }

    public GroupTypeMapper getGroupTypeMapper() {
        return groupTypeMapper;
    }

    @Resource(name = "GroupTypeMapper")
    public void setGroupTypeMapper(GroupTypeMapper groupTypeMapper) {
        this.groupTypeMapper = groupTypeMapper;
    }

}
