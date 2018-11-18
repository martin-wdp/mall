/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.dao.IntegralSetMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.other.bean.IntegralSet;

/**
 * @see com.ningpai.customer.dao.IntegralSetMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月30日 下午2:07:31
 * @version
 */
@Repository("integralSetMapper")
public class IntegralSetMapperImpl extends BasicSqlSupport implements
        IntegralSetMapper {
    /*
     * 
     * 
     * @see com.ningpai.customer.dao.IntegralSetMapper#findPointSet()
     */
    @Override
    public IntegralSet findPointSet() {
        return this
                .selectOne("com.ningpai.customer.dao.IntegralSetMapper.selectByFirst");
    }

    @Override
    public Integer updateIntegralById(Integer psetRegister) {
        return this
                .update("com.ningpai.customer.dao.IntegralSetMapper.updateIntegralById",
                        psetRegister);
    }
}
