package com.ningpai.manager.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.AuthorityPage;

/**
 * 权限mapper
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午2:28:32
 */
public interface AuthorityMapper {
    /**
     * 根据ID删除权限信息
     * 
     * @param id
     *            权限ID
     * @return int 1表示成功 0表示失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入权限信息
     * 
     * @param record
     *            <code>Authority</code>对象
     *            {@link com.ningpai.manager.bean.Authority}
     * @return int 1表示成功 0表示失败
     */
    int insert(Authority record);

    /**
     * 插入权限信息
     * 
     * @param record
     *            <code>Authority</code>对象
     *            {@link com.ningpai.manager.bean.Authority}
     * @return int 1表示成功 0表示失败
     */
    int insertSelective(Authority record);

    /**
     * 根据ID选取权限信息
     * 
     * @param id
     *            权限ID
     * @return <code>Authority</code>对象
     *         {@link com.ningpai.manager.bean.Authority}
     */
    Authority selectByPrimaryKey(Long id);

    /**
     * 修改权限信息
     * 
     * @param record
     *            <code>Authority</code>对象
     *            {@link com.ningpai.manager.bean.Authority}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKeySelective(Authority record);

    /**
     * 修改权限信息
     * 
     * @param record
     *            <code>Authority</code>对象
     *            {@link com.ningpai.manager.bean.Authority}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKey(Authority record);

    /**
     * 查询权限列表
     * 
     * @return List<code>Authority</code>对象
     *         {@link com.ningpai.manager.bean.Authority}
     */
    List<Authority> selectAllAuthority(Long createId);

    /**
     * 查询权限总数
     * 
     * @return
     */
    Long queryAuthoritySize(Authority authority);

    /**
     * 查询权限
     * 
     * @param paramMap
     * @return
     */
    List<Object> selectAuthorityByLimit(Map<String, Object> paramMap);

    /**
     * 按条件查询
     * 
     * @param authority
     * @return
     */
    List<Object> selectAuthorityByAuthority(Authority authority);

    /**
     * 根据编号删除权限
     * 
     * @param parseLong
     * @return 0失败 1成功
     */
    Integer deleteAuthorityById(long parseLong);

    /**
     * 添加权限
     * 
     * @param designation
     *            权限名称
     * @return 0失败 1成功
     */
    int insertByDesignation(Map<String, Object> paramMap);

    /**
     * 获取最后插入的主键
     * 
     * @return
     */
    Long selectLastId();

    /**
     * 根据权限编号查找权限页面关系信息
     * 
     * @param id
     *            权限编号
     * @return 权限页面关系
     */
    List<AuthorityPage> selectAuthorityByAId(Long id);

    /**
     * 查询超级管理员
     * 
     * @return 权限
     */
    Authority querySupperAuthor();

    /**
     * 批量删权限
     * 
     * @param paramMap
     *            权限编号Map
     * @return 0失败 1成功
     */
    int deleteAuthorityByIds(Map<String, Object> paramMap);

    /**
     * 根据权限名称检查权限是否存在
     * 
     * @param authName
     *            权限名称
     * @return Long 0 不存在 1存在
     */
    Long checkAuthExist(String authName);

    /**
     * 根据管理员名称检查管理员是否存在
     *
     * @param username
     *            管理员名称
     * @return Authority
     */
    Authority checkManagerExist(String username);

    /**
     * 查询所属角色
     * 
     * @param id
     * @return Authority
     */
    Authority selectAuthByManagerId(Long id);
}
