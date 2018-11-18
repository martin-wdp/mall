/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.mapper.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.auth.bean.ThirdAuthorityPage;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper;

/**
 * @see com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月7日 下午5:19:08
 * @version 2.0
 */
@Repository("thirdAuthorityPageMapper")
public class ThirdAuthorityPageMapperImpl extends BasicSqlSupport implements ThirdAuthorityPageMapper {
    /**
     * 根据主键删除权限关系
     * @param id 第三方权限页面id
     * @return
     */

    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    /**
     * 新增权限关系
     * @param record 第三方权限页面
     * @return
     */

    public int insert(ThirdAuthorityPage record) {
        return 0;
    }

    /**
     * 新增权限关系
     * @param record 第三方权限页面
     * @return
     */
    public int insertSelective(ThirdAuthorityPage record) {
        return 0;
    }

    /**
     * 根据主键查询权限关系
     * @param id 第三方权限页面id
     * @return
     */
    public ThirdAuthorityPage selectByPrimaryKey(Long id) {
        return null;
    }

    /**
     * 根据主键修改权限关系
     * @param record 权限对象
     * @return
     */
    public int updateByPrimaryKeySelective(ThirdAuthorityPage record) {
        return 0;
    }

    /**
     * 根据主键修改权限关系
     * @param record 权限对象
     * @return
     */
    public int updateByPrimaryKey(ThirdAuthorityPage record) {
        return 0;
    }

    /**
     * 获取第三方管理员权限关系集合
     * @param num
     *            权限编号
     * @return
     */
    public List<ThirdPage> selectByAuthorityID(Long num) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper.selectByAuthorityID", num);
    }

    /**
     * 获取第三方管理员权限关系集合
     * @param num
     *            权限编号
     * @return
     */
    public List<ThirdPage> selectAllMenuVos(Long num) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper.selectAllMenuVos", num);
    }

    /**
     *
     * 获取第三方管理员权限关系集合
     * @param authorityId
     *            权限编号
     * @return
     */
    public List<ThirdPage> selectAllPageByAuthorityID(Long authorityId) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper.selectAllPageByAuthorityID", authorityId);
    }

    /**
     *  新增第三方管理员权限关系集合
     * @param paramMap 状态要新增的数据
     * @return
     */
    public int addPageToAuthority(Map<String, Object> paramMap) {
        return this.insert("com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper.addPageToAuthority", paramMap);
    }

    /**
     * 删除第三方管理员权限关系集合
     * @param paramMap
     * @return
     */
    public int delPageToAuthority(Map<String, Object> paramMap) {
        return this.update("com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper.delPageToAuthority", paramMap);
    }

    /**
     * 获取第三方管理员权限关系集合
     * @param parmaMap
     * @return
     */
    public List<ThirdPage> selectPageByAuthIdAndParentId(Map<String, Object> parmaMap) {
        return this.selectList("com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper.selectPageByAuthIdAndParentId", parmaMap);
    }

}
