/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service.impl;

import com.ningpai.group.bean.GroupType;
import com.ningpai.group.dao.GroupTypeMapper;
import com.ningpai.group.service.GroupTypeService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小组分类Service接口实现类
 * 
 * @version 2014年5月30日 上午10:19:07
 * @author qiyuanyuan
 */

@Service("GroupTypeService")
public class GroupTypeServiceImpl implements GroupTypeService {

    /**
     * 小组类型DAO
     */
    private GroupTypeMapper groupTypeMapper;

    /**
     * 添加小组分类
     *
     * @param groupType
     *            小组分类对象 {@link com.ningpai.group.bean.GroupType}
     * @return int
     */
    @Transactional
    public int addGroupTyope(GroupType groupType) {
        groupType.setGroupTypeCreateTime(new Date());
        groupType.setGroupTypeModifyTime(new Date());
        groupType.setGroupTypeDelFlag("0");
        groupType.setGroupTypeStatus("0");
        return this.groupTypeMapper.insertSelective(groupType);
    }

    /**
     * 删除小组分类
     *
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int deleteGroupType(Long groupTypeId) {
        return groupTypeMapper.deleteByGroupTypeId(groupTypeId);
    }

    /**
     * 更新小组分类
     *
     * @param groupType
     *            小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @return int
     */
    @Transactional
    public int updateGroupType(GroupType groupType) {
        groupType.setGroupTypeModifyTime(new Date());
        return this.groupTypeMapper.updateByPrimaryKeySelective(groupType);
    }

    /**
     * 停用小组分类
     *
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int updateDisGroupByid(Long groupTypeId) {
        GroupType groupType = this.groupTypeMapper.selectByPrimaryKey(groupTypeId);
        groupType.setGroupTypeModifyTime(new Date());
        return this.groupTypeMapper.updateDisStatusById(groupTypeId);
    }

    /**
     * 恢复小组分类
     *
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int updateGroupById(Long groupTypeId) {
        GroupType groupType = this.groupTypeMapper.selectByPrimaryKey(groupTypeId);
        groupType.setGroupTypeModifyTime(new Date());
        return this.groupTypeMapper.updateStatusById(groupTypeId);
    }

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
     * 按条件查询小组分类
     *
     * @param groupType
     *            小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @param pageBean
     *            分页
     * @return PageBean
     */
    public PageBean selectGroupTypeList(GroupType groupType, PageBean pageBean) {
        Map<String, Integer> paramMap = null;
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(Integer.parseInt(groupTypeMapper.selectGroupTypeSize(groupType) + ""));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询条件
            if (groupType != null) {
                groupType.setStartRowNum(pageBean.getStartRowNum());
                groupType.setEndRowNum(pageBean.getEndRowNum());
            }
            paramMap = new HashMap<String, Integer>();
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            // 查询会员信息
            pageBean.setList(groupType == null ? groupTypeMapper.selectGroupTypeByLimit(paramMap) : groupTypeMapper.selectGrouptype(groupType));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 根据分类名称查询小组分类
     *
     * @param typeName
     *            分类名称 {@link java.lang.String}
     * @return 对象
     */
    public GroupType queryTypeByTypeName(String typeName) {
        return this.groupTypeMapper.queryTypeByTypeName(typeName);
    }

    /**
     * 查询所有的小组分类
     *
     * @return List
     */
    public List<GroupType> selectAll() {
        return groupTypeMapper.selectAll();
    }

    public GroupTypeMapper getGroupTypeMapper() {
        return groupTypeMapper;
    }

    @Resource(name = "GroupTypeMapper")
    public void setGroupTypeMapper(GroupTypeMapper groupTypeMapper) {
        this.groupTypeMapper = groupTypeMapper;
    }

}
