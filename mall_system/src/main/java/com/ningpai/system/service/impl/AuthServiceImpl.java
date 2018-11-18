/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.Auth;
import com.ningpai.system.dao.AuthMapper;
import com.ningpai.system.service.AuthService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 第三方登录服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月25日 下午2:30:42
 * @version 1.0
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {
    /** spring注解 */
    @Resource(name = "authMapper")
    private AuthMapper authMapper;

    /**
     * 第三方登录分页查询
     * 
     * @param pageBean
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 设置总行数
        pageBean.setRows(authMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置显示列表
        pageBean.setList(authMapper.findByPageBean(map));

        return pageBean;
    }

    /**
     * 根据类型查询
     * 
     * @see
     * com.ningpai.system.service.AuthService#findAuthByAuthType(java.lang.String
     * )
     */
    public Auth findAuthByAuthType(String authType) {
        return authMapper.findAuthByAuthType(authType);
    }

    /**
     * 添加第三方登录
     * 
     * @param auth
     * @return int
     */
    public int insertAuth(Auth auth) {
        // 添加第三方登录信息
        auth.setAuthDeleteFlag("0");
        auth.setAuthCreated(new Date());
        return authMapper.insertSelective(auth);
    }

    /**
     * 删除第三方登录
     * 
     * @param authIds
     *            删除人
     * @return int
     */
    public int deleteAuth(String[] authIds) {
        int count = 0;
        // 设置删除参数
        Auth auth = new Auth();
        // 设置删除时间
        auth.setAuthDeleted(new Date());
        // 删除导航信息
        if (authIds != null) {
            // 循环数组
            for (String id : authIds) {
                // 设置删除ID
                auth.setAuthId(Long.parseLong(id));

                count += authMapper.deleteByPrimaryKey(auth);
            }
        }
        return count;
    }

    /**
     * 根据id查询信息
     * 
     * @param authId
     * @return Auth
     */
    public Auth findAuthByAuthId(Long authId) {
        // 根据id查询信息
        return authMapper.selectByPrimaryKey(authId);
    }

    /**
     * 修改第三方登录信息
     * 
     * @param auth
     * @return int
     */
    public int updateAuth(Auth auth) {
        // 修改登录信息
        auth.setAuthUpdated(new Date());
        return authMapper.updateByPrimaryKeySelective(auth);
    }

    /**
     * 根据第三方登录接口ID修改启用状态
     * 
     * @see
     * com.ningpai.system.service.AuthService#updateUserdStatus(java.lang.Long)
     */
    @Override
    public boolean updateUserdStatus(Long authId) {
        Auth auth = authMapper.selectByPrimaryKey(authId);
        if ("0".equals(auth.getAuthShow())) {
            auth.setAuthShow("1");
        } else {
            auth.setAuthShow("0");
        }
        auth.setAuthUpdated(new Date());
        return authMapper.updateByPrimaryKeySelective(auth) > 0;
    }

    /**
     * 查找已启用的第三方登录接口
     * 
     * @see com.ningpai.system.service.AuthService#findByShow()
     */
    @Override
    public List<Auth> findByShow() {
        return this.authMapper.findByShow();
    }

    /**
     * 根据主键删除记录
     * 
     * @param authId
     *            主键id
     * @return 执行结果
     */
    @Override
    public int deleteAuthById(Long authId) {
        Auth auth = new Auth();
        auth.setAuthId(authId);
        auth.setAuthDeleteFlag("1");
        auth.setAuthDeleted(new Date());
        return authMapper.updateByPrimaryKeySelective(auth);
    }

    /**
     * 查询微信登录接口（移动版登录接口）
     * 
     * @param pageBean
     * @param selectBean
     * @return
     */
    public PageBean findBywxPageBean(PageBean pageBean, SelectBean selectBean) {
        // 设置总行数
        pageBean.setRows(authMapper.findTotalwxCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置显示列表
        pageBean.setList(authMapper.findByWxLogin(map));
        return pageBean;
    }

}
