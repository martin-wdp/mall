/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.GroupType;
import com.ningpai.group.dao.GroupTypeMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 小组分类实现
 * 
 * @version 2014年5月27日 下午3:05:24
 * @author qiyuanyuan
 */

@Repository("GroupTypeMapper")
public class GroupTypeMapperImpl extends BasicSqlSupport implements GroupTypeMapper {

    /**
     * 根据主键删除
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long groupTypeId) {
        return this.delete("com.ningpai.group.mapper.GroupTypeMapper.deleteByPrimaryKey", groupTypeId);
    }

    /**
     * 
     * 插入，空属性也会插入
     * @see com.ningpai.group.dao.GroupTypeMapper#insert(com.ningpai.group.bean.GroupType )
     */
    public int insert(GroupType groupType) {
        return this.insert("com.ningpai.group.mapper.GroupTypeMapper.insert", groupType);
    }

    /**
     * 
     * 根据主键查询
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public int insertSelective(GroupType groupType) {
        return this.insert("com.ningpai.group.mapper.GroupTypeMapper.insertSelective", groupType);
    }

    /**
     * 根据主键查询
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper#selectByPrimaryKey(java.lang.Long)
     */
    public GroupType selectByPrimaryKey(Long groupTypeId) {
        return this.selectOne("com.ningpai.group.mapper.GroupTypeMapper.selectByPrimaryKey", groupTypeId);
    }

    /**
     * 
     * 根据主键修改，空值条件不会修改成null
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public int updateByPrimaryKeySelective(GroupType groupType) {
        return this.update("com.ningpai.group.mapper.GroupTypeMapper.updateByPrimaryKeySelective", groupType);
    }

    /**
     * 
     * 根据主键修改，空值条件会修改成null
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public int updateByPrimaryKey(GroupType groupType) {
        return this.update("com.ningpai.group.mapper.GroupTypeMapper.updateByPrimaryKey", groupType);
    }

    /**
     *  根据小组分类主键删除小组（更新小组分类的delFlag）
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper#deleteByGroupTypeId(java.lang.Long)
     */
    public int deleteByGroupTypeId(Long groupTypeId) {
        return this.delete("com.ningpai.group.mapper.GroupTypeMapper.deleteByGroupTypeId", groupTypeId);
    }

    /**
     * 恢复小组分类
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper#updateDisStatusById(java.lang.Long)
     */
    public int updateDisStatusById(Long groupTypeId) {
        return this.update("com.ningpai.group.mapper.GroupTypeMapper.disableGroupById", groupTypeId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public int updateStatusById(Long groupTypeId) {
        return this.update("com.ningpai.group.mapper.GroupTypeMapper.recoveryGroupById", groupTypeId);
    }

    /**
     * 查询所有的小组分类
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper#selectAll()
     */
    public List<GroupType> selectAll() {
        return this.selectList("com.ningpai.group.mapper.GroupTypeMapper.selectAllType");
    }

    /**
     * 
     * 按条件查询小组分类
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public List<Object> selectGrouptype(GroupType groupType) {
        return this.selectList("com.ningpai.group.mapper.GroupTypeMapper.selectgrouptype", groupType);
    }

    /**
     * 按条件查询会员 返回数量
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public Long selectGroupTypeSize(GroupType groupType) {
        return this.selectOne("com.ningpai.group.mapper.GroupTypeMapper.selectGroupTypeSize", groupType);
    }

    /**
     * 分页查询小组分类
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public List<Object> selectGroupTypeByLimit(Map<String, Integer> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupTypeMapper.selectGroupTypeByLimit", paramMap);
    }

    /**
     * 根据分类名称查询分类小组
     *
     * 
     * @see com.ningpai.group.dao.GroupTypeMapper
     */
    public GroupType queryTypeByTypeName(String typeName) {
        return this.selectOne("com.ningpai.group.mapper.GroupTypeMapper.queryTypeByTypeName", typeName);
    }

}
