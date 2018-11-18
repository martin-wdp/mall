/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.dao.CouponNoMapper;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * @author ggn 优惠券券码接口实现类 2014-03-19
 */
@Service("CouponNoService")
public class CouponNoServiceImpl implements CouponNoService {

    private static final String COUPONID = "couponId";

    private CouponNoMapper couponNoMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#selectNoByCouponId(java.lang
     * .Long)
     */
    @Override
    public List<CouponNo> selectNoByCouponId(Long couponId) {
        return couponNoMapper.selectNoByCouponId(couponId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#updateCodeIsUse(java.lang.
     * String, java.lang.Long)
     */
    @Override
    public int updateCodeIsUse(String codeNo, String orderCode) {
        // 创建bean
        CouponNo cn = new CouponNo();
        // 设置修改参数
        cn.setCodeGetTime(new Date());
        cn.setCodeUseOrderId(orderCode);
        cn.setCodeNo(codeNo);
        cn.setCodeGetTime(new Date());
        return couponNoMapper.updateCodeIsUse(cn);
    }

    public CouponNoMapper getCouponNoMapper() {
        return couponNoMapper;
    }

    @Resource(name = "CouponNoMapper")
    public void setCouponNoMapper(CouponNoMapper couponNoMapper) {
        this.couponNoMapper = couponNoMapper;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#selectNoByCouponIdByStatus(java
     * .lang.Long)
     */
    @Override
    public CouponNo selectNoByCouponIdByStatus(Long couponNo) {
        return couponNoMapper.selectNoByCouponIdByStatus(couponNo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#selectList(com.ningpai.util
     * .PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectList(PageBean pb, Long couponId, CouponNo couponNo) {
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(couponNo);
        // 设置劵码id
        paramMap.put(COUPONID, couponId);
        paramMap.put("start", pb.getStartRowNum());
        paramMap.put("end", pb.getEndRowNum());
        // 查询总数
        pb.setRows(couponNoMapper.selectNoCountByCouponId(paramMap));

        // 查询条件封装
        paramMap.put("start", pb.getStartRowNum());
        paramMap.put("number", pb.getEndRowNum());
        try {
            // 查询列表页
            pb.setList(couponNoMapper.selectNoListByCouponId(paramMap));
        } finally {
            paramMap = null;
        }
        return pb;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#changeCouponGetAndStatus(java
     * .lang.Long)
     */
    @Override
    public int changeCouponGetAndStatus(Long codeId) {
        return couponNoMapper.changeCouponGetAndStatus(codeId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#getCouponGetNoByCouponId(java
     * .lang.Long)
     */
    @Override
    public int getCouponGetNoByCouponId(Long couponId) {
        return couponNoMapper.getCouponGetNoByCouponId(couponId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#updateCouponCustomer(java.
     * lang.Long, java.lang.Long)
     */
    @Override
    public int updateCouponCustomer(Long codeId, Long cId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("codeId", codeId);
        param.put("customerId", cId);
        return couponNoMapper.updateCouponCustomer(param);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#selectReadyGet(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public int selectReadyGet(Long couponId, Long cId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(COUPONID, couponId);
        param.put("customerId", cId);
        return couponNoMapper.selectReadyGet(param);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#queryUsedCountByCouponId(java
     * .lang.Long)
     */
    @Override
    public Long queryUsedCountByCouponId(Long couponId) {

        return couponNoMapper.queryUsedCountByCouponId(couponId);
    }

    @Override
    public int selectCountAllByCouponId(Long couponId) {
        return couponNoMapper.selectCountAllByCouponId(couponId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#selectCouponNoByStatus(java
     * .lang.Long)
     */
    @Override
    public int selectCouponNoByStatus(Long couponId) {
        return couponNoMapper.selectCouponNoByStatus(couponId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponNoService#selectCouponList(java.lang
     * .Long, com.ningpai.coupon.bean.CouponNo)
     */
    @Override
    public List<Object> selectCouponList(Long couponId, CouponNo couponNo) {
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(couponNo);
        // 设置劵码id
        paramMap.put(COUPONID, couponId);
        return couponNoMapper.newSelectCouponList(paramMap);
    }
}
