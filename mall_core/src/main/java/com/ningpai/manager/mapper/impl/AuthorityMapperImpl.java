package com.ningpai.manager.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.mapper.AuthorityMapper;

/**
 * @see com.ningpai.manager.AuthorityMapper
 * @author AthrunNatu
 * @since 2013年11月20日下午3:58:57
 */
@Repository("authorityMapper")
public final class AuthorityMapperImpl extends BasicSqlSupport implements
        AuthorityMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this
                .delete("com.ningpai.manager.mapper.AuthorityMapper.deleteByPrimaryKey",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#insert(com.ningpai.manager
     * .bean.Authority)
     */
    @Override
    public int insert(Authority record) {
        return this.insert("com.ningpai.manager.mapper.AuthorityMapper.insert",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#insertSelective(com.ningpai
     * .manager.bean.Authority)
     */
    @Override
    public int insertSelective(Authority record) {
        return this.insert(
                "com.ningpai.manager.mapper.AuthorityMapper.insertSelective",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public Authority selectByPrimaryKey(Long id) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.AuthorityMapper.selectByPrimaryKey",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#updateByPrimaryKeySelective
     * (com.ningpai.manager.bean.Authority)
     */
    @Override
    public int updateByPrimaryKeySelective(Authority record) {
        return this
                .update("com.ningpai.manager.mapper.AuthorityMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#updateByPrimaryKey(com.ningpai
     * .manager.bean.Authority)
     */
    @Override
    public int updateByPrimaryKey(Authority record) {
        return this
                .update("com.ningpai.manager.mapper.AuthorityMapper.updateByPrimaryKey",
                        record);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.AuthorityMapper#selectAllAuthority()
     */
    @Override
    public List<Authority> selectAllAuthority(Long createId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("createId", createId);

        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityMapper.selectAllAuthority",
                        paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.AuthorityMapper#queryAuthoritySize()
     */
    @Override
    public Long queryAuthoritySize(Authority authority) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.AuthorityMapper.queryAuthoritySize",
                        authority);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#selectAuthorityByLimit(java
     * .util.Map)
     */
    @Override
    public List<Object> selectAuthorityByLimit(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityMapper.selectAuthorityByLimit",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#selectAuthorityByAuthority
     * (com.ningpai.manager.bean.Authority)
     */
    @Override
    public List<Object> selectAuthorityByAuthority(Authority authority) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityMapper.selectAuthorityByAuthority",
                        authority);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.AuthorityMapper#deleteAuthorityById(long)
     */
    @Override
    public Integer deleteAuthorityById(long parseLong) {
        return this
                .delete("com.ningpai.manager.mapper.AuthorityMapper.deleteAuthorityById",
                        parseLong);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#insertByDesignation(java.lang
     * .String)
     */
    @Override
    public int insertByDesignation(Map<String, Object> paramMap) {
        return this
                .insert("com.ningpai.manager.mapper.AuthorityMapper.insertByDesignation",
                        paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.AuthorityMapper#selectLastId()
     */
    @Override
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.manager.mapper.AuthorityMapper.selectLastId");
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#selectAuthorityByAId(java.
     * lang.Long)
     */
    @Override
    public List<AuthorityPage> selectAuthorityByAId(Long id) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.AuthorityPageMapper.selectAuthorityByAId",
                        id);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.AuthorityMapper#querySupperAuthor()
     */
    @Override
    public Authority querySupperAuthor() {
        return this
                .selectOne("com.ningpai.manager.mapper.AuthorityMapper.querySupperAuthor");
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#deleteAuthorityByIds(java.
     * util.Map)
     */
    @Override
    public int deleteAuthorityByIds(Map<String, Object> paramMap) {
        return this
                .delete("com.ningpai.manager.mapper.AuthorityMapper.deleteAuthorityByIds",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#checkAuthExist(java.lang.String
     * )
     */
    @Override
    public Long checkAuthExist(String authName) {
        return this.selectOne(
                "com.ningpai.manager.mapper.AuthorityMapper.checkAuthExist",
                authName);
    }

    @Override
    public Authority checkManagerExist(String username) {
        return this.selectOne(
                "com.ningpai.manager.mapper.AuthorityMapper.checkManagerExist",
                username);
    }

    /*
     * 
     * @see
     * com.ningpai.manager.mapper.AuthorityMapper#selectAuthByManagerId(java
     * .lang.Long)
     */
    @Override
    public Authority selectAuthByManagerId(Long id) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.AuthorityMapper.selectAuthByManagerId",
                        id);
    }

}
