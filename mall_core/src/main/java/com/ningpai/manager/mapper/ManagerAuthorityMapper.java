package com.ningpai.manager.mapper;

import java.util.Map;

import com.ningpai.manager.bean.ManagerAuthority;

/**
 * 管理员管理员权限关系关系表
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午2:42:20
 */
public interface ManagerAuthorityMapper {
    /**
     * 根据ID删除管理员权限关系信息
     * 
     * @param id
     *            管理员权限关系ID
     * @return int 1表示成功 0表示失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入管理员权限关系信息
     * 
     * @param record
     *            <code>ManagerAuthority</code>对象
     *            {@link com.ningpai.manager.bean.ManagerAuthority}
     * @return int 1表示成功 0表示失败
     */
    int insert(ManagerAuthority record);

    /**
     * 插入管理员权限关系信息
     * 
     * @param record
     *            <code>ManagerAuthority</code>对象
     *            {@link com.ningpai.manager.bean.ManagerAuthority}
     * @return int 1表示成功 0表示失败
     */
    int insertSelective(ManagerAuthority record);

    /**
     * 根据ID选取管理员权限关系信息
     * 
     * @param id
     *            管理员权限关系ID
     * @return <code>ManagerAuthority</code>对象
     *         {@link com.ningpai.manager.bean.ManagerAuthority}
     */
    ManagerAuthority selectByPrimaryKey(Long id);

    /**
     * 修改管理员权限关系信息
     * 
     * @param record
     *            <code>ManagerAuthority</code>对象
     *            {@link com.ningpai.manager.bean.ManagerAuthority}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKeySelective(ManagerAuthority record);

    /**
     * 修改管理员权限关系信息
     * 
     * @param record
     *            <code>ManagerAuthority</code>对象
     *            {@link com.ningpai.manager.bean.ManagerAuthority}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKey(ManagerAuthority record);

    /**
     * 根据管理员ID选取管理员权限关系信息
     * 
     * @param id
     *            管理员ID
     * @return <code>ManagerAuthority</code>对象
     *         {@link com.ningpai.manager.bean.ManagerAuthority}
     */
    ManagerAuthority selectByManagerId(Long id);

    /**
     * 删除权限管理员关系
     * 
     * @param paramMap
     *            管理员编号Map
     * @return 0失败 1成功
     */
    int deleteByManagerIds(Map<String, Object> paramMap);

    /**
     * 启用权限管理员关系
     *
     * @param paramMap
     *            管理员编号Map
     * @return 0失败 1成功
     */
    int enabledByManagerIds(Map<String, Object> paramMap);

}
