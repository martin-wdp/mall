/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper;

import com.ningpai.site.customer.vo.CustomerAllInfo;

import java.util.Map;

/**
 * 会员MapperSite
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:07:58
 * @version 0.0.1
 */
public interface CustomerMapperSite {
    /**
     * 根据用户名查询用户简单信息
     * 
     * @param paramMap
     *            查询条件 {@link java.util.Map}
     * @return 用户简单信息 {@link com.ningpai.customer.bean.Customer}
     */
    CustomerAllInfo selectCustomerByUname(Map<String, Object> paramMap);

}
