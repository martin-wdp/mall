package com.ningpai.site.thirdseller.dao;

import com.ningpai.site.thirdseller.bean.CollectionSeller;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;

import java.util.List;
import java.util.Map;

/**
 * 店铺MapperImpl
 * @Description 店铺MapperImpl
 * @author Songhl
 * @since 2015年8月28日 15:39:34
 */
public interface CollectionSellerMapper {
    /**
     * 根据会员Id查询第三方店铺
     * @param storeId
     * @return
     */
    ThirdStoreInfo selectStoreByCustomerId(Long storeId);

    /**
     * 查询是否已经收藏过此商家
     * @param collectionSeller
     * @return int
     */
    int selectCollectionSeller(CollectionSeller collectionSeller);

    /**
     * 收藏商家
     * @param collectionSeller
     * @return int
     */
    int addCollectionSeller(CollectionSeller collectionSeller);

    /**
     * 查询我收藏商家列表总数
     * @param paramMap
     * @return int
     */
    Integer sellerMyFollwCount(Map<String, Object> paramMap);

    /**
     * 查询我收藏商家列表
     * @param paramMap
     * @return
     */
    List<Object> sellerMyFollwList(Map<String, Object> paramMap);

    /**
     * 删除收藏商家
     * @param paramMap
     * @return int
     */
    int delMyFollw(Map<String, Object> paramMap);

}
