/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.dao.CouponMapper;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.PointSet;

/**
 * 优惠券实现类
 * 
 * @author ggn 2014-03-19
 * 
 */
@Repository("CouponMapper")
public class CouponMapperImpl extends BasicSqlSupport implements CouponMapper {

    /*
     * 添加优惠券接口
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#doAddCoupon(com.ningpai.coupon.bean
     * .Coupon)
     */
    @Override
    public int doAddCoupon(Coupon coupon) {
        return this.insert("com.ningpai.web.dao.CouponMapper.doAddCoupon",
                coupon);
    }

    /*
     * 查询刚刚插入的优惠券ID
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#selectLastOne()
     */
    @Override
    public Long selectLastId() {
        return this.selectOne("com.ningpai.web.dao.CouponMapper.selectLastId");
    }

    /*
     * 查询优惠券列表总数
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#selectCouponListCount(java.util.Map)
     */
    @Override
    public int selectCouponListCount(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponMapper.selectCouponListCount",
                paramMap);
    }

    /*
     * 分页查询优惠券列表
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#selectCouponList(java.util.Map)
     */
    @Override
    public List<Object> selectCouponList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.CouponMapper.selectCouponList", paramMap);
    }

    /*
     * 删除优惠券
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#delCoupon(java.lang.Long)
     */
    @Override
    public int delCoupon(Long couponId) {
        return this.update("com.ningpai.web.dao.CouponMapper.delCoupon",
                couponId);
    }

    /*
     * 批量删除优惠券
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#delAllCoupon(java.util.List)
     */
    @Override
    public int delAllCoupon(List<Long> list) {
        return this.update("com.ningpai.web.dao.CouponMapper.delAllCoupon",
                list);
    }

    /*
     * 查询优惠券详细信息
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#searchCouponById(java.lang.Long)
     */
    @Override
    public Coupon searchCouponById(Long couponId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponMapper.searchCouponById", couponId);
    }

    /*
     * 修改优惠券
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#doUpdateCouponById(com.ningpai.coupon
     * .bean.Coupon)
     */
    @Override
    public int doUpdateCouponById(Coupon coupon) {
        return this.update(
                "com.ningpai.web.dao.CouponMapper.doUpdateCouponById", coupon);
    }

    /*
     * 根据List查询优惠券信息
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#selectCouponByListId(java.util.List)
     */
    @Override
    public List<Coupon> selectCouponByListId(List<Long> list) {
        return this.selectList(
                "com.ningpai.web.dao.CouponMapper.selectCouponByListId", list);
    }

    /*
     * 我的优惠券
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#myCouponList(java.util.Map)
     */
    @Override
    public List<Object> myCouponList(Map<String, Object> paramMap) {
        // 获取状态
        String codeStatus = (String) paramMap.get("codeStatus");
        List<Object> olist = null;
        // 判断状态
        // 1已经领取可使用 2已经使用
        if ("1".equals(codeStatus) || "2".equals(codeStatus)
                || "".equals(codeStatus)) {
            olist = this.selectList(
                    "com.ningpai.web.dao.CouponMapper.myCouponList", paramMap);
            // 3过期作废
        } else if ("3".equals(codeStatus)) {
            olist = this.selectList(
                    "com.ningpai.web.dao.CouponMapper.myCouponListThree",
                    paramMap);
        }
        return olist;
    }

    /*
     * 查询我的优惠券总数
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#myCouponListCount(java.util.Map)
     */
    @Override
    public int myCouponListCount(Map<String, Object> paramMap) {
        // 获取状态
        String codeStatus = (String) paramMap.get("codeStatus");
        int count = 0;
        // 判断状态
        // 1已经领取可使用 2已经使用
        if ("1".equals(codeStatus) || "2".equals(codeStatus)
                || "".equals(codeStatus)) {
            count = this.selectOne(
                    "com.ningpai.web.dao.CouponMapper.myCouponListCount",
                    paramMap);
            // 3过期作废
        } else if ("3".equals(codeStatus)) {
            count = this.selectOne(
                    "com.ningpai.web.dao.CouponMapper.myCouponListCountThree",
                    paramMap);
        }
        return count;
    }

    /*
     * 查询我的可使用的优惠券总数
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#myCouponNoCount(java.lang.Long)
     */
    @Override
    public int myCouponNoCount(Long customerId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponMapper.myCouponNoCount", customerId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#selectCouponListByIds(java.util.Map)
     */
    @Override
    public List<Coupon> selectCouponListByIds(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.CouponMapper.selectCouponListByIds",
                paramMap);
    }

    @Override
    public List<Coupon> selectCouponListByIdsNew(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.CouponMapper.selectCouponListByIdsNew",
                paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#selectCouponByCodeNo(java.lang.String
     * )
     */
    @Override
    public Coupon selectCouponByCodeNo(String codeNo) {
        // 优惠券bean
        Coupon coupon = new Coupon();
        // 设置券码
        coupon.setCodeNo(codeNo);
        return this
                .selectOne(
                        "com.ningpai.web.dao.CouponMapper.selectCouponByCodeNo",
                        coupon);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#selectOneCouponNoByCouponId(java.
     * lang.Long)
     */
    @Override
    public Coupon selectOneCouponNoByCouponId(Long couponId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponMapper.selectOneCouponNoByCouponId",
                couponId);
    }

    @Override
    public Date selectCouponTimeByCodeId(Long codeId) {
        return this.selectOne(
                "com.ningpai.web.dao.CouponMapper.selectCouponTimeByCodeId",
                codeId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.dao.CouponMapper#selectCustomerPointByCustomerId(java
     * .lang.Long)
     */
    @Override
    public CustomerPoint selectCustomerPointByCustomerId(Long customerId) {
        // 定义map
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置用户id
        map.put("customerId", customerId);
        return this
                .selectOne(
                        "com.ningpai.web.dao.CouponMapper.selectCustomerPointByCustomerId",
                        map);
    }

    @Override
    public PointSet selectPointSet() {
        return this
                .selectOne("com.ningpai.web.dao.CouponMapper.selectPointSet");
    }

    @Override
    public List<Object> queryCouponListToBoss() {
        return this
                .selectList("com.ningpai.web.dao.CouponMapper.queryCouponListToBoss");
    }

    @Override
    public List<Coupon> selectCouponList_site() {

        return selectList("com.ningpai.web.dao.CouponMapper.selectCouponList_site");
    }

    /**
     *查询所有优惠卷信息根据type
     * @return
     */
    @Override
    public List<Coupon> selectCouponListByType(Map<String,Object> param){
        return selectList("com.ningpai.web.dao.CouponMapper.selectCouponListByType" , param);
    }
    @Override
    public List<Coupon> selectCouponListBySite(Map<String,Object> param){
        return selectList("com.ningpai.web.dao.CouponMapper.selectCouponListBySite" , param);
    }

    @Override
    public List<Coupon> selectCouponListByStoreId(Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        return this.selectList(
                "com.ningpai.web.dao.CouponMapper.selectCouponListByStoreId",
                map);
    }

    @Override
    public int updateCustomerPoint(CustomerPoint point) {
        return this.update(
                "com.ningpai.web.dao.CouponMapper.updateCustomerPoint", point);
    }

    @Override
    public List<Object> queryCouponList() {
        return this
                .selectList("com.ningpai.web.dao.CouponMapper.queryCouponList");
    }

    /*
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#selectCouponListByAble()
     */
    @Override
    public List<Coupon> selectCouponListByAble() {
        return this
                .selectList("com.ningpai.web.dao.CouponMapper.selectCouponListByAble");

    }

    /*
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#newDelCoupon(java.util.Map)
     */
    @Override
    public int newDelCoupon(Map<String, Object> paramMap) {
        return this.update("com.ningpai.web.dao.CouponMapper.newdelCoupon",
                paramMap);
    }

    /*
     * 
     * @see com.ningpai.coupon.dao.CouponMapper#newDelAllCoupon(java.util.Map)
     */
    @Override
    public int newDelAllCoupon(Map<String, Object> paramMap) {
        return this.update("com.ningpai.web.dao.CouponMapper.newdelAllCoupon",
                paramMap);
    }

}
