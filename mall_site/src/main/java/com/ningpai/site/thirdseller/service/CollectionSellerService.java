package com.ningpai.site.thirdseller.service;

import com.ningpai.site.thirdseller.bean.CollectionSeller;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;
import com.ningpai.util.PageBean;

/**
 * 店铺集合控制器
 * @Description 店铺集合控制器
 * @author Songhl
 * @since 2015年8月28日 15:26:54
 */
public interface CollectionSellerService {
    /**
     * 根据会员Id查询第三方店铺
     * @param storeId
     * @return
     */
    ThirdStoreInfo selectStoreByCustomerId(Long storeId);

    /**
     * 收藏此商家
     * @param collectionSeller
     * @return int
     */
    int addCollectionSeller(CollectionSeller collectionSeller);

    /**
     * 查询商家列表
     * @param customerId
     * @param pageBean
     * @return PageBean
     */
    PageBean sellerMyFollw(Long customerId, PageBean pageBean);
    /**
     * 删除收藏
     * @param customerId
     * @param collectionSellerId
     * @return int
     */
    int delMyFollw(Long customerId, Long collectionSellerId);

}
