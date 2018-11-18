/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.auth.bean.ThirdAuthority;
import com.ningpai.third.auth.mapper.ThirdAuthorityMapper;

/**
 * @see com.ningpai.third.auth.mapper.ThirdAuthorityMapper
 * @author zhanghl
 * @since 2014年5月10日 上午11:21:25
 * @version 2.0
 */
@Repository("thirdAuthorityMapper")
public final class ThirdAuthorityMapperImpl extends BasicSqlSupport implements ThirdAuthorityMapper {

    @Override
    public ThirdAuthority selectAuthorById(Long id) {
        return this.selectOne("com.ningpai.third.auth.mapper.ThirdAuthorityMapper.selectAuthorById", id);
    }

    /**
     * 删除权限
     * @param id 权限ID thirdId 商家ID
     * @param thirdId
     * @return
     */
    public int deleteByPrimaryKey(Long id,Long thirdId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("auid",id);
        map.put("thirdId",thirdId);
        return this.delete("com.ningpai.third.auth.mapper.ThirdAuthorityMapper.deleteByPrimaryKey", map);
    }

    /**
     * 新增一条权限
     * @param record 第三方权限Bean
     * @return
     */
    public int insert(ThirdAuthority record) {
        return 0;
    }

    /**
     * 根据对象插入信息
     * @param record
     *            第三方权限角色 {@link ThirdAuthority}
     * @return
     */
    public int insertSelective(ThirdAuthority record) {
        record.setFlag("0");
        return this.insert("com.ningpai.third.auth.mapper.ThirdAuthorityMapper.insertSelective", record);
    }

    /**
     * 根据权限ID获取权限对象
     * @param id 权限ID
     * @return
     */
    public ThirdAuthority selectByPrimaryKey(Long id) {
        return null;
    }

    /**
     * 修改权限
     * @param record 权限对象
     * @return
     */
    public int updateByPrimaryKeySelective(ThirdAuthority record) {
        return this.update("com.ningpai.third.auth.mapper.ThirdAuthorityMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改权限
     * @param record 权限对象
     * @return
     */
    public int updateByPrimaryKey(ThirdAuthority record) {
        return 0;
    }

    /**
     *
     * @param storeId
     *            商家编号 {@link java.lang.Long}
     * @return
     */
    public List<ThirdAuthority> queryThirdAuthorityByStotreId(Long storeId) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdAuthorityMapper.queryThirdAuthorityByStotreId", storeId);
    }

    /**
     * 根据商家编号查询商家权限列表
     * @param designation
     *            角色名称 {@link java.lang.String}
     * @param thirdId
     * @return
     */
    public List<ThirdAuthority> selectByDesignation(String designation,Long thirdId) {
        //装载查询的条件
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("designation",designation);
        //商家ID
        map.put("storeId",thirdId);
        return this.selectList("com.ningpai.third.auth.mapper.ThirdAuthorityMapper.selectByDesignationAndTid", map);
    }

}
