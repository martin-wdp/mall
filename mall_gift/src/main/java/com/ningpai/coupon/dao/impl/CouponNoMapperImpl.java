/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.dao.CouponNoMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 优惠券卷码实现类
 * 
 * @author ggn 2014-03-19
 * 
 */
@Repository("CouponNoMapper")
public class CouponNoMapperImpl extends BasicSqlSupport implements
        CouponNoMapper {

    /*
     * 创建优惠券卷码
     * 
     * @see com.ningpai.coupon.dao.CouponNoMapper#createCouponNo(java.util.List)
     */
    @Override
    public int createCouponNo(List<CouponNo> list) {
        return this.insert("com.ningpai.web.dao.CouponNoMapper.createCouponNo",
                list);
    }

    /*
     * 删除优惠券卷码
     * 
     * @see com.ningpai.coupon.dao.CouponNoMapper#delCouponNo(java.lang.Long)
     */
    @Override
    public int delCouponNo(Long couponId) {
        return this.update("com.ningpai.web.dao.CouponNoMapper.delCouponNo",
                couponId);
    }

    /*
     * 批量删除优惠券卷码
     * 
     * @see com.ningpai.coupon.dao.CouponNoMapper#delAllCouponNo(java.util.List)
     */
    @Override
    public int delAllCouponNo(List<Long> list) {
        return this.update("com.ningpai.web.dao.CouponNoMapper.delAllCouponNo",
                list);
    }

    /*
     * 查询所有卷吗
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#selectNoByCouponId(java.lang.Long)
     */
    @Override
    public List<CouponNo> selectNoByCouponId(Long couponId) {
        return this.selectList(
                "com.ningpai.web.dao.CouponNoMapper.selectNoByCouponId",
                couponId);
    }

    /*
     * 修改优惠券使用
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#updateCodeIsUse(com.ningpai.coupon
     * .bean.CouponNo)
     */
    @Override
    public int updateCodeIsUse(CouponNo cn) {
        return this.update(
                "com.ningpai.web.dao.CouponNoMapper.updateCodeIsUse", cn);
    }

    /*
     * 修改优惠券为已经领取
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#updateNoIsGet(com.ningpai.coupon
     * .bean.CouponNo)
     */
    @Override
    public int updateNoIsGet(CouponNo cn) {
        return this.update("com.ningpai.web.dao.CouponNoMapper.updateNoIsGet",
                cn);
    }

    /*
     * 查询未使用的卷码
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#selectNoByCouponIdByStatus(java
     * .lang.Long)
     */
    @Override
    public CouponNo selectNoByCouponIdByStatus(Long couponId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.CouponNoMapper.selectNoByCouponIdByStatus",
                        couponId);
    }

    /*
     * 修改优惠劵劵码的使用状态
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#modifyNoStatus(com.ningpai.coupon
     * .bean.CouponNo)
     */
    @Override
    public int modifyNoStatus(CouponNo no) {
        return this.update("com.ningpai.web.dao.CouponNoMapper.modifyNoStatus",
                no);
    }

    /*
     * 赠送优惠劵
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#giveCusCoupon(com.ningpai.coupon
     * .bean.CouponNo)
     */
    @Override
    public int giveCusCoupon(CouponNo no) {
        return this.update("com.ningpai.web.dao.CouponNoMapper.giveCusCoupon",
                no);
    }

    /*
     * 分页查询数据
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#selectNoListByCouponId(java.util
     * .Map)
     */
    @Override
    public List<Object> selectNoListByCouponId(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.CouponNoMapper.selectNoListByCouponId",
                map);
    }

    /*
     * 查询总数
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#selectNoCountByCouponId(java.util
     * .Map)
     */
    @Override
    public int selectNoCountByCouponId(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponNoMapper.selectNoCountByCouponId",
                map);
    }

    /*
     * 修改领取状态
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#changeCouponGetAndStatus(java.lang
     * .Long)
     */
    @Override
    public int changeCouponGetAndStatus(Long codeId) {
        return this.update(
                "com.ningpai.web.dao.CouponNoMapper.changeCouponGetAndStatus",
                codeId);
    }

    /*
     * 获取领取优惠券数量
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#getCouponGetNoByCouponId(java.lang
     * .Long)
     */
    @Override
    public int getCouponGetNoByCouponId(Long couponId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponNoMapper.getCouponGetNoByCouponId",
                couponId);
    }

    /*
     * 获取优惠券
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#updateCouponCustomer(java.util.Map)
     */
    @Override
    public int updateCouponCustomer(Map<String, Object> param) {
        return this.update(
                "com.ningpai.web.dao.CouponNoMapper.updateCouponCustomer",
                param);
    }

    /*
     * 查询获取会员卷张数
     * 
     * @see com.ningpai.coupon.dao.CouponNoMapper#selectReadyGet(java.util.Map)
     */
    @Override
    public int selectReadyGet(Map<String, Object> param) {

        return this.selectOne(
                "com.ningpai.web.dao.CouponNoMapper.selectReadyGet", param);
    }

    /*
     * 返还优惠劵
     * 
     * @see com.ningpai.coupon.dao.CouponNoMapper#returnCouponNo(java.lang.Long)
     */
    @Override
    public int returnCouponNo(String codeNo) {
        return this.update("com.ningpai.web.dao.CouponNoMapper.backCouponNo",
                codeNo);
    }

    /*
     * 根据优惠券ID查询该优惠券被领取的张数
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#queryUsedCountByCouponId(java.lang
     * .Long)
     */
    @Override
    public Long queryUsedCountByCouponId(Long couponId) {

        return this.selectOne(
                "com.ningpai.web.dao.CouponNoMapper.queryUsedCountByCouponId",
                couponId);
    }

    @Override
    public int selectCountAllByCouponId(Long couponId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponNoMapper.selectCountAllByCouponId",
                couponId);
    }

    @Override
    public int selectCouponNoByStatus(Long couponId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponNoMapper.selectCouponNoByStatus",
                couponId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.dao.CouponNoMapper#newSelectCouponList(java.util.Map)
     */
    @Override
    public List<Object> newSelectCouponList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.CouponNoMapper.newselectNoListByCouponId",
                map);
    }

}
