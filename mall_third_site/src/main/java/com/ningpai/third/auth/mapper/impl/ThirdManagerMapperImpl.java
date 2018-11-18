/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.mapper.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.customer.bean.Customer;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.auth.bean.ThirdManager;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.mapper.ThirdManagerMapper;

/**
 * @see com.ningpai.third.auth.mapper.ThirdManagerMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月7日 下午4:06:03
 * @version 0.0.1
 */
@Repository("thirdManagerMapper")
public class ThirdManagerMapperImpl extends BasicSqlSupport implements ThirdManagerMapper {
    /**
     * 根据主键删除管理员
     * @param id 权限关系ID
     * @return
     */
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    /**
     * 新增管理员
     * @param record 权限关系对象
     * @return
     */
    public int insert(ThirdManager record) {
        return 0;
    }

    /**
     * 新增管理员
     * @param record 权限关系对象
     * @return
     */
    public int insertSelective(ThirdManager record) {
        return 0;
    }

    /**
     * 根据主键获取管理员信息
     * @param id 权限关系ID
     * @return
     */
    public ThirdManager selectByPrimaryKey(Long id) {
        return null;
    }

    /**
     * 根据主键修改管理员信息
     * @param record  权限关系
     * @return
     */
    public int updateByPrimaryKeySelective(ThirdManager record) {
        return 0;
    }

    /**
     * 根据主键修改管理员信息
     * @param record 权限关系
     * @return
     */
    public int updateByPrimaryKey(ThirdManager record) {
        return 0;
    }

    /**
     * 根据ID获取会员信息
     * @param cid
     *            用户编号
     * @return
     */
    public Customer selectCustByCid(Long cid) {
        return this.selectOne("com.ningpai.third.auth.mapper.ThirdManagerMapper.selectCustByCid", cid);
    }

    /**
     * 根据商铺ID 获取第三方管理员集合
     * @param stordId
     *            商家编号 {@link java.lang.Long}
     * @return
     */
    public List<ThirdManager> queryThirdManagerByStoreId(Long stordId) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdManagerMapper.queryThirdManagerByStoreId", stordId);
    }

    /**
     * 获取第三方管理员权限关系集合
     * @return
     */
    public List<ThirdPage> selectAllAuthority() {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdPageMapper.selectAllAuthority");
    }

    /**
     * 根据商家ID获取下面的员工的数量
     * @param stordId
     *            商家编号 {@link Long}
     * @return
     */
    public Long queryEmployeeList(Long stordId) {
        return this.selectOne("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.queryEmployeeList", stordId);
    }

    /**
     * 获取会员集合
     * @param paramMap
     *            查询内容 {@link Map} 包含分页辅助类信息 和商家编号信息
     * @return
     */
    public List<Object> queryEmployeeListByStotreId(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper.queryEmployeeListByStotreId", paramMap);
    }

}
