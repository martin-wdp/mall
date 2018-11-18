package com.ningpai.site.thirdseller.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.thirdseller.bean.CollectionSeller;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;
import com.ningpai.site.thirdseller.dao.CollectionSellerMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺MapperImpl
 * @Description 店铺MapperImpl
 * @author Songhl
 * @since 2015年8月28日 15:39:34
 */
@Repository("CollectionSellerMapper")
public class CollectionSellerMapperImpl extends BasicSqlSupport implements
        CollectionSellerMapper {
    /**
     * 根据会员Id查询第三方店铺
     * @param storeId
     * @return
     */
    @Override
    public ThirdStoreInfo selectStoreByCustomerId(Long storeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeId",storeId);
        return this.selectOne("com.ningpai.site.dao.CollectionSellerMapper.selectStoreByCustomerId",paramMap);
    }

    /**
     * 查询是否已经收藏过此商家
     * @param collectionSeller
     * @return
     */
    @Override
    public int selectCollectionSeller(CollectionSeller collectionSeller) {
        
        return this.selectOne("com.ningpai.site.dao.CollectionSellerMapper.selectCollectionSeller",collectionSeller);
    }

    /**
     * 收藏商家
     * @param collectionSeller
     * @return
     */
    @Override
    public int addCollectionSeller(CollectionSeller collectionSeller) {
        
        return this.insert("com.ningpai.site.dao.CollectionSellerMapper.addCollectionSeller", collectionSeller);
    }

    /**
     * 查询我收藏商家列表总数
     * @param paramMap
     * @return
     */
    @Override
    public Integer sellerMyFollwCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.sellerMyFollwCount",paramMap);
    }

    /**
     * 查询我收藏商家列表
     * @param paramMap
     * @return
     */
    @Override
    public List<Object> sellerMyFollwList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.thirdaudit.mapper.StoreInfoMapper.sellerMyFollwList",paramMap);
    }

    /**
     * 删除收藏商家
     * @param paramMap
     * @return
     */
    @Override
    public int delMyFollw(Map<String, Object> paramMap) {
        
        return this.insert("com.ningpai.site.dao.CollectionSellerMapper.delMyFollw",paramMap);
    }

}
