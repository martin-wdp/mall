/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import com.ningpai.other.bean.IntegralSet;

/**
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月30日 上午11:59:29
 * @version
 */
public interface IntegralSetMapper {
    /**
     * 查找积分信息
     * 
     * @return
     */
    IntegralSet findPointSet();

    /**
     * 修改注册送积分
     * 
     * @param psetRegister
     * @return
     */
    Integer updateIntegralById(Integer psetRegister);

}
