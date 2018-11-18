package com.ningpai.m.store.mapper.impl;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.m.store.bean.StoreStreetThirdImage;
import com.ningpai.m.store.mapper.StoreListMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghailong on 2015/6/9.
 */
@Repository("storeListMapper")
public class StoreListMapperImpl extends BasicSqlSupport implements StoreListMapper {

    private static final String STOREID = "storeId";
    private static final String CUSTOMERID = "customerId";

    /**
     * 删除关注记录
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#deleteController(java.lang.Long,
     *      java.lang.Long)
     */
    @Override
    public int deleteController(Long storeId, Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 店铺ID
        map.put(STOREID, storeId);
        // 会员ID
        map.put(CUSTOMERID, customerId);
        return this.delete("com.ningpai.mybatis.mapper.StoreListMapper.deleteController", map);
    }

    /**
     * 查询所有的商品一级分类
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#selectgoodscatebyone()
     */
    @Override
    public List<GoodsCateGory> selectgoodscatebyone() {
        return selectList("com.ningpai.web.dao.GoodsCateGoryMapper.selectgoodscatebyone");
    }

    /**
     * 是否关注
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#IsCollection(java.lang.Long,
     *      java.lang.Long)
     */
    @Override
    public Integer IsCollection(Long storeId, Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 店铺ID
        map.put(STOREID, storeId);
        // 会员ID
        map.put(CUSTOMERID, customerId);
        return this.selectOne("com.ningpai.mybatis.mapper.StoreListMapper.IsCollection", map);
    }

    /**
     * 查询最近上架的商品集合
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#StoreNewProcudtList(java.lang.Long)
     */
    @Override
    public List<Object> StoreNewProcudtList(Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STOREID, storeId);
        return this.selectList("com.ningpai.mybatis.mapper.StoreListMapper.setStoreNewProcudtList", map);
    }

    /**
     * 关注店铺
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#addCollectionSeller(java.lang.Long,
     *      java.lang.Long)
     */
    @Override
    public int addCollectionSeller(Long customerId, Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 店铺ID
        map.put(STOREID, storeId);
        // 会员ID
        map.put(CUSTOMERID, customerId);
        // 创建时间
        map.put("newdate", new Date());
        // 是否删除
        map.put("del", '0');
        return this.insert("com.ningpai.mybatis.mapper.StoreListMapper.addCollectionSeller", map);
    }

    /**
     * 获取该店铺下面的图片信息
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#selectStoreStreetListImage(java.lang.Long)
     */
    @Override
    public List<StoreStreetThirdImage> selectStoreStreetListImage(Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STOREID, storeId);
        return this.selectList("com.ningpai.mybatis.mapper.StoreListMapper.selectStoreStreetListImage", map);
    }

    /**
     * 查询Store数量
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#selectStoreNum(java.util.Map)
     */
    @Override
    public Integer selectStoreNum(Map<String, Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.StoreListMapper.selectStoreNum", map);
    }

    /**
     * 获取指定店铺下面的最新上架货品
     *
     * @param storeId
     *            店铺ID
     * @return
     */
    @Override
    public Long selectStoreNewUpProductCount(Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STOREID, storeId);
        return this.selectOne("com.ningpai.mybatis.mapper.StoreListMapper.selectStoreNewUpProductCount", map);
    }

    /**
     * 获取所有的商铺信息
     * 
     * @see com.ningpai.m.store.mapper.StoreListMapper#selectStoreList(java.util.Map)
     */
    @Override
    public List<Object> selectStoreList(Map<String, Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.StoreListMapper.selectStoreList", map);
    }

    /**
     * 获取指定店铺下面的促销信息
     *
     * @param storeCustomerId
     * @return
     */
    @Override
    public List<Marketing> selectMarketingByBusinessId(Long storeCustomerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 0:商品促销 1：订单促销
        map.put("marketingType", "0");
        // 商家ID
        map.put("businessId", storeCustomerId);
        return this.selectList("com.ningpai.mybatis.mapper.StoreListMapper.selectMarketingByBusinessId", map);
    }

    /**
     * 获取指定店铺下面的优惠券信息
     * 
     * @param storeId
     * @return
     */
    @Override
    public List<Coupon> selectCouponByBusinessId(Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 商家ID
        map.put("businessId", storeId);
        return this.selectList("com.ningpai.mybatis.mapper.StoreListMapper.selectCouponByBusinessId", map);
    }
}
