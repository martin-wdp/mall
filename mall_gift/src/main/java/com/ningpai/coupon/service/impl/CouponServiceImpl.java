/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service.impl;

import com.ningpai.common.util.DateUtil;
import com.ningpai.coupon.bean.*;
import com.ningpai.coupon.dao.*;
import com.ningpai.coupon.service.CouponLelevlService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.system.bean.PointSet;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.RanddomMath;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author ggn 优惠券接口实现类 2014-03-19
 */
@Service("CouponService")
public class CouponServiceImpl implements CouponService {

    private static final String DOWNPRICE = "downPrice";
    private static final String FULLPRICE = "fullPrice";
    private static final String REDUCTIONPRICE = "reductionPrice";
    private static final String CODESTATUS = "codeStatus";
    private static final String CUSTOMERID = "customerId";

    // 优惠券
    private CouponMapper couponMapper;
    // 优惠券券码
    private CouponNoMapper couponNoMapper;
    // 优惠券范围
    private CouponRangeMapper couponRangeMapper;

    private GoodsCateMapper goodsCateMapper;

    private GoodsBrandMapper goodsBrandMapper;

    private GoodsMapper goodsMapper;

    private CouponFullReductionMapper couponFullReductionMapper;

    private CouponStraightDownMapper couponStraightDownMapper;

    private CouponLelevlService couponLelevlService;

    private GoodsProductService goodsProductService;

    @Override
    public List<Object> queryCouponListToBoss() {
        return couponMapper.queryCouponListToBoss();
    }

    @Override
    public List<Coupon> selectCouponList_site() {
        // 获取所有的优惠卷信息
        List<Coupon> couponLists = couponMapper.selectCouponList_site();
        for (int i = 0; i < couponLists.size(); i++) {
            Coupon coupons = couponLists.get(i);
            // 直降
            if ("1".equals(coupons.getCouponRulesType())) {
                // 装载对应的直降信息
                coupons.setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(coupons.getCouponId()));
            }
        }
        return couponLists;
    }
    @Override
    public List<Coupon> selectCouponListByType(Map<String ,Object> param) {
        // 获取所有的优惠卷信息
        List<Coupon> couponLists = couponMapper.selectCouponListByType(param);
        for (int i = 0; i < couponLists.size(); i++) {
            Coupon coupons = couponLists.get(i);
            // 直降
            if ("1".equals(param.get("couponRulesType"))) {
                // 装载对应的直降信息
                coupons.setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(coupons.getCouponId()));
            }
            //ADD BY LY 增加满减信息
            if ("2".equals(param.get("couponRulesType"))) {
                // 装载对应的满减信息
                coupons.setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(coupons.getCouponId()));
            }
        }
        return couponLists;
    }
    @Override
    public List<Coupon> selectCouponListBySite(Map<String,Object> param){
        // 获取所有的优惠卷信息
        List<Coupon> couponLists = couponMapper.selectCouponListBySite(param);
        for (int i = 0; i < couponLists.size(); i++) {
            Coupon coupons = couponLists.get(i);
            if(param.get("couponRulesType")!=null) {
                // 直降
                if ("1".equals(param.get("couponRulesType"))) {
                    // 装载对应的直降信息
                    coupons.setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(coupons.getCouponId()));
                }
                //ADD BY LY 增加满减信息
                if ("2".equals(param.get("couponRulesType"))) {
                    // 装载对应的满减信息
                    coupons.setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(coupons.getCouponId()));
                }
            }else {
                if("1".equals(coupons.getCouponRulesType())){
                    coupons.setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(coupons.getCouponId()));
                }
                if("2".equals(coupons.getCouponRulesType())){
                    coupons.setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(coupons.getCouponId()));
                }
            }
        }
        return couponLists;
    }
    @Override
    public List<Coupon> selectCouponListByStoreId(Long storeId) {
        return couponMapper.selectCouponListByStoreId(storeId);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#updateCustomerPoint(com.ningpai
     * .customer.bean.CustomerPoint)
     */
    @Override
    public int updateCustomerPoint(CustomerPoint point) {
        return this.couponMapper.updateCustomerPoint(point);
    }

    /*
     * 
     * @see com.ningpai.coupon.service.CouponService#selectPointSet()
     */
    @Override
    public PointSet selectPointSet() {
        return this.couponMapper.selectPointSet();
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#selectCustomerPointByCustomerId
     * (java.lang.Long)
     */
    @Override
    public CustomerPoint selectCustomerPointByCustomerId(Long customerId) {
        return this.couponMapper.selectCustomerPointByCustomerId(customerId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#doAddCoupon(com.ningpai.coupon
     * .bean.Coupon)
     */
    @Override
    @Transactional
    public int doAddCoupon(Coupon coupon, HttpServletRequest request, Long[] lelvelId) {
        // 创建时间
        coupon.setCreateTime(new Date());
        // 修改时间
        coupon.setModifyTime(new Date());
        // 删除标记 0正常 1删除
        coupon.setDelFlag("0");
        // 创建优惠券
        int f = couponMapper.doAddCoupon(coupon);
        // 如果创建成功
        if (f > 0) {
            // 查询刚刚创建的ID
            Long couponId = couponMapper.selectLastId();
            // 创建优惠券等级范围
            List<CouponLelvel> clist = new ArrayList<CouponLelvel>();
            for (int i = 0; i < lelvelId.length; i++) {
                CouponLelvel level = new CouponLelvel();
                level.setCouponId(couponId);
                level.setLelvelId(lelvelId[i]);
                level.setLelvelDelFlag("0");
                clist.add(level);
            }
            // 判断是否为空
            if (clist != null && !clist.isEmpty()) {
                couponLelevlService.addCouponLelvel(clist);
            }
            // 判断状态
            if ("1".equals(coupon.getCouponRulesType())) {
                // 设置参数
                CouponStraightDown straightDown = new CouponStraightDown();
                straightDown.setCouponId(couponId);
                straightDown.setUpdateTime(new Date());
                straightDown.setDownPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(DOWNPRICE))));
                straightDown.setDelFlag("0");
                // 插入
                couponStraightDownMapper.insertStraightDown(straightDown);
            }
            // 判断状态
            if ("2".equals(coupon.getCouponRulesType())) {
                // 设置参数
                CouponFullReduction fullReduction = new CouponFullReduction();
                fullReduction.setCouponId(couponId);
                fullReduction.setUpdateTime(new Date());
                fullReduction.setFullPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(FULLPRICE))));
                fullReduction.setReductionPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCTIONPRICE))));
                fullReduction.setDelFlag("0");
                // 插入
                couponFullReductionMapper.insertFullDuction(fullReduction);
            }

            List<CouponNo> list = new ArrayList<CouponNo>();
            if (coupon.getCouponCount() != null && coupon.getCouponCount() > 0) {
                for (int i = 0; i < coupon.getCouponCount(); i++) {
                    // 封装卷码
                    CouponNo cn = new CouponNo();
                    cn.setCodeStatus("0");
                    cn.setCodeNo(RanddomMath.randomString(32));
                    cn.setCouponId(couponId);
                    list.add(cn);
                }
                // 创建优惠券券码
                couponNoMapper.createCouponNo(list);
            }

            String[] goodsId = request.getParameterValues("goodsId");
            List<CouponRange> mslist = new ArrayList<CouponRange>();
            if (goodsId != null && goodsId.length != 0) {
                // 循环添加
                for (int i = 0; i < goodsId.length; i++) {
                    CouponRange cr = new CouponRange();
                    cr.setCouponId(couponId);
                    cr.setCouponRangeFkId(Long.valueOf(goodsId[i]));
                    cr.setCouponRangeType("2");
                    cr.setDelFlag("0");
                    mslist.add(cr);
                }
            }

            if (!mslist.isEmpty()) {
                // 批量增加优惠券范围
                couponRangeMapper.createCouponRange(mslist);
            }

        }
        return f;
    }

    /**
     * 添加
     */
    public void addCouponC(Long couponId) {
        List<CouponNo> list = new ArrayList<CouponNo>();
        for (int i = 0; i < 100; i++) {
            // 封装卷码
            CouponNo cn = new CouponNo();
            cn.setCodeStatus("0");
            cn.setCodeNo(RanddomMath.randomString(32));
            cn.setCouponId(couponId);
            list.add(cn);
        }
        // 创建优惠券券码
        couponNoMapper.createCouponNo(list);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#doAddCoupon(com.ningpai.coupon
     * .bean.Coupon)
     */
    @Override
    @Transactional
    public int doAddCoupon(Coupon coupon, HttpServletRequest request, Long[] lelvelId, String status) {
        // 创建时间
        coupon.setCreateTime(new Date());
        // 修改时间
        coupon.setModifyTime(new Date());
        // 删除标记 0正常 1删除
        coupon.setDelFlag("0");
        // 创建优惠券
        int f = couponMapper.doAddCoupon(coupon);
        // 如果创建成功
        if (f > 0) {
            // 查询刚刚创建的ID
            Long couponId = couponMapper.selectLastId();
            // 创建优惠券等级范围
            List<CouponLelvel> clist = new ArrayList<CouponLelvel>();
            // 判断不为空
            if (null != lelvelId) {
                for (int i = 0; i < lelvelId.length; i++) {
                    CouponLelvel level = new CouponLelvel();
                    level.setCouponId(couponId);
                    level.setLelvelId(lelvelId[i]);
                    level.setLelvelDelFlag("0");
                    clist.add(level);
                }
            }
            if (clist != null && !clist.isEmpty()) {
                couponLelevlService.addCouponLelvel(clist);
            }
            // 判断状态
            if ("1".equals(coupon.getCouponRulesType())) {
                CouponStraightDown straightDown = new CouponStraightDown();
                straightDown.setCouponId(couponId);
                straightDown.setUpdateTime(new Date());
                // 判断不为空添加图片
                if (request.getParameter(DOWNPRICE) != null) {
                    straightDown.setDownPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(DOWNPRICE))));
                } else {
                    straightDown.setDownPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCTIONPRICE))));
                }
                straightDown.setDelFlag("0");
                couponStraightDownMapper.insertStraightDown(straightDown);
            }
            // 判断状态
            if ("2".equals(coupon.getCouponRulesType())) {
                CouponFullReduction fullReduction = new CouponFullReduction();
                fullReduction.setCouponId(couponId);
                fullReduction.setUpdateTime(new Date());
                fullReduction.setFullPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(FULLPRICE))));
                fullReduction.setReductionPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCTIONPRICE))));
                fullReduction.setDelFlag("0");
                couponFullReductionMapper.insertFullDuction(fullReduction);
            }

            List<CouponNo> list = new ArrayList<CouponNo>();
            // 判断不为空
            if (coupon.getCouponCount() != null && coupon.getCouponCount() > 0) {
                for (int i = 0; i < coupon.getCouponCount(); i++) {
                    // 封装卷码
                    CouponNo cn = new CouponNo();
                    cn.setCodeStatus("0");
                    cn.setCodeNo(RanddomMath.randomString(32));
                    cn.setCouponId(couponId);
                    list.add(cn);
                }
                // 创建优惠券券码
                couponNoMapper.createCouponNo(list);
            }

            List<CouponRange> mslist = new ArrayList<CouponRange>();
            if ("1".equals(status)) {
                // 分类插入
                String[] cateId = request.getParameterValues("cateIdp");
                for (int i = 0; i < cateId.length; i++) {
                    CouponRange cr = new CouponRange();
                    cr.setCouponId(couponId);
                    cr.setDelFlag("0");
                    cr.setCouponRangeFkId(Long.valueOf(cateId[i]));
                    // 设置类型：0：分类
                    cr.setCouponRangeType("0");
                    mslist.add(cr);
                }
            } else if ("2".equals(status)) {
                // 品牌插入
                String[] brandId = request.getParameterValues("brandIdP");
                for (int i = 0; i < brandId.length; i++) {
                    CouponRange cr = new CouponRange();
                    cr.setCouponId(couponId);
                    cr.setDelFlag("0");
                    cr.setCouponRangeFkId(Long.valueOf(brandId[i]));
                    // 设置类型：0：品牌
                    cr.setCouponRangeType("1");
                    mslist.add(cr);
                }
            } else {
                String[] goodsId = request.getParameterValues("goodsIdP");
                if (goodsId != null && goodsId.length != 0) {
                    for (int i = 0; i < goodsId.length; i++) {
                        CouponRange cr = new CouponRange();
                        cr.setCouponId(couponId);
                        cr.setCouponRangeFkId(Long.valueOf(goodsId[i]));
                        // 设置类型：0：sku
                        cr.setCouponRangeType("2");
                        cr.setDelFlag("0");
                        mslist.add(cr);
                    }
                }
            }

            if (!mslist.isEmpty()) {
                // 批量增加优惠券范围
                couponRangeMapper.createCouponRange(mslist);
            }

        }
        return f;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#searchCouponList(com.ningpai
     * .coupon.bean.Coupon, com.ningpai.util.PageBean)
     */
    @Override
    public PageBean searchCouponList(Coupon coupon, PageBean pageBean, String startTime, String endTime) {
        // 设置删除标记
        coupon.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(coupon);
        // 判断开始是否为空
        if (startTime != null && !"".equals(startTime)) {
            // 设置开始时间
            coupon.setCouponStartTime(DateUtil.stringToDate(startTime, null));
            // 设置开始时间
            paramMap.put("couponStartTime", startTime);

        }
        // 判断结束时间是否为空
        if (endTime != null && !"".equals(endTime)) {
            // 设置结束时间
            coupon.setCouponEndTime(DateUtil.stringToDate(endTime, null));
            // 设置结束时间
            paramMap.put("couponEndTime", endTime);

        }
        // 设置第三方id
        paramMap.put("businessId", coupon.getBusinessId());
        // 查询总数
        pageBean.setRows(couponMapper.selectCouponListCount(paramMap));
        // 计算分页
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        // 分页
        no = no == 0 ? 1 : no;
        if (pageBean.getPageNo() >= no) {
            // 设置页数
            pageBean.setPageNo(no);
            // 设置开始行数
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            // 设置条件
            pageBean.setObjectBean(coupon);
        }
        // 查询条件封装
        // 开始行数
        paramMap.put("start", pageBean.getStartRowNum());
        // 结束行数
        paramMap.put("number", pageBean.getEndRowNum());
        try {
            // 查询列表页
            pageBean.setList(couponMapper.selectCouponList(paramMap));
        } finally {
            // 至空参数
            paramMap = null;
        }
        // 返回结果
        return pageBean;
    }

    /*
     * 
     * 
     * @see com.ningpai.coupon.service.CouponService#delCoupon(java.lang.Long)
     */
    @Override
    @Transactional
    public int delCoupon(Long couponId) {
        Coupon cn = couponMapper.searchCouponById(couponId);
        int s = couponMapper.delCoupon(couponId);
        if (s > 0) {
            couponNoMapper.delCouponNo(couponId);
            this.deleteCouponShip(couponId, cn.getCouponRulesType());
        }
        return s;
    }

    /*
     * 
     * 
     * @see com.ningpai.coupon.service.CouponService#delCoupon(java.lang.Long)
     */
    @Override
    @Transactional
    public int delAllCoupon(Long[] couponId) {
        List<Long> list = new ArrayList<Long>();
        for (Long s : couponId) {
            Coupon cn = couponMapper.searchCouponById(s);
            this.deleteCouponShip(s, cn.getCouponRulesType());
            list.add(s);
        }
        int dcount = couponMapper.delAllCoupon(list);
        if (dcount > 0) {
            couponNoMapper.delAllCouponNo(list);
        }
        return dcount;
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#newdelCoupon(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    @Transactional
    public int newdelCoupon(Long couponId, Long thirdId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("couponId", couponId);
        paramMap.put("business", thirdId);
        Coupon cn = couponMapper.searchCouponById(couponId);
        int s = couponMapper.newDelCoupon(paramMap);
        if (s > 0) {
            couponNoMapper.delCouponNo(couponId);
            this.deleteCouponShip(couponId, cn.getCouponRulesType());
        }
        return s;
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#delAllCoupon(java.lang.Long[],
     * java.lang.Long)
     */
    @Override
    @Transactional
    public int delAllCoupon(Long[] couponId, Long thirdId) {
        List<Long> list = new ArrayList<Long>();
        for (Long s : couponId) {
            Coupon cn = couponMapper.searchCouponById(s);
            this.deleteCouponShip(s, cn.getCouponRulesType());
            list.add(s);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", list);
        paramMap.put("business", thirdId);
        int dcount = couponMapper.newDelAllCoupon(paramMap);
        if (dcount > 0) {
            couponNoMapper.delAllCouponNo(list);
        }
        return dcount;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#searchCouponById(java.lang.Long)
     */
    @Override
    public Coupon searchCouponById(Long couponId) {
        Coupon coupon = couponMapper.searchCouponById(couponId);
        // 直降
        if ("1".equals(coupon.getCouponRulesType())) {
            coupon.setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(couponId));
        }
        // 满减
        if ("2".equals(coupon.getCouponRulesType())) {
            coupon.setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(couponId));
        }

        return coupon;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#selectCouponRange(java.lang.
     * Long, java.lang.String)
     */
    @Override
    public List<Object> selectCouponRange(Long couponId, String type) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("couponId", couponId);
        paramMap.put("couponRangeType", type);
        List<CouponRange> scopelist = couponRangeMapper.selectCouponRange(paramMap);
        List<Object> list = null;
        List<Long> idList = new ArrayList<Long>();
        if (scopelist != null && !scopelist.isEmpty()) {
            for (int i = 0; i < scopelist.size(); i++) {
                idList.add(scopelist.get(i).getCouponRangeFkId());
            }
        }

        // 分类
        if ("0".equals(type) && !idList.isEmpty()) {
                list = goodsCateMapper.selectProductCateList(idList);
        }
        // 品牌
        if ("1".equals(type) && !idList.isEmpty()) {
                list = goodsBrandMapper.selectProductBrandList(idList);
        }
        // SKU
        if ("2".equals(type) && !idList.isEmpty()) {
                list = goodsMapper.selectProductSkuList(idList);
        }
        return list;
    }

    /*
     * 修改优惠券
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#doUpdateCouponById(com.ningpai
     * .coupon.bean.Coupon)
     */
    @Override
    @Transactional
    public int doUpdateCouponById(Coupon coupon, HttpServletRequest request, Long[] lelvelId, String status) {
        coupon.setModifyTime(new Date());
        Coupon cn = couponMapper.searchCouponById(coupon.getCouponId());
        // 删除优惠券规则信息
        this.deleteCouponShip(cn.getCouponId(), cn.getCouponRulesType());
        // 删除优惠券使用范围
        couponRangeMapper.delAllCouponRange(cn.getCouponId());

        int upflag = couponMapper.doUpdateCouponById(coupon);

        if (upflag == 1) {

            // 查询刚刚创建的ID
            Long couponId = coupon.getCouponId();
            // 删除之前的范围
            couponLelevlService.deleteCouponLelvel(couponId);
            // 创建优惠券等级范围
            List<CouponLelvel> clist = new ArrayList<CouponLelvel>();
            for (int i = 0; i < lelvelId.length; i++) {
                CouponLelvel level = new CouponLelvel();
                level.setCouponId(couponId);
                level.setLelvelId(lelvelId[i]);
                level.setLelvelDelFlag("0");
                clist.add(level);
            }
            if (clist != null && !clist.isEmpty()) {
                couponLelevlService.addCouponLelvel(clist);
            }

            if ("1".equals(coupon.getCouponRulesType())) {
                CouponStraightDown straightDown = new CouponStraightDown();
                straightDown.setCouponId(couponId);
                straightDown.setUpdateTime(new Date());
                straightDown.setDownPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(DOWNPRICE))));
                straightDown.setDelFlag("0");
                couponStraightDownMapper.insertStraightDown(straightDown);
            }
            if ("2".equals(coupon.getCouponRulesType())) {
                CouponFullReduction fullReduction = new CouponFullReduction();
                fullReduction.setCouponId(couponId);
                fullReduction.setUpdateTime(new Date());
                fullReduction.setFullPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(FULLPRICE))));
                fullReduction.setReductionPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCTIONPRICE))));
                fullReduction.setDelFlag("0");
                couponFullReductionMapper.insertFullDuction(fullReduction);
            }
            List<CouponRange> mslist = new ArrayList<CouponRange>();
            if ("1".equals(status)) {
                // 分类插入
                String[] cateId;
                // 判断是否是全场促销 0 不是，1是
                if ("0".equals(coupon.getIsAll())) {
                    cateId = request.getParameterValues("cateIdp");
                } else {
                    cateId = request.getParameterValues("thirdcate_ids");
                }
                for (int i = 0; i < cateId.length; i++) {
                    CouponRange cr = new CouponRange();
                    cr.setCouponId(couponId);
                    cr.setDelFlag("0");
                    cr.setCouponRangeFkId(Long.valueOf(cateId[i]));
                    // 设置类型：0：分类
                    cr.setCouponRangeType("0");
                    mslist.add(cr);
                }
            } else if ("2".equals(status)) {
                // 品牌插入
                String[] brandId;
                // 判断是否是全场促销 0 不是，1是
                if ("0".equals(coupon.getIsAll())) {
                    brandId = request.getParameterValues("brandIdP");
                } else {
                    brandId = request.getParameterValues("brand_allids");
                }
                for (int i = 0; i < brandId.length; i++) {
                    CouponRange cr = new CouponRange();
                    cr.setCouponId(couponId);
                    cr.setDelFlag("0");
                    cr.setCouponRangeFkId(Long.valueOf(brandId[i]));
                    // 设置类型：0：品牌
                    cr.setCouponRangeType("1");
                    mslist.add(cr);
                }
            } else {
                String[] goodsId;
                // 判断是否全场商品促销
                if ("0".equals(coupon.getIsAll())) {
                    goodsId = request.getParameterValues("goodsIdP");
                } else {
                    goodsId = request.getParameterValues("product_allids");
                }
                if (goodsId != null && goodsId.length != 0) {
                    for (int i = 0; i < goodsId.length; i++) {
                        CouponRange cr = new CouponRange();
                        cr.setCouponId(couponId);
                        cr.setCouponRangeFkId(Long.valueOf(goodsId[i]));
                        // 设置类型：0：sku
                        cr.setCouponRangeType("2");
                        cr.setDelFlag("0");
                        mslist.add(cr);
                    }
                }
            }

            if (!mslist.isEmpty()) {
                // 批量增加优惠券范围
                couponRangeMapper.createCouponRange(mslist);
            }

        }
        return upflag;
    }

    /**
     * 删除优惠券附属信息
     * 
     * @param couponId
     * @param couponRulesType
     */
    private void deleteCouponShip(Long couponId, String couponRulesType) {

        // 直降
        if ("1".equals(couponRulesType)) {
            couponStraightDownMapper.deleteStraightDown(couponId);
        }
        // 满减
        if ("2".equals(couponRulesType)) {
            couponFullReductionMapper.deleteFullReduction(couponId);
        }
        // 删除范围信息
        couponRangeMapper.delAllCouponRange(couponId);

    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#myCouponList(com.ningpai.util
     * .PageBean, java.lang.Long, java.lang.String)
     */
    @Override
    public PageBean myCouponList(PageBean pageBean, Long customerId, String codeStatus) {
        // 分装实体类属性
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if ("1".equals(codeStatus)) {
            paramMap.put(CODESTATUS, "1");
            // 未使用
        } else if ("2".equals(codeStatus)) {
            paramMap.put(CODESTATUS, "2");
            // 已经使用
        } else if ("3".equals(codeStatus)) {
            // 过期
            paramMap.put(CODESTATUS, "3");
        } else {
            paramMap.put(CODESTATUS, "");
        }
        paramMap.put(CUSTOMERID, customerId);
        // 查询总数
        pageBean.setRows(couponMapper.myCouponListCount(paramMap));
        // //计算分页
        // 查询条件封装
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        try {
            // 查询列表页
            List<Object> couponlist = couponMapper.myCouponList(paramMap);
            if (couponlist != null && !couponlist.isEmpty()) {
                for (int i = 0; i < couponlist.size(); i++) {
                    // 直降
                    if ("1".equals(((Coupon) couponlist.get(i)).getCouponRulesType())) {
                        ((Coupon) couponlist.get(i)).setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(((Coupon) couponlist.get(i)).getCouponId()));
                    }
                    // 满减
                    if ("2".equals(((Coupon) couponlist.get(i)).getCouponRulesType())) {
                        ((Coupon) couponlist.get(i)).setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(((Coupon) couponlist.get(i)).getCouponId()));
                    }
                    ((Coupon) couponlist.get(i)).setGclist(this.selectCouponRange(((Coupon) couponlist.get(i)).getCouponId(), "0"));
                    ((Coupon) couponlist.get(i)).setGblist(this.selectCouponRange(((Coupon) couponlist.get(i)).getCouponId(), "1"));
                    List<Object> gpList = this.selectCouponRange(((Coupon) couponlist.get(i)).getCouponId(), "2");
                    ((Coupon) couponlist.get(i)).setGplist(gpList);

                }
            }

            pageBean.setList(couponlist);
        } finally {
            paramMap = null;
        }
        return pageBean;

    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#selectCouponListByIds(java.lang
     * .Long, java.lang.Long, java.lang.Long,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public List<Coupon> selectCouponListByIds(List<ParamIds> list, HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", list);
        paramMap.put(CUSTOMERID, request.getSession().getAttribute(CUSTOMERID));
        List<Coupon> couponlist = couponMapper.selectCouponListByIds(paramMap);
        if (couponlist != null && !couponlist.isEmpty()) {
            for (int i = 0; i < couponlist.size(); i++) {
                // 直降
                if ("1".equals(couponlist.get(i).getCouponRulesType())) {
                    couponlist.get(i).setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(couponlist.get(i).getCouponId()));
                }
                // 满减
                if ("2".equals(couponlist.get(i).getCouponRulesType())) {
                    couponlist.get(i).setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(couponlist.get(i).getCouponId()));
                }
            }

        }
        return couponlist;
    }

    /**
     * 查询订单商品可以使用的未领取的优惠券
     * @param list
     * @param request
     * @return
     */
    @Override
    public List<Coupon> selectCouponListByIdsNew(List<ParamIds> list, HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", list);
        List<Coupon> couponlist = couponMapper.selectCouponListByIdsNew(paramMap);
        if (couponlist != null && !couponlist.isEmpty()) {
            for (int i = 0; i < couponlist.size(); i++) {
                // 直降
                if ("1".equals(couponlist.get(i).getCouponRulesType())) {
                    couponlist.get(i).setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(couponlist.get(i).getCouponId()));
                }
                // 满减
                if ("2".equals(couponlist.get(i).getCouponRulesType())) {
                    couponlist.get(i).setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(couponlist.get(i).getCouponId()));
                }
            }

        }
        return couponlist;
    }
    /*
     * com.ningpai.coupon.service.CouponService#selectCouponByCodeNo(java.lang
     * .String)
     */
    @Override
    public Coupon selectCouponByCodeNo(String codeNo) {
        Coupon coupon = couponMapper.selectCouponByCodeNo(codeNo);
        if (coupon != null) {
            // 直降
            if ("1".equals(coupon.getCouponRulesType())) {
                coupon.setCouponStraightDown(couponStraightDownMapper.selectCouponStraightDown(coupon.getCouponId()));
            }
            // 满减
            if ("2".equals(coupon.getCouponRulesType())) {
                coupon.setCouponFullReduction(couponFullReductionMapper.selectCouponFullReduction(coupon.getCouponId()));
            }

        }
        return coupon;
    }

    /**
     * 获取优惠券优惠价格
     * 2016-02-02 wuyanbo add
     * @param codeNo
     * @return
     */
    @Override
    public BigDecimal getCouponPriceByCodeNo(String codeNo) {
        Coupon coupon = this.selectCouponByCodeNo(codeNo);
        BigDecimal couponPrice = BigDecimal.valueOf(0.00);//优惠券优惠价格
        if (coupon != null) {
            // 直降
            if ("1".equals(coupon.getCouponRulesType())) {
                couponPrice = coupon.getCouponStraightDown().getDownPrice();
            }
            // 满减
            if ("2".equals(coupon.getCouponRulesType())) {
                couponPrice = coupon.getCouponStraightDown().getDownPrice();
            }

        }
        return couponPrice;
    }
    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#myCouponNoCount(java.lang.Long)
     */
    @Override
    public int myCouponNoCount(Long customerId) {
        return couponMapper.myCouponNoCount(customerId);
    }

    /*
     * 
     * 返回一个卷卷码 并且修改此优惠券为已经领取
     * @see com.ningpai.coupon.service.CouponService#
     * selectOneCouponNoByCouponIdAndUpdateNoIsGet(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public Coupon selectOneCouponNoByCouponIdAndUpdateNoIsGet(Long couponId, Long customerId) {
        Coupon coupon = couponMapper.selectOneCouponNoByCouponId(couponId);
        if (coupon != null) {
            CouponNo cn = new CouponNo();
            cn.setCodeNo(coupon.getCodeNo());
            cn.setCodeStatus("1");
            couponNoMapper.modifyNoStatus(cn);
        }
        return coupon;
    }

    /*
     * 修改优惠劵状态
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#modifyNoStatus(java.lang.String,
     * java.lang.String)
     */
    @Override
    public int modifyNoStatus(String couponNo, String codeStatus) {
        CouponNo no = new CouponNo();
        // 要修改的优惠劵劵码
        no.setCodeNo(couponNo);
        // 要修改的优惠劵
        no.setCodeStatus(codeStatus);
        return couponNoMapper.modifyNoStatus(no);
    }

    /**
     * 根据codeId获取优惠券时间
     * @param codeId
     * @return
     */
    @Override
    public Date selectCouponTimeByCodeId(Long codeId) {
        return couponMapper.selectCouponTimeByCodeId(codeId);
    }

    /*
     * 查询优惠劵总数量
     * @see com.ningpai.coupon.service.CouponService#selectCouponCount()
     */
    @Override
    public int selectCouponCount() {
        return couponMapper.selectCouponListCount(null);
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#returnCouponNo(java.lang.Long)
     */
    @Override
    public int returnCouponNo(String couponNo) {
        return couponNoMapper.returnCouponNo(couponNo);
    }

    /*
     * 赠送优惠劵
     * 
     * @see
     * com.ningpai.coupon.service.CouponService#giveCusCoupon(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public int giveCusCoupon(String couponNo, Long customerId) {
        CouponNo cn = new CouponNo();
        cn.setCodeNo(couponNo);
        cn.setCustomerId(customerId);
        // 赠送优惠劵
        return couponNoMapper.giveCusCoupon(cn);
    }

    @Override
    public List<Object> queryCouponList() {
        return couponMapper.queryCouponList();
    }

    /*
     * 
     * @see com.ningpai.coupon.service.CouponService#selectCouponListByAble()
     */
    @Override
    public List<Coupon> selectCouponListByAble() {
        return couponMapper.selectCouponListByAble();
    }

    public CouponNoMapper getCouponNoMapper() {
        return couponNoMapper;
    }

    @Resource(name = "CouponNoMapper")
    public void setCouponNoMapper(CouponNoMapper couponNoMapper) {
        this.couponNoMapper = couponNoMapper;
    }

    public CouponMapper getCouponMapper() {
        return couponMapper;
    }

    @Resource(name = "CouponMapper")
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    public CouponRangeMapper getCouponRangeMapper() {
        return couponRangeMapper;
    }

    @Resource(name = "CouponRangeMapper")
    public void setCouponRangeMapper(CouponRangeMapper couponRangeMapper) {
        this.couponRangeMapper = couponRangeMapper;
    }

    public GoodsCateMapper getGoodsCateMapper() {
        return goodsCateMapper;
    }

    @Resource(name = "GoodsCateMapper")
    public void setGoodsCateMapper(GoodsCateMapper goodsCateMapper) {
        this.goodsCateMapper = goodsCateMapper;
    }

    public GoodsBrandMapper getGoodsBrandMapper() {
        return goodsBrandMapper;
    }

    @Resource(name = "GoodsBrandMapper")
    public void setGoodsBrandMapper(GoodsBrandMapper goodsBrandMapper) {
        this.goodsBrandMapper = goodsBrandMapper;
    }

    public GoodsMapper getGoodsMapper() {
        return goodsMapper;
    }

    @Resource(name = "GoodsMapper")
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    public CouponFullReductionMapper getCouponFullReductionMapper() {
        return couponFullReductionMapper;
    }

    @Resource(name = "CouponFullReductionMapper")
    public void setCouponFullReductionMapper(CouponFullReductionMapper couponFullReductionMapper) {
        this.couponFullReductionMapper = couponFullReductionMapper;
    }

    public CouponStraightDownMapper getCouponStraightDownMapper() {
        return couponStraightDownMapper;
    }

    @Resource(name = "CouponStraightDownMapper")
    public void setCouponStraightDownMapper(CouponStraightDownMapper couponStraightDownMapper) {
        this.couponStraightDownMapper = couponStraightDownMapper;
    }

    public CouponLelevlService getCouponLelevlService() {
        return couponLelevlService;
    }

    @Resource(name = "CouponLelevlService")
    public void setCouponLelevlService(CouponLelevlService couponLelevlService) {
        this.couponLelevlService = couponLelevlService;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    /*
     * 查询优惠券数量
     * (non-Javadoc)
     * @see com.ningpai.coupon.service.CouponService#countByCodeStatus(java.lang.Long, java.lang.String)
     */
    @Override
    public Long countByCodeStatus(Long customerId, String status) {
     // 分装实体类属性
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CODESTATUS, status);
        paramMap.put(CUSTOMERID, customerId);
        return (long) this.couponMapper.myCouponListCount(paramMap);
    }

}
