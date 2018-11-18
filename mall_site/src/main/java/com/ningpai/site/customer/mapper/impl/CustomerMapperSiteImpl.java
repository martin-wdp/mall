/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.mapper.CustomerMapperSite;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @see com.ningpai.site.customer.mapper.CustomerMapperSite
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:25:04
 * @version 0.0.1
 */
@Repository("customerMapperFindPwd")
public class CustomerMapperSiteImpl extends BasicSqlSupport implements
        CustomerMapperSite {
    /**
     * 根据用户名查询用户简单信息
     * @param paramMap
     *            查询条件 {@link java.util.Map}
     * @return
     */
    @Override
    public CustomerAllInfo selectCustomerByUname(Map<String, Object> paramMap) {

        return (CustomerAllInfo) this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectCustomerByUname",
                        paramMap).get(0);
    }

}
