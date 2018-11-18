/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.Group;
import com.ningpai.group.bean.GroupLabel;
import com.ningpai.group.dao.GroupMapper;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 小组接口的实现类
 * 
 * @version 2014年5月26日 下午2:09:52
 * @author qiyuanyuan
 */

@Repository("GroupMapper")
public class GroupMapperImpl extends BasicSqlSupport implements GroupMapper {

    /**
     * 根据主键删除
     * 
     */
    public int deleteByGroupId(Long groupId) {
        return this.delete("com.ningpai.group.mapper.GroupMapper.deleteGroupId", groupId);
    }

    /**
     * 插入小组
     * 
     */
    public int insert(Group group) {
        return this.insert("com.ningpai.group.mapper.GroupMapper.insert", group);
    }

    /**
     * 插入小组
     * 
     */
    public int insertSelective(Group group) {
        return this.insert("com.ningpai.group.mapper.GroupMapper.insertSelective", group);
    }

    /**
     * 查询小组详细根据ID
     * 
     */
    public Group selectByPrimaryKey(Long groupId) {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.selectByPrimaryKey", groupId);
    }

    /**
     * 修改小组信息
     * 
     */
    public int updateByPrimaryKeySelective(Group group) {
        return this.update("com.ningpai.group.mapper.GroupMapper.updateByPrimaryKeySelective", group);
    }

    /**
     * 修改小组信息
     * 
     */
    public int updateByPrimaryKey(Group group) {
        return this.update("com.ningpai.group.mapper.GroupMapper.updateByPrimaryKey", group);
    }

    /**
     * 查询小组列表信息
     * 
     */
    public List<GroupVo> findGroupList(GroupVo groupVo) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.findGroupList", groupVo);
    }

    /**
     * 查询所有小组记录
     * 
     */
    public int queryTotalCount() {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.queryTotalCount");
    }

    /**
     * 根据分页参数查询分页列表
     * 
     */
    public List<Object> queryByPageBean(Map<String, Integer> map) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.queryByPageBean", map);
    }

    /**
     * 根据分页参数查询分页列表
     * 
     */
    public List<Object> selectGroupByLimit(Map<String, Integer> map) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.selectGroupByLimit", map);
    }

    /**
     * 根据分页参数查询分页列表
     * 
     */
    public List<Object> selectGroup(Map<String, Object> map) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.selectGroup", map);
    }

    /**
     * 根据分页参数查询数量
     * 
     */
    public int selectGroupSize(Map<String, Object> map) {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.selectGroupSize", map);
    }

    /**
     * 解散小组
     * 
     */
    public int dissolveGroupById(Long groupId) {
        return this.update("com.ningpai.group.mapper.GroupMapper.dissolveGroupById", groupId);
    }

    /**
     * 通过小组审核
     * 
     */
    public int passGroupById(Long groupId) {
        return this.update("com.ningpai.group.mapper.GroupMapper.passGroupById", groupId);
    }

    /**
     * 拒绝小组 
     * 
     */
    public int refuseGroupById(Group group) {
        return this.update("com.ningpai.group.mapper.GroupMapper.refuseGroupById", group);
    }

    /**
     * 查询小组详细
     * 
     */
    public GroupVo selectGroupVoByGroupId(Long groupId) {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.selectGroupVoByGroupId", groupId);
    }

    /**
     * 设置活跃小组
     * 
     */
    public int activeGroupById(Long groupId) {
        return this.update("com.ningpai.group.mapper.GroupMapper.activeGroupById", groupId);
    }

    /**
     * 后台设置活跃小组为一般小组
     * 
     */
    public int commonGroupId(Long groupId) {
        return this.update("com.ningpai.group.mapper.GroupMapper.commonGroupById", groupId);
    }

    /**
     * 统计小组成员数量
     * 
     */
    public List<GroupVo> groupMemberById() {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.groupMemberById");
    }

    /**
     * 小组数量
     * 
     */
    public long groupCount() {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.groupCount");
    }

    /**
     * 小组成员总数
     * 
     */
    public long groupMember() {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.groupMember");
    }

    /**
     * 去除小组背景
     * 
     */
    public int editGroupbg(GroupVo gorup) {
        return this.update("com.ningpai.group.mapper.GroupMapper.editGroupbg", gorup);
    }

    /**
     * 我管理的小组
     * 
     */
    public List<Object> myManagerGroupList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.myManagerGroupListByPage", paramMap);
    }

    /**
     *  我管理的小组的数量
     * 
     */
    public int myManagerGroupCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.myManagerGroupCount", paramMap);
    }

    /**
     * 我加入的小组
     * 
     */
    public List<Object> myJoinedGroupList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.myJoinedGroupList", paramMap);
    }

    /**
     * 我加入的小组的数量
     * 
     */
    public int myJoinedGroupCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.myJoinedGroupCount", paramMap);
    }

    /**
     * 我管理的小组
     * 
     */
    public List<GroupVo> myManagerGroupList(GroupVo groupVo) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.myManagerGroupList", groupVo);
    }

    /**
     * 根据分类ID查询小组标签
     * 
     */
    public List<GroupLabel> groupLabelList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.groupLabelByTypeId", paramMap);
    }

    /**
     * 根据用户Id查询他加入的小组
     * 
     */
    public List<Group> joinedGroup(Long customerId) {
        return this.selectList("com.ningpai.group.mapper.GroupMapper.joinedGroup", customerId);
    }

    /**
     * 后台设置热门小组
     * 
     */
    public int hotGroupById(Long groupId) {
        return this.update("com.ningpai.group.mapper.GroupMapper.hotGroupById", groupId);
    }

    /**
     *  后台设置热门小组为一般小组
     * 
     */
    public int cancelHotGroupId(Long groupId) {
        //
        return this.update("com.ningpai.group.mapper.GroupMapper.cancelhotGroupById", groupId);
    }

    /**
     * 后台设置特别推荐小组
     * 
     */
    public int recommendGroupById(Long groupId) {
        //
        return this.update("com.ningpai.group.mapper.GroupMapper.recommendGroupById", groupId);
    }

    /**
     * 后台取特别推荐小组
     * 
     */
    public int cancelRecommendGroupId(Long groupId) {
        //
        return this.update("com.ningpai.group.mapper.GroupMapper.cancelrecommendGroupById", groupId);
    }

    /**
     * 根据小组标签和小组类型查询小组
     * 
     */
    public List<Object> labelGroup(Map<String, Object> paramMap) {
        //
        return this.selectList("com.ningpai.group.mapper.GroupMapper.labelGroup", paramMap);
    }

    /**
     * 根据小组标签和小组类型查询小组数目
     * 
     */
    public int labelGroupCount(Map<String, Object> paramMap) {
        //
        return this.selectOne("com.ningpai.group.mapper.GroupMapper.labelGroupSize", paramMap);
    }

}
