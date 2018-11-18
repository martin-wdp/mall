/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.Map;

import com.ningpai.customer.bean.ComplainVo;
import com.ningpai.customer.bean.OrderComplainBack;
import com.ningpai.util.PageBean;

/**
 * 订单投诉Service
 * 
 * @author jiping
 * @since 2014年7月22日 下午5:50:08
 * @version 0.0.1
 */
public interface OrderComplainBackService {
    /**
     * 查询未处理投诉记录
     * 
     * @param paramMap
     * @param pb
     * @return
     */
    PageBean queryComplainList(PageBean pb, ComplainVo orderCB);

    /**
     * 根据投诉id查询投诉记录
     * 
     * @param complainId
     * @return 投诉记录
     */
    OrderComplainBack selectByPrimaryKey(Long complainId);

    /**
     * 回复投诉
     */
    int replayCom(Map<String, Object> paramMap);

    /**
     * 查询已投诉记录
     */
    PageBean queryComplainHadList(PageBean pb, ComplainVo orderCB);
}
