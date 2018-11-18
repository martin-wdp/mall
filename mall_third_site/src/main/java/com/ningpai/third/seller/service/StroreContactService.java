/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.third.seller.bean.StoreContact;

/**
 * 联系人信息Service
 * 
 * @author Zhouh
 * @since 2014年5月21日 上午11:27:09
 * @version 0.0.1
 * 
 */
public interface StroreContactService {
    /**
     * 根据店铺查询联系人信息
     * 
     * @param storeId
     *            店铺编号 {@link Long}
     * @return List<StoreContact> 商家关联联系人集合 {@link List} {@link com.ningpai.third.seller.bean.StoreContact}
     */
    List<StoreContact> selectByStoreId(Long storeId);

    /**
     * 修改卖家信息
     * 
     * @param storeContact
     *            店铺联系人信息 {@link storeContact}
     * @return 0失败 1成功
     */
    int updateByPrimaryKeySelective(StoreContact storeContact);

    /**
     * 新增卖家信息
     * 
     * @param request
     * @param storeContact
     *            店铺联系人信息 {@link storeContact}
     * @return 0失败 1成功
     */
    int insertStoreSelective(HttpServletRequest request, StoreContact storeContact);
}
