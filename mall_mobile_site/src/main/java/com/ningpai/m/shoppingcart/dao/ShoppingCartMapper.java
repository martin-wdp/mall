/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.shoppingcart.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.m.shoppingcart.bean.ShoppingCart;
import com.ningpai.m.shoppingcart.bean.StoreTemp;

/**
 * @author ggn
 *
 */
public interface ShoppingCartMapper {
    /**
     * 查询我的购物车
     * @param  {@link java.lang.Long}
     * @return List
     */ 
    List<Object> shoppingCart(Map<String,Object> paramMap);

    /**
     * 删除
     * @param shoppingCartId
     * @return int
     */
    int delShoppingCartById(Long shoppingCartId);
    /**
     * 修改购买数量
     * @param sc
     * @return int
     */
    int changeShoppingCartById(ShoppingCart sc);

    /**
     * 修改优惠信息
     * @param sc
     * @return int
     */
    int changeShoppingCartMarket(ShoppingCart sc);

    /**
     * 根据购物车查询第三方id和名称
     * @param shopcartIds
     * @return
     */
    List<StoreTemp> selectStoreTempByshopcartIds(List<Long> shopcartIds);

    /**
     * 查询购物车的详细信息
     * @param list
     * @return
     */
    List<ShoppingCart> shopCartListByIds(List<Long> list);

    /**
     * 根据促销分组查询购物车集合
     * @param list
     * @return
     */
    List<ShoppingCart> shopCartListGroupByMarkettingId(List<Long> list);

    /**
     * 查询购物车商品 根据List里的ID
     *
     * @param list
     * @return List
     */
    List<ShoppingCart> shoppingCartListByIds(List<Long> list);


    /**
     * 加入购物车
     * @param shoppingCart
     * @return int
     */
    int addShoppingCart(ShoppingCart shoppingCart);
    /**
     * 删除所购买商品
     * @param list
     * @return int
     */
    int deleteShoppingCartByIds(List<Long> list);
    /**
     * MINI
     * @param customerId
     * @return
     */
    List<ShoppingCart> shoppingCartMini(Long customerId);
    /**
     * 查询购物车总数
     * @param paramMap
     * @return Long
     */
    int shoppingCartCount(Map<String, Object> paramMap);

    /**
     * 查询是否已经加入购物车商品
     * @param shoppingCart
     * @return int
     */
    int selectCountByReady(ShoppingCart shoppingCart);
    /**
     * 修改购物车
     * @param shoppingCart
     * @return int
     */
    int updateShoppingCart(ShoppingCart shoppingCart);
    /**
     * 查询我已经加入购物车的数量
     * @param customerId
     * @return int
     */
    int selectSumByCustomerId(Long customerId);
    
    /**
     * 修改选中的订单优惠
     * @param shoppingCart 要修改的参数
     * @author NINGPAI-LIH
     */
    int changeShoppingCartOrderMarket(ShoppingCart shoppingCart);
    
    /**
     * 根据地区id查询省名称
     * @param districtId 地区id
     * @return 省名称和地区id
     */
    String selectPNameByParam(Long districtId);
    
    /**
     * 查询最后一次加入的购物车id
     * @param shoppingCart
     * @return
     */
    Long selectLastId(ShoppingCart shoppingCart);
    
    
    /**
     * 根据购物车参数查询购物车信息
     * @param shoppingCart 参数
     * @return
     */
    ShoppingCart selectShopingByParam(ShoppingCart shoppingCart);
}


