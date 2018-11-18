/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.coupon.bean.CouponNo;

/**
 * 优惠券卷码接口
 * 
 * @author ggn 2014-03-19
 * 
 */
public interface CouponNoMapper {

    /**
     * 创建优惠券卷码
     * 
     * @param list
     *            {@link java.util.List}
     * @return list
     */
    int createCouponNo(List<CouponNo> list);

    /**
     * 删除优惠券卷码
     * 
     * @param couponId
     * @return int
     */
    int delCouponNo(Long couponId);

    /**
     * 批量删除优惠券卷码
     * 
     * @param list
     * @return int
     */
    int delAllCouponNo(List<Long> list);

    /**
     * 查询所有卷吗
     * 
     * @param couponId
     * @return List
     */
    List<CouponNo> selectNoByCouponId(Long couponId);

    /**
     * 查询未使用的卷码
     * 
     * @param couponId
     * @return List
     */
    CouponNo selectNoByCouponIdByStatus(Long couponId);

    /**
     * 修改优惠券使用
     * 
     * @param cn
     * @return int
     */
    int updateCodeIsUse(CouponNo cn);

    /**
     * 修改优惠券为已经领取
     * 
     * @param cn
     * @return int
     */
    int updateNoIsGet(CouponNo cn);

    /**
     * 修改优惠劵劵码的使用状态
     */
    int modifyNoStatus(CouponNo no);

    /**
     * 赠送优惠劵
     * 
     * @param no
     *            赠送的优惠劵信息
     * @return
     */
    int giveCusCoupon(CouponNo no);

    /**
     * 分页查询数据
     * 
     * @param map
     *            分页数据
     * @return {@link list}
     */
    List<Object> selectNoListByCouponId(Map<String, Object> map);

    /**
     * 查询总数
     * 
     * @param map
     *            查询总数量
     * @return 劵码总数量
     */
    int selectNoCountByCouponId(Map<String, Object> map);

    /**
     * 修改领取状态
     * 
     * @param couponId
     * @return
     */
    int changeCouponGetAndStatus(Long codeId);

    /**
     * 获取领取优惠券数量
     * 
     * @param couponId
     * @return
     */
    int getCouponGetNoByCouponId(Long couponId);

    /**
     * 获取优惠券
     * 
     * @param param
     * @return
     */
    int updateCouponCustomer(Map<String, Object> param);

    /**
     * 查询获取会员卷张数
     * 
     * @param param
     * @return int
     */
    int selectReadyGet(Map<String, Object> param);

    /**
     * 返还优惠劵
     * 
     * @param couponNo
     * @return
     */
    int returnCouponNo(String codeNo);

    /**
     * 根据优惠券ID查询该优惠券被领取的张数
     * 
     * @param couponId
     * @return
     */
    Long queryUsedCountByCouponId(Long couponId);

    /**
     * 查询优惠券总数
     * 
     * @param couponId
     * @return
     */
    int selectCountAllByCouponId(Long couponId);

    /**
     * 根据优惠券id查询可用优惠券
     * 
     * @param couponId
     * @return
     */
    int selectCouponNoByStatus(Long couponId);

    /**
     * 新查询优惠券券码
     * 
     * @param map
     * @return
     */
    List<Object> newSelectCouponList(Map<String, Object> map);

}
