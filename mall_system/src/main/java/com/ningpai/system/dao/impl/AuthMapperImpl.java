/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.dao.AuthMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 第三方登录接口实现类
 *
 * @author NINGPAI-LiHaoZe
 * @version 1.0
 * @since 2013年12月25日 下午2:28:00
 */
@Repository("authMapper")
public class AuthMapperImpl extends BasicSqlSupport implements AuthMapper {

    /**
     * 删除第三方登录信息
     */
    public int deleteByPrimaryKey(Auth auth) {
        // 删除第三方登录信息
        return this.delete(
                "com.ningpai.system.dao.AuthMapper.deleteByPrimaryKey", auth);
    }

    /**
     * 添加第三方登录信息
     *
     * @param record
     * @return
     */
    public int insert(Auth record) {
        return 0;
    }

    /**
     * 添加第三方登录信息
     *
     * @param record
     * @return int
     */
    public int insertSelective(Auth record) {
        // 插入第三方登录数据
        return this.insert("com.ningpai.system.dao.AuthMapper.insertSelective",
                record);
    }

    /**
     * 添加第三方登录信息
     *
     * @param record
     * @return int
     */
    public Auth selectByPrimaryKey(Long authId) {
        return this.selectOne(
                "com.ningpai.system.dao.AuthMapper.selectByPrimaryKey", authId);
    }

    /**
     * 修改第三方登录信息
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Auth record) {
        return this
                .update("com.ningpai.system.dao.AuthMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改第三方登录信息
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Auth record) {
        return 0;
    }

    /**
     * 分页查询第三方登录信息
     *
     * @param map
     * @return
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.AuthMapper.findByPageBean", map);
    }

    /**
     * 查询总行数
     *
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.system.dao.AuthMapper.findTotalCount", selectBean);
    }

    /**
     * 根据类型查询
     */
    public Auth findAuthByAuthType(String authType) {
        List<Auth> alist = this.selectList(
                "com.ningpai.system.dao.AuthMapper.findAuthByAuthType",
                authType);
        if (alist != null && !alist.isEmpty()) {
            return alist.get(0);
        }
        return null;
    }

    /*
     * 查找已启用的第三方登录接口
     * 
     * @see com.ningpai.system.dao.AuthMapper#findByShow()
     */
    @Override
    public List<Auth> findByShow() {
        return this.selectList("com.ningpai.system.dao.AuthMapper.findByShow");
    }

    /**
     * 查询为微信登录的记录
     */
    @Override
    public List<Object> findByWxLogin(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.AuthMapper.findByWxLogin", map);
    }

    /**
     * 查询微信登录的记录条数
     *
     * @param selectBean
     * @return
     */
    @Override
    public int findTotalwxCount(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.system.dao.AuthMapper.findTotalwxCount",
                selectBean);
    }

}
