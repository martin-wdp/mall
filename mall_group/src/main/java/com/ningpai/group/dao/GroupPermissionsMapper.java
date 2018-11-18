package com.ningpai.group.dao;

import com.ningpai.group.bean.GroupPermissions;

/**
 * 小组权限DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupPermissionsMapper {
    /**
     * 根据主键删除
     * 
     * @param:limitId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    int deleteByPrimaryKey(Long limitId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    int insert(GroupPermissions groupPermissions);

    /**
     * 插入，空属性不会插入
     * 
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    int insertSelective(GroupPermissions groupPermissions);

    /**
     * 根据主键查询
     * 
     * @param:limitId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    GroupPermissions selectByPrimaryKey(Long limitId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    int updateByPrimaryKeySelective(GroupPermissions groupPermissions);

    /**
     * 根据小组ID，空值条件不会修改成null
     * 
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    int updateByGroupId(GroupPermissions groupPermissions);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:groupPermissions 对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:35
     */
    int updateByPrimaryKey(GroupPermissions groupPermissions);
}
