/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.ParamIds;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.system.bean.PointSet;
import com.ningpai.util.PageBean;

/**
 * @author ggn 优惠券接口 2014-03-19
 */
public interface CouponService {

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
     * ADD BY LY
     *查询所有优惠卷信息(根据type)
     * @return
     */
    List<Coupon> selectCouponListByType(Map<String ,Object> param);

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

    /***
     * 修改指定会员总积分
     * 
     * @param point
     * @return
     */
    int updateCustomerPoint(CustomerPoint point);

    /**
     * 获取积分消费规则
     * 
     * @return
     */
    PointSet selectPointSet();

    /***
     * 获取当前会员的所有积分
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
     * 
     * @return int
     */
    int doAddCoupon(Coupon coupon, HttpServletRequest request, Long[] lelvelId);

    /**
     * 添加优惠劵接口（按品牌和分类或者单个商品进行插入）
     * 
     * @param coupon
     * @param request
     * @param lelvelId
     * @param status
     *            0 货品 1品牌 2分类
     * @return
     */
    int doAddCoupon(Coupon coupon, HttpServletRequest request, Long[] lelvelId,
            String status);

    /**
     * 分页查询所有优惠券列表
     * 
     * @param coupon
     *            {@link com.ningpai.coupon.bean.Coupon}
     * @param pageBean
     * @return List
     */
    PageBean searchCouponList(Coupon coupon, PageBean pageBean,
            String startTime, String endTime);

    /**
     * 删除优惠券
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return int
     */
    int delCoupon(Long couponId);

    /**
     * 批量删除优惠券
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return int
     */
    int delAllCoupon(Long[] couponId);

    /**
     * 查询优惠券详细信息
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return Coupon
     */
    Coupon searchCouponById(Long couponId);

    /**
     * 修改优惠券
     * 
     * @param coupon
     *            {@link com.ningpai.coupon.bean.Coupon}
     * @param cateId
     *            {@link java.lang.Long}
     * @param brandId
     *            {@link java.lang.Long}
     * @param skuId
     *            {@link java.lang.Long}
     * @param status
     *            {@link java.lang.String}
     * @return int
     */
    int doUpdateCouponById(Coupon coupon, HttpServletRequest request,
            Long[] lelvelId, String status);

    /**
     * 查询范围
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @param type
     *            {@link java.lang.String}
     * @return Object
     */
    Object selectCouponRange(Long couponId, String type);

    /**
     * 我的优惠券
     * 
     * @param customerId
     *            {@link java.lang.Long}
     * @param codeStatus
     *            {@link java.lang.String} 1未使用 2已使用 3已经过期
     * @return PageBean
     */
    PageBean myCouponList(PageBean pageBean, Long customerId, String codeStatus);

    /**
     * 查询我可以使用 未过期的所有优惠券总数
     * 
     * @param customerId
     *            {@link java.lang.Long}
     * @return int
     */
    int myCouponNoCount(Long customerId);

    /**
     * * 查询货品可以使用的优惠券
     * 
     * @param list
     * @return List
     */
    List<Coupon> selectCouponListByIds(List<ParamIds> list,
            HttpServletRequest request);

    /**
     * * 查询货品可以使用的优惠券(输入使用优惠券时使用)
     *
     * @param list
     * @return List
     */
    List<Coupon> selectCouponListByIdsNew(List<ParamIds> list,HttpServletRequest request);

    /**
     * 根据卷码查询优惠券
     * 
     * @param codeNo
     * @return Coupon
     */
    Coupon selectCouponByCodeNo(String codeNo);

    /**
     * 根据卷码查询优惠券优惠价格
     * 2016-02-02 wuyanbo add
     * @param codeNo
     * @return Coupon
     */
    BigDecimal getCouponPriceByCodeNo(String codeNo);

    /**
     * 返回一个卷卷码 并且修改此优惠券为已经领取
     * 
     * @param couponNo
     * @return
     */
    Coupon selectOneCouponNoByCouponIdAndUpdateNoIsGet(Long couponNo,
            Long customerId);

    /**
     * 赠送优惠劵
     * 
     * @param couponNo
     *            劵码
     * @param customerId
     *            用户id
     * @return int
     */
    int giveCusCoupon(String couponNo, Long customerId);

    /**
     * 修改优惠劵状态
     * 
     * @param couponNo
     *            劵码
     * @param codeStatus
     *            要修改的优惠劵状态
     * @return
     */
    int modifyNoStatus(String couponNo, String codeStatus);

    /**
     * tianjia
     * 
     * @param couponId
     */
    void addCouponC(Long couponId);

    /**
     * 根据codeId获取优惠券时间
     * 
     * @param codeId
     * @return
     */
    Date selectCouponTimeByCodeId(Long codeId);

    /**
     * 查询优惠劵总数量
     * 
     * @return
     */
    int selectCouponCount();

    /**
     * 取消订单取消优惠劵
     * 
     * @param couponNo
     * @return
     */
    int returnCouponNo(String couponNo);

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
     * @param couponId
     *            {@link java.lang.Long}
     * @return int
     */
    int newdelCoupon(Long couponId, Long thirdId);

    /**
     * 批量删除优惠券
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return int
     */
    int delAllCoupon(Long[] couponId, Long thirdId);
    
    /**
     * 查询优惠券数量
     * @param customerId 用户id
     * @param status 优惠券状态
     * @return
     */
    Long countByCodeStatus(Long customerId, String status);
}
