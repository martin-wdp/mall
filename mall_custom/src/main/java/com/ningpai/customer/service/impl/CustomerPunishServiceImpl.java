/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.CustomerPunish;
import com.ningpai.customer.dao.CustomerPunishMapper;
import com.ningpai.customer.service.CustomerPunishService;
import com.ningpai.util.PageBean;
/**
 * 会员处罚service实现层
 *
 * @author Zhangsl
 *
 */
@Service("CustomerPunishService")
public class CustomerPunishServiceImpl implements CustomerPunishService {

    @Resource(name = "CustomerPunishMapper")
    private CustomerPunishMapper customerPunishMapper;
    /**
     * 分页查询
     *
     * @param pageBean
     * @return
     */
    @Override
    public PageBean selectPunishInfoByPage(PageBean pageBean) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            pageBean.setRows(customerPunishMapper.selectPunishInfoCount());
            pageBean.setList(customerPunishMapper
                    .selectPunishInfoByPage(paramMap));
        } finally {
            paramMap = null;
        }

        return pageBean;
    }
    /**
     * 添加
     */
    @Override
    public int addPunishInfo(CustomerPunish customerPunish) {
        customerPunish.setCreateTime(new Date());
        customerPunish.setModifiedTime(new Date());
        return customerPunishMapper.insertSelective(customerPunish);
    }
    /**
     * 根据Id 查询
     */
    @Override
    public CustomerPunish queryPunishInfoById(Long id) {

        return customerPunishMapper.selectByPrimaryKey(id);
    }
    /**
     * 根据ID修改
     */

    @Override
    public int updatePunishInfoById(CustomerPunish customerPunish) {
        customerPunish.setModifiedTime(new Date());
        return customerPunishMapper.updateByPrimaryKeySelective(customerPunish);
    }
    /**
     * 删除（逻辑）
     */
    @Override
    public int updateDelflagById(Long id) {

        return customerPunishMapper.updateDelflag(id);
    }
    /**
     * 查询所有
     *
     * */
    @Override
    public List<CustomerPunish> queryAllRules() {

        return customerPunishMapper.queryAllRules();
    }
    /**
     * 查询单个
     *
     * @param customerPunish
     * */
    @Override
    public CustomerPunish queryIdByRule(CustomerPunish customerPunish) {

        return customerPunishMapper.queryIdByRule(customerPunish);
    }

}
