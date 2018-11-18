package com.ningpai.manager.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.mapper.ManagerMapper;

/**
 * @see com.ningpai.manager.mapper.ManagerMapper
 * @author AthrunNatu
 * @since 2013年11月20日下午4:34:10
 */
@Repository("managerMapper")
public final class ManagerMapperImpl extends BasicSqlSupport implements
        ManagerMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {

        return this.delete(
                "com.ningpai.manager.mapper.ManagerMapper.deleteByPrimaryKey",
                id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#insert(com.ningpai.manager.bean
     * .Manager)
     */
    @Override
    @Transactional
    public int insert(Manager record) {

        return this.insert("com.ningpai.manager.mapper.ManagerMapper.insert",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#insertSelective(com.ningpai.
     * manager.bean.Manager)
     */
    @Override
    @Transactional
    public int insertSelective(Manager record) {

        return this.insert(
                "com.ningpai.manager.mapper.ManagerMapper.insertSelective",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public Manager selectByPrimaryKey(Long id) {

        return this.selectOne(
                "com.ningpai.manager.mapper.ManagerMapper.selectByPrimaryKey",
                id);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#updateByPrimaryKeySelective(
     * com.ningpai.manager.bean.Manager)
     */
    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Manager record) {
        return this
                .update("com.ningpai.manager.mapper.ManagerMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#updateByPrimaryKey(com.ningpai
     * .manager.bean.Manager)
     */
    @Override
    @Transactional
    public int updateByPrimaryKey(Manager record) {
        return this.update(
                "com.ningpai.manager.mapper.ManagerMapper.updateByPrimaryKey",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#selectByName(java.lang.String)
     */
    @Override
    public Manager selectByName(String name) {
        return this.selectOne(
                "com.ningpai.manager.mapper.ManagerMapper.selectByName", name);
    }

    @Override
    public Manager selectDelManagerByName(String name) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.ManagerMapper.selectDelManagerByName",
                        name);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.ManagerMapper#queryManagerList()
     */
    @Override
    public List<Manager> queryManagerList(Manager manager) {
        return this.selectList(
                "com.ningpai.manager.mapper.ManagerMapper.queryManagerList",
                manager);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#selectManagerByLimit(java.util
     * .Map)
     */
    @Override
    public List<Object> selectManagerByLimit(Map<String, Integer> paramMap) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.ManagerMapper.selectManagerByLimit",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#selectManagerByManager(com.ningpai
     * .manager.bean.Manager)
     */
    @Override
    public List<Object> selectManagerByManager(Manager manager) {
        return this
                .selectList(
                        "com.ningpai.manager.mapper.ManagerMapper.selectManagerByManager",
                        manager);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#addManager(com.ningpai.manager
     * .bean.Manager, java.lang.String[])
     */
    @Override
    @Transactional
    public int addManager(Manager manager, String authorityId) {
        int i = this.insert(
                "com.ningpai.manager.mapper.ManagerMapper.insertSelective",
                manager);
        if (i == 1) {
            Long managerId = this
                    .selectOne("com.ningpai.manager.mapper.ManagerMapper.selectLastId");
            manager.setId(managerId);
            Map<String, Long> paramMap = new HashMap<String, Long>();
            try {
                paramMap.put("managerId", managerId);
                paramMap.put("authorityId", Long.parseLong(authorityId));
                return this
                        .insert("com.ningpai.manager.mapper.ManagerAuthorityMapper.insertSelective",
                                paramMap);
            } finally {
                paramMap = null;
            }
        } else {
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#delectMangerById(java.lang.Long
     * [])
     */
    @Override
    public int delectMangerById(Long parameterIds) {
        return this.delete(
                "com.ningpai.manager.mapper.ManagerMapper.delectMangerById",
                parameterIds);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#delectMangerByIds(java.util.Map)
     */
    @Override
    public int delectMangerByIds(Map<String, Object> paramMap) {
        return this.delete(
                "com.ningpai.manager.mapper.ManagerMapper.delectMangerByIds",
                paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#delectMangerByIds(java.util.Map)
     */
    @Override
    public int enabledMangerByIds(Map<String, Object> paramMap) {
        return this.delete(
                "com.ningpai.manager.mapper.ManagerMapper.enabledMangerByIds",
                paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#queryManagerCount(com.ningpai
     * .manager.bean.Manager)
     */
    @Override
    public int queryManagerCount(Manager manager) {
        return this.selectOne(
                "com.ningpai.manager.mapper.ManagerMapper.queryManagerCount",
                manager);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#selectCaptcha(java.lang.Long)
     */
    @Override
    public Manager selectCaptcha(Long managerId) {
        return this.selectOne(
                "com.ningpai.manager.mapper.ManagerMapper.selectCaptcha",
                managerId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#updateSmsCaptcha(com.ningpai
     * .manager.bean.Manager)
     */
    @Override
    public int updateSmsCaptcha(Manager manager) {
        return this.update(
                "com.ningpai.manager.mapper.ManagerMapper.updateSmsCaptcha",
                manager);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#checkexistsByCustName(java.lang
     * .String)
     */
    @Override
    public Long checkexistsByCustName(String name) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.ManagerMapper.checkexistsByCustName",
                        name);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.mapper.ManagerMapper#selectCustomerByNamePwd(java
     * .util.Map)
     */
    @Override
    public Manager selectCustomerByNamePwd(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.manager.mapper.ManagerMapper.selectCustomerByNamePwd",
                        paramMap);
    }

    /*
     * 
     * @see com.ningpai.manager.mapper.ManagerMapper#selectById(java.lang.Long)
     */
    @Override
    public Manager selectById(Long id) {
        return this.selectOne(
                "com.ningpai.manager.mapper.ManagerMapper.selectById", id);
    }

    @Override
    public List<Manager> queryCloudManagerList() {
        return this
                .selectList("com.ningpai.manager.mapper.ManagerMapper.queryCloudManagerList");
    }

}
