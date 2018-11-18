/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.shoppingcart.dao.impl;

import java.util.List;
import java.util.Map;

import com.ningpai.m.shoppingcart.bean.StoreTemp;
import org.springframework.stereotype.Repository;

import com.ningpai.m.shoppingcart.bean.ShoppingCart;
import com.ningpai.m.shoppingcart.dao.ShoppingCartMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @author ggn
 * 
 */
@Repository("ShoppingCartMapper")
public class ShoppingCartMapperImpl extends BasicSqlSupport implements
        ShoppingCartMapper {

    /**
     * 查询我的购物车
     * @param  {@link java.lang.Long}
     * @return List
     */
    public List<Object> shoppingCart(Map<String, Object> paramMap) {
        return this.selectList(
                        "com.ningpai.web.dao.ShoppingCartMapper.shoppingCart",
                        paramMap);
    }

    /**
     * 查询购物车总数
     * @param paramMap
     * @return Long
     */
    public int shoppingCartCount(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.shoppingCartCount",
                paramMap);
    }

    /**
     * MINI
     * @param customerId
     * @return
     */
    @Override
    public List<ShoppingCart> shoppingCartMini(Long customerId) {
        return this.selectList(
                "com.ningpai.web.dao.ShoppingCartMapper.shoppingCartMini",
                customerId);
    }

    /**
     * 删除
     * @param shoppingCartId
     * @return int
     */
    @Override
    public int delShoppingCartById(Long shoppingCartId) {
        return this.update(
                "com.ningpai.web.dao.ShoppingCartMapper.delShoppingCartById",
                shoppingCartId);
    }

    /**
     * 修改购买数量
     * @param sc
     * @return int
     */
    @Override
    public int changeShoppingCartById(ShoppingCart sc) {
        return this.update("com.ningpai.web.dao.ShoppingCartMapper.changeShoppingCartById",
                        sc);
    }

    /**
     * 修改优惠信息
     * @param sc
     * @return int
     */
    @Override
    public int changeShoppingCartMarket(ShoppingCart sc) {
        return this.update("com.ningpai.web.dao.ShoppingCartMapper.changeShoppingCartMarket",
                        sc);
    }
    /**
     * 根据购物车查询第三方id和名称
     * @param shopcartIds
     * @return
     */
    @Override
    public List<StoreTemp> selectStoreTempByshopcartIds(List<Long> shopcartIds) {
        return this.selectList("com.ningpai.web.dao.ShoppingCartMapper.selectStoreTempByshopcartIds", shopcartIds);
    }
    /**
     * 查询购物车的详细信息
     * @param list
     * @return
     */
    @Override
    public List<ShoppingCart> shopCartListByIds(List<Long> list) {

        return this.selectList("com.ningpai.web.dao.ShoppingCartMapper.shopCartListByIds", list);
    }
    /**
     * 根据促销分组查询购物车集合
     * @param list
     * @return
     */
    @Override
    public List<ShoppingCart> shopCartListGroupByMarkettingId(List<Long> list) {
        return this.selectList(
                "com.ningpai.web.dao.ShoppingCartMapper.shopCartListGroupByMarkettingId",
                list);
    }
    /**
     * 查询购物车商品 根据List里的ID
     *
     * @param list
     * @return List
     */
    @Override
    public List<ShoppingCart> shoppingCartListByIds(List<Long> list) {
        return this.selectList(
                "com.ningpai.web.dao.ShoppingCartMapper.shoppingCartListByIds",
                list);
    }


    /**
     * 加入购物车
     * @param shoppingCart
     * @return int
     */
    @Override
    public int addShoppingCart(ShoppingCart shoppingCart) {
        return this.insert(
                "com.ningpai.web.dao.ShoppingCartMapper.addShoppingCart",
                shoppingCart);
    }

    /**
     * 删除所购买商品
     * @param list
     * @return int
     */
    @Override
    public int deleteShoppingCartByIds(List<Long> list) {
        return this.update("com.ningpai.web.dao.ShoppingCartMapper.deleteShoppingCartByIds",
                        list);
    }

    /**
     * 查询是否已经加入购物车商品
     * @param shoppingCart
     * @return int
     */
    @Override
    public int selectCountByReady(ShoppingCart shoppingCart) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectCountByReady",
                shoppingCart);
    }

    /**
     * 修改购物车
     * @param shoppingCart
     * @return int
     */
    @Override
    public int updateShoppingCart(ShoppingCart shoppingCart) {
        return this.update(
                "com.ningpai.web.dao.ShoppingCartMapper.updateShoppingCart",
                shoppingCart);
    }

    /**
     * 查询我已经加入购物车的数量
     * @param customerId
     * @return int
     */
    @Override
    public int selectSumByCustomerId(Long customerId) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectSumByCustomerId",
                customerId);
    }

    /**
     * 修改选中的订单优惠
     * @param shoppingCart 要修改的参数
     * @author NINGPAI-LIH
     */
    @Override
    public int changeShoppingCartOrderMarket(ShoppingCart shoppingCart) {
        return this.update("com.ningpai.web.dao.ShoppingCartMapper.changeShoppingCartOrderMarket",
                        shoppingCart);
    }

    /**
     * 根据地区id查询省名称
     * @param districtId 地区id
     * @return 省名称和地区id
     */
    @Override
    public String selectPNameByParam(Long districtId) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectPNameByParam",
                districtId);
    }

    /**
     * 查询最后一次加入的购物车id
     * @param shoppingCart
     * @return
     */
    @Override
    public Long selectLastId(ShoppingCart shoppingCart) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectLastId",shoppingCart);
    }

    /**
     * 根据购物车参数查询购物车信息
     * @param shoppingCart 参数
     * @return
     */
    @Override
    public ShoppingCart selectShopingByParam(ShoppingCart shoppingCart) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectShopingByParam",shoppingCart);
    }

}
