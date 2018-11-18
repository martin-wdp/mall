/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Auth;
import com.ningpai.system.util.SelectBean;

/**
 * 第三方登录实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月25日 下午2:26:19
 * @version 1.0
 */
public interface AuthMapper {

    /**
     * 删除第三方登录信息
     * 
     * @param authId
     * @return int
     */
    int deleteByPrimaryKey(Auth auth);

    /**
     * 添加第三方登录信息
     * 
     * @param record
     * @return
     */
    int insert(Auth record);

    /**
     * 添加第三方登录信息
     * 
     * @param record
     * @return int
     */
    int insertSelective(Auth record);

    /**
     * 添加第三方登录信息
     * 
     * @param record
     * @return int
     */
    Auth selectByPrimaryKey(Long authId);

    /**
     * 修改第三方登录信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Auth record);

    /**
     * 修改第三方登录信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Auth record);

    /**
     * 分页查询第三方登录信息
     * 
     * @param map
     * @return
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 根据类型查询
     * 
     * @param authType
     * @return Auth
     */
    Auth findAuthByAuthType(String authType);

    /**
     * 查找已启用的第三方登录接口
     * 
     * @return
     */
    List<Auth> findByShow();

    /**
     * 查询为微信登录的记录
     * 
     * @param map
     * @return
     */
    List<Object> findByWxLogin(Map<String, Object> map);

    /**
     * 查询微信登录的记录条数
     * 
     * @param selectBean
     * @return
     */
    int findTotalwxCount(SelectBean selectBean);
}
