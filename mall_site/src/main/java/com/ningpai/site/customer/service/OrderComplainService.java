/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service;

import com.ningpai.site.customer.bean.OrderComplain;
import com.ningpai.util.PageBean;

import java.util.Map;

/**
 * 订单投诉Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 下午3:13:22
 * @version 0.0.1
 */
public interface OrderComplainService {
    /**
     * 添加订单投诉
     * 
     * @param orderComplain
     * @return 0 失败 1成功
     */
    int addComplain(OrderComplain orderComplain);

    /**
     * 查询投诉列表
     * @param paramMap
     * @param pb
     * @return
     */
    PageBean queryComplainList(Map<String, Object> paramMap, PageBean pb);
}
