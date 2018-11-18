/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.InterLogin;
import com.ningpai.system.dao.InterLoginMapper;
import com.ningpai.system.service.InterLoginService;
import com.ningpai.util.PageBean;

/**
 * 登录接口服务层实现
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 上午11:42:40
 * @version
 */
@Service("interLoginService")
public class InterLoginServiceImpl implements InterLoginService {
    /** 登录接口dao */
    @Resource(name = "interLoginMapper")
    private InterLoginMapper interLoginMapper;

    /**
     * 修改登录接口信息
     * 
     * @param interLogin
     * @return int
     */
    public int updateLogin(InterLogin interLogin) {
        // 更改修改时间
        interLogin.setModifyTime(new Date());
        return interLoginMapper.updateByPrimaryKeySelective(interLogin);
    }

    /**
     * 分页查询登录信息
     * 
     * @param pageBean
     * @return List
     */
    public PageBean findByPageBean(PageBean pageBean) {

        // 查询总行数
        pageBean.setRows(interLoginMapper.findTotalCount());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        // 设置列表
        pageBean.setList(interLoginMapper.findByPageBean(map));

        return pageBean;

    }

    /**
     * 添加登录信息
     * 
     * @param interLogin
     * @return
     */
    public int insertLogin(InterLogin interLogin) {
        // 删除标记设置为‘未删除’
        interLogin.setDelFlag("0");
        return interLoginMapper.insertSelective(interLogin);
    }

    /**
     * 删除登录接口信息
     * 
     * @param parameterValues
     * @return int
     */
    public int deleteLogin(String[] loginIds) {
        int count = 0;
        // 判断参数是否为空
        if (loginIds != null) {
            // 循环删除
            for (String id : loginIds) {
                count += interLoginMapper
                        .deleteByPrimaryKey(Long.parseLong(id));
            }
        }
        return count;
    }

    /**
     * 根据ID查询登录接口信息
     * 
     * @param loginId
     * @return InterLogin
     */
    public InterLogin findLoginByLoginId(Long loginId) {
        return interLoginMapper.selectByPrimaryKey(loginId);
    }
}
