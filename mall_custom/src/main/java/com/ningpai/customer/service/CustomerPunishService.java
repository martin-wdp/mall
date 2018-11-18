/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.service;

import java.util.List;

import com.ningpai.customer.bean.CustomerPunish;
import com.ningpai.util.PageBean;

/**
 * 会员处罚service层
 * 
 * @author Zhangsl
 *
 */
public interface CustomerPunishService {

    /**
     * 分页查询
     * 
     * @param pageBean
     * @return
     */
    PageBean selectPunishInfoByPage(PageBean pageBean);

    /**
     * 添加
     */

    int addPunishInfo(CustomerPunish customerPunish);

    /**
     * 根据Id 查询
     */

    CustomerPunish queryPunishInfoById(Long id);

    /**
     * 根据ID修改
     */

    int updatePunishInfoById(CustomerPunish customerPunish);

    /**
     * 删除（逻辑）
     */

    int updateDelflagById(Long id);
    /**
     * 查询所有
     *
     * */
    List<CustomerPunish> queryAllRules();

    /**
     * 查询单个
     *
     * @param customerPunish
     * */
    CustomerPunish queryIdByRule(CustomerPunish customerPunish);

}
