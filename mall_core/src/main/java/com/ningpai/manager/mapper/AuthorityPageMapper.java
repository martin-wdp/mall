package com.ningpai.manager.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.bean.valuebean.MenuVo;

/**
 * 权限页面关系网页关系mapper
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午2:38:28
 */
public interface AuthorityPageMapper {
    /**
     * 根据ID删除权限页面关系信息
     * 
     * @param id
     *            权限页面关系ID
     * @return int 1表示成功 0表示失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入权限页面关系信息
     * 
     * @param record
     *            <code>AuthorityPage</code>对象
     *            {@link com.ningpai.manager.bean.AuthorityPage}
     * @return int 1表示成功 0表示失败
     */
    int insert(AuthorityPage record);

    /**
     * 插入权限页面关系信息
     * 
     * @param record
     *            <code>AuthorityPage</code>对象
     *            {@link com.ningpai.manager.bean.AuthorityPage}
     * @return int 1表示成功 0表示失败
     */
    int insertSelective(AuthorityPage record);

    /**
     * 根据ID选取权限页面关系信息
     * 
     * @param id
     *            权限页面关系ID
     * @return <code>AuthorityPage</code>对象
     *         {@link com.ningpai.manager.bean.AuthorityPage}
     */
    AuthorityPage selectByPrimaryKey(Long id);

    /**
     * 修改权限页面关系信息
     * 
     * @param record
     *            <code>AuthorityPage</code>对象
     *            {@link com.ningpai.manager.bean.AuthorityPage}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKeySelective(AuthorityPage record);

    /**
     * 修改权限页面关系信息
     * 
     * @param record
     *            <code>AuthorityPage</code>对象
     *            {@link com.ningpai.manager.bean.AuthorityPage}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKey(AuthorityPage record);

    /**
     * 根据权限ID选取权限页面关系信息
     * 
     * @param authorityId
     *            权限ID
     * @return java.util.List <code>MenuVo</code>对象
     *         {@link com.ningpai.manager.bean.valuebean.MenuVo}
     */
    List<MenuVo> selectByAuthorityID(Long authorityId);

    /**
     * 查询所有页面
     * 
     * @return java.util.List <code>MenuVo</code>对象
     *         {@link com.ningpai.manager.bean.valuebean.MenuVo}
     */
    List<MenuVo> selectAllMenuVos(Long authorityId);

    /**
     * 查询所有页面--用于URL拦截权限
     * 
     * @return java.util.List <code>MenuVo</code>对象
     *         {@link com.ningpai.manager.bean.valuebean.MenuVo}
     */
    List<MenuVo> selectAllMenuVo(Long authorityId);

    /**
     * 添加页面
     * 
     * @param map
     * @return
     */
    int insertAAndPage(Map<String, Object> map);

    /**
     * 删除权限页面
     * 
     * @param parseLong
     *            编号
     * @return 0 失败 1 成功
     */
    Integer deleteAuthorityPageById(long parseLong);

    /**
     * 批量赋予某个角色权限
     *
     * @param paramMap
     *            参数包括 ： authorityId 角色id menuVos 菜单列表
     */
    void addAuthorityPageBatch(Map<String, Object> paramMap);

    /**
     * 删除模块中所有的权限
     *
     * @param paramMap 参数包括 ：
     *                 startMenuId 开始菜单id
     *                 endMenuId 结束菜单id
     */
    void deleteAuthByParam(Map<String, Object> paramMap);

    /**
     * 添加页面
     *
     * @param list
     * @return
     */
    int insertAuthorities(ArrayList<Map<String, Object>> list);
}
