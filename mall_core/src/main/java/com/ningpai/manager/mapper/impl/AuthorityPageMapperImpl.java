package com.ningpai.manager.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.AuthorityPageMapper;

/**
 * @see com.ningpai.manager.mapper.AuthorityPageMapper
 * @author AthrunNatu
 * @since 2013年11月20日下午4:11:58
 */
@Repository("authorityPageMapper")
public final class AuthorityPageMapperImpl extends BasicSqlSupport implements
        AuthorityPageMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#deleteByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long id) {

        return this
                .delete("com.ningpai.manager.mapper.AuthorityPageMapper.deleteByPrimaryKey",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#insert(com.ningpai.manager
     * .bean.AuthorityPage)
     */
    @Override
    public int insert(AuthorityPage record) {

        return this
                .insert("com.ningpai.manager.mapper.AuthorityPageMapper.insert",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#insertSelective(com.ningpai
     * .manager.bean.AuthorityPage)
     */
    @Override
    public int insertSelective(AuthorityPage record) {

        return this
                .insert("com.ningpai.manager.mapper.AuthorityPageMapper.insertSelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#selectByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public AuthorityPage selectByPrimaryKey(Long id) {

        return this
                .selectOne(
                        "com.ningapi.manager.mapper.AuthorityPageMapper.selectByPrimaryKey",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#updateByPrimaryKeySelective
     * (com.ningpai.manager.bean.AuthorityPage)
     */
    @Override
    public int updateByPrimaryKeySelective(AuthorityPage record) {

        return this
                .update("com.ningpai.manager.mapper.AuthorityPageMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#updateByPrimaryKey(com
     * .ningpai.manager.bean.AuthorityPage)
     */
    @Override
    public int updateByPrimaryKey(AuthorityPage record) {

        return this
                .update("com.ningpai.manager.mapper.AuthorityPageMapper.updateByPrimaryKey",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#selectByAuthorityID(java
     * .lang.Long)
     */
    @Override
    public List<MenuVo> selectByAuthorityID(Long authorityId) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityPageMapper.selectByAuthorityID",
                        authorityId);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.AuthorityPageMapper#selectAllMenuVos(Long
     * authorityId)
     */
    @Override
    public List<MenuVo> selectAllMenuVos(Long authorityId) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityPageMapper.selectAllMenuVos",
                        authorityId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#selectAllMenuVo(java.lang
     * .Long)
     */
    @Override
    public List<MenuVo> selectAllMenuVo(Long authorityId) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityPageMapper.selectAllMenuVo",
                        authorityId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#insertAAndPage(java.util
     * .Map)
     */
    @Override
    public int insertAAndPage(Map<String, Object> map) {
        return this
                .insert("com.ningpai.manager.mapper.AuthorityPageMapper.insertAAndPage",
                        map);
    }

    /*
     *
     *
     * @see
     * com.ningpai.manager.mapper.AuthorityPageMapper#deleteAuthorityPageById
     * (long)
     */
    @Override
    public Integer deleteAuthorityPageById(long parseLong) {
        return this
                .insert("com.ningpai.manager.mapper.AuthorityPageMapper.deleteAuthorityPageById",
                        parseLong);
    }

    /**
     * 批量赋予某个角色权限
     *
     * @param paramMap
     *            参数包括 ： authorityId 角色id menuVos 菜单列表
     */
    @Override
    public void addAuthorityPageBatch(Map<String, Object> paramMap) {
        this.insert(
                "com.ningpai.manager.mapper.AuthorityPageMapper.addAuthorityPageBatch",
                paramMap);
    }

    /**
     * 删除模块中所有的权限
     *
     * @param paramMap 参数包括 ：
     *                 startMenuId 开始菜单id
     *                 endMenuId 结束菜单id
     */
    @Override
    public void deleteAuthByParam(Map<String, Object> paramMap) {
        this.delete("com.ningpai.manager.mapper.AuthorityPageMapper.deleteAuthByParam", paramMap);
    }

    /**
     * 添加页面
     *
     * @param list
     * @return
     */
    @Override
    public int insertAuthorities(ArrayList<Map<String, Object>> list) {
        return this
                .insert("com.ningpai.manager.mapper.AuthorityPageMapper.insertAuthorities",
                        list);
    }

}
