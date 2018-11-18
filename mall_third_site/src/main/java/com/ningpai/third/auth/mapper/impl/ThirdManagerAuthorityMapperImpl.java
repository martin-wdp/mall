/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.mapper.impl;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.customer.bean.Customer;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.auth.bean.ThirdManagerAuthority;
import com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper;

/**
 * @see com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月7日 下午4:20:58
 * @version 0.0.1
 */
@Repository("thirdManagerAuthorityMapper")
public class ThirdManagerAuthorityMapperImpl extends BasicSqlSupport implements ThirdManagerAuthorityMapper {

    /**
     * 根据主键查询权限关系
     * @param manageId
     * @return
     */
    @Override
    public Long selectCustomerByManagerId(Long manageId) {
        return this.selectOne("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.selectCustomerByManagerId", manageId);
    }

    /**
     * 根据主键删除权限关系
     * @param id 权限关系ID
     * @return
     */
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    /**
     * 新增一条权限关系
     * @param record 权限关系ID
     * @return
     */
    public int insert(ThirdManagerAuthority record) {
        return 0;
    }

    /**
     * 新增一条权限
     * @param record 权限关系对象
     * @return
     */
    public int insertSelective(ThirdManagerAuthority record) {
        return 0;
    }

    /**
     * 根据主键查询第三方管理员权限关系
     * @param id  权限关系对象ID
     * @return
     */
    public ThirdManagerAuthority selectByPrimaryKey(Long id) {
        return null;
    }

    /**
     * 根据主键第三方管理员权限关系
     * @param record 权限关系对象
     * @return
     */
    public int updateByPrimaryKeySelective(ThirdManagerAuthority record) {
        return this.update("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.updateByPrimaryByid", record);
    }

    /**
     * 根据主键修改第三方管理员权限关系
     * @param record 权限关系对象
     * @return
     */
    public int updateByPrimaryKey(ThirdManagerAuthority record) {
        return 0;
    }

    /**
     * 用户信息 只包含用户编号和商家编号
     * @param cust
     *            用户信息 只包含用户编号和商家编号
     * @return
     */
    public Long selectAuthIdByCust(Customer cust) {
        return this.selectOne("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.selectAuthIdByCust", cust);
    }

    /**
     * 根据会员查询权限ID
     * @param customerId
     *            员工编号 {@link Long}
     * @return
     */
    public Long selectAuthIdByCustId(Long customerId) {
        return this.selectOne("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.selectAuthIdByCustId", customerId);
    }

    /**
     * 新增的权限
     * @param paramMap
     *            {@link Map}
     * @return
     */
    public int addRecord(Map<String, Object> paramMap) {
        return this.insert("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.addRecord", paramMap);
    }

}
