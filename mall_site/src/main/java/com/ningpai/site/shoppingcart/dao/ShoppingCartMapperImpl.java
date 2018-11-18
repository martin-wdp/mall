/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.shoppingcart.dao;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.shoppingcart.bean.ShoppingCart;
import com.ningpai.site.shoppingcart.bean.StoreTemp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ggn
 * 
 */
@Repository("ShoppingCartMapper")
public class ShoppingCartMapperImpl extends BasicSqlSupport implements
        ShoppingCartMapper {

    /**
     * 查询我的购物车
     * @param paramMap
     * @return
     */
    public List<Object> shoppingCart(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.web.dao.ShoppingCartMapper.shoppingCart",
                        paramMap);
    }

    /**
     * 根据模板id分组查询得到分组商品重量,以及数量
     * @param list
     * @return
     */
    @Override
    public List<ShoppingCart> shopCartListOrderByFreightIds(List<Long> list) {
        return this
                .selectList(
                        "com.ningpai.web.dao.ShoppingCartMapper.shopCartListOrderByFreightIds",
                        list);
    }

    /**
     * 根据购物车id促销id分组查询
     * @param list
     * @return
     */
    @Override
    public List<ShoppingCart> shopCartListOrderByMarkettingId(List<Long> list) {
        return this
                .selectList(
                        "com.ningpai.web.dao.ShoppingCartMapper.shopCartListOrderByMarkettingId",
                        list);
    }

    /**
     * 查询购物车总数
     * @param paramMap
     * @return
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
     * @param paramMap
     * @return
     */
    @Override
    public int delShoppingCartById(Map<String, Object> paramMap) {
        return this.update(
                "com.ningpai.web.dao.ShoppingCartMapper.delShoppingCartById",
                paramMap);
    }

    /**
     * 修改购买数量
     * @param sc
     * @return
     */
    @Override
    public int changeShoppingCartById(ShoppingCart sc) {
        return this
                .update("com.ningpai.web.dao.ShoppingCartMapper.changeShoppingCartById",
                        sc);
    }

    /**
     * 修改优惠信息
     * @param sc
     * @return
     */
    @Override
    public int changeShoppingCartMarket(ShoppingCart sc) {
        return this
                .update("com.ningpai.web.dao.ShoppingCartMapper.changeShoppingCartMarket",
                        sc);
    }

    /**
     * 查询购物车商品 根据List里的ID
     * @param list
     * @return
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
     * @return
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
     * @return
     */
    @Override
    public int deleteShoppingCartByIds(List<Long> list) {
        return this
                .update("com.ningpai.web.dao.ShoppingCartMapper.deleteShoppingCartByIds",
                        list);
    }

    /**
     * 查询是否已经加入购物车商品
     * @param shoppingCart
     * @return
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
     * @return
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
     * @return
     */
    @Override
    public int selectSumByCustomerId(Long customerId) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectSumByCustomerId",
                customerId);
    }

    /**
     * 修改选中的订单优惠
     * @param shoppingCart
     *            要修改的参数
     * @return
     */
    @Override
    public int changeShoppingCartOrderMarket(ShoppingCart shoppingCart) {
        return this
                .update("com.ningpai.web.dao.ShoppingCartMapper.changeShoppingCartOrderMarket",
                        shoppingCart);
    }

    /**
     * 根据地区id查询省名称
     * @param districtId
     *            地区id
     * @return
     */
    @Override
    public String selectPNameByParam(Long districtId) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectPNameByParam",
                districtId);
    }

    /**
     * 查询加入的购物车id
     * @param shoppingCart
     * @return
     */
    @Override
    public Long selectLastId(ShoppingCart shoppingCart) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectLastId",
                shoppingCart);
    }

    /**
     * 根据商品id和客户id查询购物车内容
     * @param shoppingCart
     * @return
     */
    @Override
    public ShoppingCart selectShopingByParam(ShoppingCart shoppingCart) {
        return this.selectOne(
                "com.ningpai.web.dao.ShoppingCartMapper.selectShopingByParam",
                shoppingCart);
    }

    /**
     * 查询购物车
     * @param list
     * @return
     */
    @Override
    public List<ShoppingCart> shopCartListByIds(List<Long> list) {

        return this.selectList(
                "com.ningpai.web.dao.ShoppingCartMapper.shopCartListByIds",
                list);
    }

    /**
     * 查询购物车里信息
     * @param customerId
     * @return
     */
    @Override
    public List<ShoppingCart> selectShoppingCartListByCustomerId(Long customerId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.ShoppingCartMapper.selectShoppingCartListByCustomerId",
                        customerId);
    }

    /**
     * 查询购物车里的商家
     * @param customerId
     * @return
     */
    @Override
    public List<StoreTemp> selectStoreTemp(Long customerId) {
        return this.selectList(
                "com.ningpai.web.dao.ShoppingCartMapper.selectStoreTemp",
                customerId);
    }

    /**
     * 查询购物车里的商家
     * @param shopcartIds
     * @return
     */
    @Override
    public List<StoreTemp> selectStoreTempByshopcartIds(List<Long> shopcartIds) {
        return this
                .selectList(
                        "com.ningpai.web.dao.ShoppingCartMapper.selectStoreTempByshopcartIds",
                        shopcartIds);
    }

    /**
     * 根据用户id修改购车仓库
     *
     * @param map
     */
    @Override
    public int updShoppingCartHouseByCstId(Map<String, Object> map) {
        return this.update("com.ningpai.web.dao.ShoppingCartMapper.updShoppingCartHouseByCstId",map);
    }
}
