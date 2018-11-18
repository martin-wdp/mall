package com.ningpai.m.store.mapper;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.m.store.bean.StoreStreetThirdImage;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghailong on 2015/6/9.
 */
public interface StoreListMapper {
    /**
     * 删除关注记录
     * @param storeId
     * @param customerId
     * @return
     */
    int deleteController(Long storeId,Long customerId);
    /**
     * 查询所有的商品一级分类
     * @return 一级分类的集合
     * @author zhanghl
     */
    List<GoodsCateGory> selectgoodscatebyone();
    /**
     * 是否关注
     * @param storeId 店铺ID
     * @param customerId 会员
     * @return 是否被关注过
     */
    Integer IsCollection(Long storeId,Long customerId);
    /**
     *查询最近上架的商品集合
     * @param storeId
     * @author zhanghl
     * storeId 店铺ID
     * @return
     */
    List<Object> StoreNewProcudtList(Long storeId);
    /**
     * 关注店铺
     * @param customerId
     * @param storeId
     * @return
     */
    int addCollectionSeller(Long customerId,Long storeId);
    /**
     * 获取该店铺下面的图片信息
     * @param storeId
     * @return
     */
    List<StoreStreetThirdImage> selectStoreStreetListImage(Long storeId);

    /**查询Store数量
     * @param map 装载查询条件的集合
     * @return
     */
    Integer selectStoreNum( Map<String,Object> map);

    /**
     * 获取指定店铺下面的最新上架货品
     * @param storeId
     * @return
     */
    Long selectStoreNewUpProductCount(Long storeId);
    /**
     * 获取所有的商铺信息
     * @return
     */
    List<Object> selectStoreList(Map<String,Object> map);


    /**
     * 获取指定店铺下面的促销信息
     * @param storeCustomerId 注册店铺的会员
     * @return
     */
    List<Marketing> selectMarketingByBusinessId(Long storeCustomerId);


    /**
     * 获取指定店铺下面的优惠券信息
     * @return
     */
    List<Coupon> selectCouponByBusinessId(Long storeCustomerId);


}
