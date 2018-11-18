/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.CustomerPunish;
import com.ningpai.customer.dao.CustomerPunishMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 会员处罚实现类
 *
 * */
@Repository("CustomerPunishMapper")
public class CustomerPunishMapperImpl extends BasicSqlSupport implements
        CustomerPunishMapper {
    /**
     * 根据主键进行删除
     *
     * @param id
     * */
    @Override
    public int deleteByPrimaryKey(Long id) {

        return 0;
    }
    /**
     * 插入一条记录
     *
     * @param record
     * */
    @Override
    public int insert(CustomerPunish record) {

        return 0;
    }
    /**
     * 添加
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(CustomerPunish record) {

        return this
                .insert("com.ningpai.customer.dao.CustomerPunishMapper.insertSelective",
                        record);
    }
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public CustomerPunish selectByPrimaryKey(Long id) {

        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerPunishMapper.selectByPrimaryKey",
                        id);
    }
    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerPunish record) {

        return this
                .update("com.ningpai.customer.dao.CustomerPunishMapper.updateByPrimaryKeySelective",
                        record);
    }
    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    @Override
    public int updateByPrimaryKey(CustomerPunish record) {

        return 0;
    }
    /**
     * 分页查询
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Object> selectPunishInfoByPage(Map<String, Object> paramMap) {

        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerPunishMapper.selectPunishInfoByPage",
                        paramMap);
    }
    /**
     * 查询总条数
     *
     * @return
     */
    @Override
    public int selectPunishInfoCount() {

        return this
                .selectOne("com.ningpai.customer.dao.CustomerPunishMapper.selectPunishInfoCount");
    }
    /**
     * 删除(逻辑删除)
     *
     * @param id
     * @return
     */
    @Override
    public int updateDelflag(Long id) {

        return this.update(
                "com.ningpai.customer.dao.CustomerPunishMapper.updateDelflag",
                id);
    }
    /**
     * 查询所有
     * */
    @Override
    public List<CustomerPunish> queryAllRules() {

        return this
                .selectList("com.ningpai.customer.dao.CustomerPunishMapper.queryAllRules");
    }
    /**
     *根据条件查询
     * */
    @Override
    public CustomerPunish queryIdByRule(CustomerPunish customerPunish) {

        return this.selectOne(
                "com.ningpai.customer.dao.CustomerPunishMapper.queryIdByRule",
                customerPunish);
    }

}
