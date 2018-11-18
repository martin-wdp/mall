package com.ningpai.m.store.service.impl;

import com.ningpai.m.store.bean.StoreInfoVo;
import com.ningpai.m.store.mapper.StoreListMapper;
import com.ningpai.m.store.service.StoreListService;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghailong on 2015/6/9.
 */
@Service("storeListService")
public class StoreListServiceImpl implements StoreListService {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(StoreListServiceImpl.class);

    //店铺列表
    @Resource(name = "storeListMapper")
    private StoreListMapper storeListMapper;

    /**
     * 删除关注记录
     *
     * @see com.ningpai.m.store.service.StoreListService#deleteController(java.lang.Long, java.lang.Long)
     */
    @Override
    public int deleteController(Long storeId, Long customerId) {
        return storeListMapper.deleteController(storeId, customerId);
    }

    /**
     * 判断此是否关注此店铺
     *
     * @see com.ningpai.m.store.service.StoreListService#selectController(java.lang.Long, java.lang.Long)
     */
    @Override
    public int selectController(Long storeId, Long customerId) {
        return storeListMapper.IsCollection(storeId, customerId);
    }

    /**
     * 查询所有的商品一级分类
     *
     * @see com.ningpai.m.store.service.StoreListService#selectgoodscatebyone()
     */
    @Override
    public List<GoodsCateGory> selectgoodscatebyone() {
        return storeListMapper.selectgoodscatebyone();
    }

    /**
     * 查询最近上架的商品集合
     *
     * @see com.ningpai.m.store.service.StoreListService#setStoreNewProcudtList(com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean setStoreNewProcudtList(PageBean pb, Long storeId) {
        //货品信息
        pb.setList(storeListMapper.StoreNewProcudtList(storeId));
        //货品的行数
        pb.setRows(storeListMapper.selectStoreNewUpProductCount(storeId).intValue());
        return pb;
    }

    /**
     * 关注店铺
     *
     * @see com.ningpai.m.store.service.StoreListService#addCollectionSeller(java.lang.Long, java.lang.Long)
     */
    @Override
    public int addCollectionSeller(Long customerId, Long storeId) {
        return this.storeListMapper.addCollectionSeller(customerId, storeId);
    }

    /**
     * Store列表
     *
     * @see com.ningpai.m.store.service.StoreListService#selectStoreList(com.ningpai.util.PageBean, java.lang.Long, java.lang.Long)
     */
    @Override
    public PageBean selectStoreList(PageBean pb, Long cateId, Long customerId) {
        Long cateIdNew = cateId;
        //装载分页使用的条件
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //cateId==-1 为竞选 查所有店铺
            if (null != cateIdNew) {
                cateIdNew = cateIdNew == -1 ? null : cateIdNew;
            }
            //分页的开始页
            map.put("startRowNum", pb.getStartRowNum());
            //分页的结束页
            map.put("endRowNum", pb.getEndRowNum());
            //分类ID
            map.put("cateId", cateIdNew);
            //获取有效数据的总行数
            pb.setRows(this.storeListMapper.selectStoreNum(map) == null ? 0 : storeListMapper.selectStoreNum(map));
            //根据条件查询获取 店铺集合
            List<Object> storeInfos = storeListMapper.selectStoreList(map);
            if (null != storeInfos && !storeInfos.isEmpty()) {
                //遍历店铺集合
                for (int i = 0; i < storeInfos.size(); i++) {
                    //创建单个店铺对象
                    StoreInfoVo storeInfo = (StoreInfoVo) storeInfos.get(i);
                    //根据店铺ID和会员ID查询该店铺是否关注过
                    if (null != storeInfo.getStoreId() && null != customerId) {
                        storeInfo.setIsCollection(this.storeListMapper.IsCollection(storeInfo.getStoreId(), customerId) == 0 ? 0 : this.storeListMapper.IsCollection(storeInfo.getStoreId(), customerId));
                    }
                   /* //获取该店铺下面的图片信息
                    List<StoreStreetThirdImage> store_images = storeListMapper.selectStoreStreetListImage(storeInfo.getStoreId());
                    if(null != store_images ){
                        //装载图片信息
                        storeInfo.setStore_images(store_images);
                    }
                    if (null != storeInfo.getCustomerid()) {
                        //获取该店铺最近上架的商品
                        storeInfo.setStoreNewProcudtCount(storeListMapper.selectStoreNewUpProductCount(storeInfo.getStoreId()));
                        //促销信息
                        List<Marketing> marketings = storeListMapper.selectMarketingByBusinessId(storeInfo.getStoreId());
                        if (null != marketings && marketings.size() > 0) {
                            storeInfo.setMarketinglist(marketings);
                        }
                        //优惠券信息
                        List<Coupon> coupons = storeListMapper.selectCouponByBusinessId(storeInfo.getStoreId());
                        if (null != coupons && coupons.size() > 0) {
                            storeInfo.setCouponlist(coupons);
                        }
                    }*/

                }

            }
            //装载店铺集合数据
            pb.setList(storeInfos);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return pb;
    }
}
