/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.service;

import java.util.Map;

import com.ningpai.thirdaudit.bean.StoreInfo;

/**
 * 商家信息服务层接口
 * */
public interface StoreInfoService {
    /**
     * 查询所以
     * 
     * @return
     */
    /*
     * public PageBean queryAllStoreInfo(PageBean pb);
     */

    /**
     * 根据装饰城id或城市id查询所属装饰城
     * 
     * @param pageBean
     * @param cityId
     * @param mallId
     * @param orderId
     * @return
     */
    /*
     * public PageBean queryStoreByMallIdOrCityId(PageBean pageBean,Long
     * cityId,Long mallId,Long orderId,String title);
     * 
     * public List<StoreInfoVo> queryStoreByrecommendedHomePage();
     */

    /**
     * 查询商家
     * 
     * @param sellerId
     * @return
     */
    /*
     * public StoreInfo queryStoreInfoBySellerId(Long sellerId);
     */

    /**
     * 删除商家
     * 
     * @param storeInfoIds
     * @return
     */
    public int delStoreInfoById(String[] storeInfoIds, String username);

    /**
     * 根据商家id查询
     *
     * @param storeId
     * */
    StoreInfo queryStorePointByThirdId(Long storeId);
    /**
     * 高级查询
     *
     * */
    int upStorePointByThirdId(Map<String, Object> paramMap);
    /**
     *根据map修改记录
     *
     * @param paramMap
     * */
    int upStoreBalanceByThirdId(Map<String, Object> paramMap);

    /**
     * 查询记录
     *
     * @param storeId
     * */
    StoreInfo queryStoreBalanceByThirdId(Long storeId);

}
