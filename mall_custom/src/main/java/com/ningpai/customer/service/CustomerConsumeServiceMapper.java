/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.util.PageBean;

/**
 * 会员充值消费Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午2:59:14
 * @version 0.0.1
 */
public interface CustomerConsumeServiceMapper {
    /**
     * 查询所有会员积分记录
     * 
     * @param paramMap
     *            查询参数 {@link java.util.Map}
     * @return PageBean {@link com.ningpai.util.PageBean}
     */
    PageBean selectAllCustomerConsume(PageBean pageBean);

    /**
     * 删除会员积分记录
     * 
     * @param parameterValues
     *            会员积分记录编号
     * @return 0 失败 1 成功
     */
    int deleteCustomerConsume(String[] parameterValues);

    /**
     * 按条件查找积分记录
     * 
     * @param consume
     *            积分条件
     * @param pageBean
     *            分页PageBean
     * @return pageBean {@link com.ningpai.util.PageBean}
     */
    PageBean selectCustConsumeByCustConsume(CustomerConsume consume,
            PageBean pageBean, String type);
}
