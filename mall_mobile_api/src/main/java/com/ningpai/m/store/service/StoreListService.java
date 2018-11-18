package com.ningpai.m.store.service;

import com.ningpai.thirdaudit.bean.GoodsCateGory;
import com.ningpai.util.PageBean;
import java.util.List;


/**
 * Created by zhanghailong on 2015/6/9.
 *
 */

public interface StoreListService {
    /**
     * 删除关注记录
     * @param storeId
     * @param customerId
     * @return
     */
    int deleteController(Long storeId,Long customerId);
    /**
     * 判断此是否关注此店铺
     * @param storeId
     * @param customerId
     * @return
     */
    int selectController(Long storeId,Long customerId);

    /**
     * 查询所有的商品一级分类
     * @return 一级分类的集合
     * @author zhanghl
     */
    List<GoodsCateGory> selectgoodscatebyone();
    /**
     *查询最近上架的商品集合
     * @param storeId
     * @author zhanghl
     * storeId 店铺ID
     * @return
     */
    PageBean setStoreNewProcudtList(PageBean pb,Long storeId);
    /**
     * 关注店铺
     * @param customerId
     * @param storeId
     * @author zhanghl
     * storeId 店铺ID
     * customerId 会员ID
     * @return
     */
    int addCollectionSeller(Long customerId,Long storeId);

    /**
     * Store列表
     * @param pb  商品集合
     * @param cateId 商品分类一级ID
     * @param customerId 当前登录的用户
     * @author zhanghl
     * @return 商铺信息
     */
    PageBean selectStoreList(PageBean pb,Long cateId,Long customerId);

}
