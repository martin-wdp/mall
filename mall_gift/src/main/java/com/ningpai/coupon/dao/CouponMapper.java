/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.system.bean.PointSet;

/**
 * 优惠券接口
 * 
 * @author ggn 2014-03-19
 * 
 */
public interface CouponMapper {
    /**
     * 获取boss优惠券信息
     *
     * @return
     */
    List<Object> queryCouponListToBoss();

    /**
     *查询所有优惠卷信息
     * @return
     */
    List<Coupon> selectCouponList_site();

    /**
     *查询所有优惠卷信息根据type
     * @return
     */
    List<Coupon> selectCouponListByType(Map<String,Object> param);

    /**
     * add by 付陈林
     * 查询所有有效的优惠劵信息(根据type)
     * @return
     * 1、未删除的，
     * 2、活动开始时间大于当前时间的，即已开始
     * 3、活动结束时间小于当前时间的，即未结束
     * 4、优惠劵不在注册营销中的优惠劵。
     * */
    List<Coupon> selectCouponListBySite(Map<String,Object> param);

    /**
     * 根据店铺ID查询优惠券
     * 
     * @return List对象
     */
    List<Coupon> selectCouponListByStoreId(Long storeId);

    /**
     * 修改指定会员总积分
     * 
     * @param point
     * @return
     */
    int updateCustomerPoint(CustomerPoint point);

    /***
     * 获取积分消费规则
     * 
     * @return
     */
    PointSet selectPointSet();

    /***
     * 获取当前会员的总积分
     * 
     * @param customerId
     * @return
     */
    CustomerPoint selectCustomerPointByCustomerId(Long customerId);

    /**
     * 添加优惠券接口
     * 
     * @param coupon
     *            {@link com.ningpai.coupon.bean.Coupon}
     * @return int
     */
    int doAddCoupon(Coupon coupon);

    /**
     * 查询刚刚插入的优惠券ID
     * 
     * @return list
     */
    Long selectLastId();

    /**
     * 查询优惠券列表总数
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return int
     */
    int selectCouponListCount(Map<String, Object> paramMap);

    /**
     * 分页查询优惠券列表
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return List
     */
    List<Object> selectCouponList(Map<String, Object> paramMap);

    /**
     * 删除优惠券
     * 
     * @param couponId
     * @return int
     */
    int delCoupon(Long couponId);

    /**
     * 批量删除优惠券
     * 
     * @param list
     * @return int
     */
    int delAllCoupon(List<Long> list);

    /**
     * 查询优惠券详细信息
     * 
     * @param couponId
     * @return Coupon
     */
    Coupon searchCouponById(Long couponId);

    /**
     * 修改优惠券
     * 
     * @param coupon
     * @return upflag
     */
    int doUpdateCouponById(Coupon coupon);

    /**
     * 根据List查询优惠券信息
     * 
     * @param list
     * @return List
     */
    List<Coupon> selectCouponByListId(List<Long> list);

    /**
     * 我的优惠券
     * 
     * @param paramMap
     * @return List
     */
    List<Object> myCouponList(Map<String, Object> paramMap);

    /**
     * 查询我的优惠券总数
     * 
     * @param paramMap
     * @return int
     */
    int myCouponListCount(Map<String, Object> paramMap);

    /**
     * 查询我的可使用的优惠券总数
     * 
     * @param customerId
     * @return int
     */
    int myCouponNoCount(Long customerId);

    /**
     * 查询
     * 
     * @param paramMap
     * @return List
     */
    List<Coupon> selectCouponListByIds(Map<String, Object> paramMap);

    /**
     * 查询优惠券新（输入使用优惠券时）
     *
     * @param paramMap
     * @return List
     */
    List<Coupon> selectCouponListByIdsNew(Map<String, Object> paramMap);

    /**
     * 根据卷吗查询优惠券信息
     * 
     * @param codeNo
     * @return Coupon
     */
    Coupon selectCouponByCodeNo(String codeNo);

    /**
     * 根据优惠券ID查询一个卷码
     * 
     * @param couponId
     * @return Coupon
     */
    Coupon selectOneCouponNoByCouponId(Long couponId);

    /**
     * 根据codeId获取优惠券时间
     * 
     * @param codeId
     * @return
     */
    Date selectCouponTimeByCodeId(Long codeId);

    /**
     * 获取优惠券信息
     * 
     * @return
     */
    List<Object> queryCouponList();

    /**
     * 查询可使用的优惠券
     * 
     * @return List对象
     */
    List<Coupon> selectCouponListByAble();

    /**
     * 删除优惠券
     * 
     * @param paramMap
     *            删除条件
     * @return int
     */
    int newDelCoupon(Map<String, Object> paramMap);

    /**
     * 批量删除优惠券
     * 
     * @param paramMap
     *            删除条件
     * @return int
     */
    int newDelAllCoupon(Map<String, Object> paramMap);
}
