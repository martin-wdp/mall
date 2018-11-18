/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.List;

import com.ningpai.customer.bean.CustomerFollow;

/**
 * 会员商品关注Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月1日 下午5:11:16
 * @version 0.0.1
 */
public interface CustomerFollowServiceMapper {
    /**
     * 根据会员编号查找会员关注商品
     * 
     * @param customerId
     *            会员编号 {@link java.lang.Long}
     * @return List {@link java.util.List}
     */
    List<CustomerFollow> selectByCustomerId(Long customerId);
    /**
     * 根据会员编号查找会员关注商品Count
     *
     * @param customerId
     *            会员编号 {@link java.lang.Long}
     * @return List {@link java.util.List}
     */
    int selectByCustomerIdCount(Long customerId);

    /**
     * 根据货品id查询关注的会员id
     * 
     * @param goodsInfoId
     * @return
     */
    List<CustomerFollow> selectSendId(Long goodsInfoId);

}
