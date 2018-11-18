/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.group.bean.GroupPermissions;
import com.ningpai.group.dao.GroupPermissionsMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 小组权限实现
 * 
 * @version 2014年5月27日 下午2:54:08
 * @author qiyuanyuan
 */

@Repository("GroupPermissionsMapper")
public class GroupPermissionsMapperImpl extends BasicSqlSupport implements GroupPermissionsMapper {

    /**
     * 根据主键删除
     *
     * @param:limitId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public int deleteByPrimaryKey(Long limitId) {
        return this.delete("com.ningpai.group.mapper.GroupPermissionsMapper.deleteByPrimaryKey", limitId);
    }

    /**
     * 插入，空属性也会插入
     *
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public int insert(GroupPermissions groupPermissions) {
        return this.insert("com.ningpai.group.mapper.GroupPermissionsMapper.insert", groupPermissions);
    }

    /**
     * 插入，空属性不会插入
     *
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public int insertSelective(GroupPermissions groupPermissions) {
        return this.insert("com.ningpai.group.mapper.GroupPermissionsMapper.insertSelective", groupPermissions);
    }

    /**
     * 根据主键查询
     *
     * @param:limitId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public GroupPermissions selectByPrimaryKey(Long limitId) {
        return this.selectOne("com.ningpai.group.mapper.GroupPermissionsMapper.selectByPrimaryKey", limitId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     *
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public int updateByPrimaryKeySelective(GroupPermissions groupPermissions) {
        return this.update("com.ningpai.group.mapper.GroupPermissionsMapper.updateByPrimaryKeySelective", groupPermissions);
    }

    /**
     * 根据小组ID，空值条件不会修改成null
     *
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public int updateByPrimaryKey(GroupPermissions groupPermissions) {
        return this.update("com.ningpai.group.mapper.GroupPermissionsMapper.updateByPrimaryKey", groupPermissions);
    }

    /**
     * 根据小组ID，空值条件不会修改成null
     *
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    public int updateByGroupId(GroupPermissions groupPermissions) {
        return this.update("com.ningpai.group.mapper.GroupPermissionsMapper.updateByGroupId", groupPermissions);
    }

}
