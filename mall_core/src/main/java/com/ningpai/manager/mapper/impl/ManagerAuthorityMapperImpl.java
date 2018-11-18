package com.ningpai.manager.mapper.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;

/**
 * @see com.ningpai.manager.mapper.ManagerAuthorityMapper
 * @author AthrunNatu
 * @since 2013年11月20日下午4:23:29
 */
@Repository("managerAuthorityMapper")
public final class ManagerAuthorityMapperImpl extends BasicSqlSupport implements
        ManagerAuthorityMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#deleteByPrimaryKey(
     * java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long id) {

        return this
                .delete("com.ningpai.manager.mapper.ManagerAuthorityMapper.deleteByPrimaryKey",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#insert(com.ningpai.
     * manager.bean.ManagerAuthority)
     */
    @Override
    public int insert(ManagerAuthority record) {

        return this.insert(
                "com.ningpai.manager.mapper.ManagerAuthorityMapper.insert",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#insertSelective(com
     * .ningpai.manager.bean.ManagerAuthority)
     */
    @Override
    public int insertSelective(ManagerAuthority record) {
        return this
                .insert("com.ningpai.manager.mapper.ManagerAuthorityMapper.insertSelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#selectByPrimaryKey(
     * java.lang.Long)
     */
    @Override
    public ManagerAuthority selectByPrimaryKey(Long id) {

        return this
                .selectOne(
                        "com.ningpai.manager.mapper.ManagerAuthorityMapper.selectByPrimaryKey",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#updateByPrimaryKeySelective
     * (com.ningpai.manager.bean.ManagerAuthority)
     */
    @Override
    public int updateByPrimaryKeySelective(ManagerAuthority record) {
        return this
                .update("com.ningpai.manager.mapper.ManagerAuthorityMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#updateByPrimaryKey(
     * com.ningpai.manager.bean.ManagerAuthority)
     */
    @Override
    public int updateByPrimaryKey(ManagerAuthority record) {
        return this
                .update("com.ningpai.manager.mapper.ManagerAuthorityMapper.updateByPrimaryKey",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#selectByManagerId(java
     * .lang.Long)
     */
    @Override
    public ManagerAuthority selectByManagerId(Long id) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.ManagerAuthorityMapper.selectByManagerId",
                        id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerAuthorityMapper#deleteByManagerIds(
     * java.util.Map)
     */
    @Override
    public int deleteByManagerIds(Map<String, Object> paramMap) {
        return this
                .delete("com.ningpai.manager.mapper.ManagerAuthorityMapper.deleteByManagerIds",
                        paramMap);
    }

    @Override
    public int enabledByManagerIds(Map<String, Object> paramMap) {
        return this
                .delete("com.ningpai.manager.mapper.ManagerAuthorityMapper.enabledByManagerIds",
                        paramMap);
    }
}
