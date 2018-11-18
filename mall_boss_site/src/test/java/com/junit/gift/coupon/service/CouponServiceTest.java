/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.gift.coupon.service;


import com.alibaba.fastjson.JSON;
import com.ningpai.coupon.bean.*;
import com.ningpai.coupon.dao.*;
import com.ningpai.coupon.service.CouponLelevlService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.coupon.service.impl.CouponServiceImpl;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.system.bean.PointSet;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.RanddomMath;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.*;

/**
 * 优惠劵测试类
 * @author NINGPAI-LIH
 * @since 2015年3月2日17:00:53
 *
 */
public class CouponServiceTest extends UnitilsJUnit3 {

    /**
     * HttpServletResponse获取方式
     *  MockHttpServletResponse response = new MockHttpServletResponse();
     *  HttpServletRequest获取方式
     *  MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
     */
    /**
     * 准备CouponServiceImpl返回的模拟数据
     */
    @FileContent("couponList.js")
    private String couponListJS;
    @FileContent("fullReduction.js")
    private String fullReductionJs;
    @FileContent("straightDown.js")
    private String straightDownJS;
    @FileContent("pointSet.js")
    private String pointSetJS;
    @FileContent("customerPoint.js")
    private String customerPointJs;
    @FileContent("coupon.js")
    private String couponJs;



    @TestedObject
    private CouponService couponService = new CouponServiceImpl();



    @InjectIntoByType
    Mock<CouponNoMapper> couponNoMapperMock;
    @InjectIntoByType
    Mock<CouponMapper> couponMapperMock;
    @InjectIntoByType
    Mock<CouponStraightDownMapper> couponStraightDownMapperMock;
    @InjectIntoByType
    Mock<CouponFullReductionMapper> couponFullReductionMapperMock;
    @InjectIntoByType
    Mock<CouponRangeMapper> couponRangeMapperMock;



    //各个测试用例共享的测试数据
    List<Coupon> list;
    CouponStraightDown straightDown;
    CouponFullReduction fullReduction;
    PointSet pointSet;
    CustomerPoint customerPoint;
    Coupon coupon;
    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        list = JSON.parseArray(couponListJS, Coupon.class);
        straightDown=JSON.parseObject(straightDownJS, CouponStraightDown.class);
        pointSet=JSON.parseObject(pointSetJS, PointSet.class);
        customerPoint=JSON.parseObject(customerPointJs, CustomerPoint.class);
        coupon=JSON.parseObject(couponJs, Coupon.class);
        fullReduction= JSON.parseObject(fullReductionJs,CouponFullReduction.class);
    }

    /**
     * 测试数据优惠券信息
     */
    @Test
    public void testReutrnCouponNo(){
        couponNoMapperMock.returns(1).returnCouponNo("FHP2K6QBRJWCJ70XCRWCIAXJT2G7B6M1");
        assertEquals(1, couponService.returnCouponNo("FHP2K6QBRJWCJ70XCRWCIAXJT2G7B6M1"));
    }

    /**
     * 查询优惠券列表
     */
    @Test
    public void testQueryCouponListToBoss(){
        couponMapperMock.returns(list).queryCouponListToBoss();
        assertEquals(3, couponService.queryCouponListToBoss().size());
    }

    /**
     * 查询所有优惠卷信息
     */
    @Test
    public void testSelectCouponList_site(){
        couponMapperMock.returns(list).selectCouponList_site();
        couponStraightDownMapperMock.returns(straightDown).selectCouponStraightDown(101L);
        assertEquals(3, couponService.selectCouponList_site().size());
    }

    /**
     * 根据商家ID查询优惠券
     */
    @Test
    public void testSelectCouponListByStoreId(){
        couponMapperMock.returns(list).selectCouponListByStoreId(0L);
        assertEquals(3, couponService.selectCouponListByStoreId(0L).size());
    }

    /**
     * 修改指定会员总积分
     */
    @Test
    public void testUpdateCustomerPoint(){
        CustomerPoint point = new CustomerPoint();
        point.setCustomerId(1001L);
        point.setPointSum(1000L);
        couponMapperMock.returns(1).updateCustomerPoint(point);
        assertEquals(1, couponService.updateCustomerPoint(point));
    }

    /**
     * 获取积分消费规则
     */
    @Test
    public void testSelectPointSet(){
        couponMapperMock.returns(pointSet).selectPointSet();
        assertNotNull(couponService.selectPointSet());
    }

    /**
     * 获取当前会员的所有积分
     */
    @Test
    public void testSelectCustomerPointByCustomerId(){
        couponMapperMock.returns(customerPoint).selectCustomerPointByCustomerId(1001L);
        assertNotNull(couponService.selectCustomerPointByCustomerId(1001L));
    }

    @InjectIntoByType
    Mock<CouponLelevlService> couponLelevlServiceMock;


    /**
     * 添加优惠券
     */
     @Test
     public void testDoAddCoupon(){
         //模拟request数据
         MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
         request.setParameter("downPrice","100");
         request.setParameter("fullPrice","100");
         request.setParameter("reductionPrice","100");
         request.setParameter("goodsId",new String[]{"1"});

        couponMapperMock.returns(1).doAddCoupon(coupon);
        couponMapperMock.returns(101L).selectLastId();
         List<CouponLelvel> clist = new ArrayList<CouponLelvel>();
             CouponLelvel level = new CouponLelvel();
             level.setCouponId(101L);
             level.setLelvelId(1L);
             level.setLelvelDelFlag("0");
             clist.add(level);
        couponLelevlServiceMock.returns(1).addCouponLelvel(clist);
        couponStraightDownMapperMock.returns(1).insertStraightDown(straightDown);
        couponFullReductionMapperMock.returns(1).insertFullDuction(fullReduction);
         List<CouponNo> list = new ArrayList<CouponNo>();
         CouponNo cn = new CouponNo();
         cn.setCodeStatus("0");
         cn.setCodeNo(RanddomMath.randomString(32));
         cn.setCouponId(101L);
         list.add(cn);
         List<CouponRange> mslist = new ArrayList<CouponRange>();
         CouponRange cr = new CouponRange();
         cr.setCouponId(101L);
         cr.setCouponRangeFkId(1L);
         cr.setCouponRangeType("2");
         cr.setDelFlag("0");
         mslist.add(cr);
        couponNoMapperMock.returns(1).createCouponNo(list);
         couponRangeMapperMock.returns(1).createCouponRange(mslist);
         assertEquals(1, couponService.doAddCoupon(coupon, request, new Long[]{1L}));
     }

     public void testdoAddCoupon(){
 //模拟request数据
         MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
         request.setParameter("downPrice","100");
         request.setParameter("fullPrice","100");
         request.setParameter("reductionPrice","100");
         request.setParameter("brandIdP",new String[]{"1"});

        couponMapperMock.returns(1).doAddCoupon(coupon);
        couponMapperMock.returns(101L).selectLastId();
         List<CouponLelvel> clist = new ArrayList<CouponLelvel>();
             CouponLelvel level = new CouponLelvel();
             level.setCouponId(101L);
             level.setLelvelId(1L);
             level.setLelvelDelFlag("0");
             clist.add(level);
        couponLelevlServiceMock.returns(1).addCouponLelvel(clist);
        couponStraightDownMapperMock.returns(1).insertStraightDown(straightDown);
        couponFullReductionMapperMock.returns(1).insertFullDuction(fullReduction);
         List<CouponNo> list = new ArrayList<CouponNo>();
         CouponNo cn = new CouponNo();
         cn.setCodeStatus("0");
         cn.setCodeNo(RanddomMath.randomString(32));
         cn.setCouponId(101L);
         list.add(cn);
         List<CouponRange> mslist = new ArrayList<CouponRange>();
         CouponRange cr = new CouponRange();
         cr.setCouponId(101L);
         cr.setCouponRangeFkId(1L);
         cr.setCouponRangeType("2");
         cr.setDelFlag("0");
         mslist.add(cr);
        couponNoMapperMock.returns(1).createCouponNo(list);
         couponRangeMapperMock.returns(1).createCouponRange(mslist);
         assertEquals(1, couponService.doAddCoupon(coupon,request,new Long[]{1L},"2"));
     }


    /**
     * 分页查询所有优惠券列表
     */
    @Test
    public void testSearchCouponList(){
        Map<String,Object> paramMap = MapUtil.getParamsMap(coupon);
        couponMapperMock.returns(3).selectCouponListCount(paramMap);
        couponMapperMock.returns(list).selectCouponList(paramMap);
        assertNotNull(couponService.searchCouponList(coupon, new PageBean(),
                "2015-01-01 01:01:01", "2020-01-01 01:01:01"));
    }

    /**
     * 删除优惠券
     */
    @Test
    public void testDelCoupon(){
        couponMapperMock.returns(coupon).searchCouponById(101L);
        couponMapperMock.returns(1).delCoupon(101L);
        couponNoMapperMock.returns(1).delCouponNo(101L);
        couponStraightDownMapperMock.returns(1).deleteStraightDown(101L);
        couponFullReductionMapperMock.returns(1).deleteFullReduction(101L);
        couponRangeMapperMock.returns(1).delAllCouponRange(101L);
        assertEquals(1, couponService.delCoupon(101L));
    }

    /**
     *  批量删除优惠券
     */
    @Test
    public void testDelAllCoupon(){
        couponMapperMock.returns(coupon).searchCouponById(101L);
        couponStraightDownMapperMock.returns(1).deleteStraightDown(101L);
        couponFullReductionMapperMock.returns(1).deleteFullReduction(101L);
        couponRangeMapperMock.returns(1).delAllCouponRange(101L);
        List<Long> lists = new ArrayList<Long>();
        lists.add(101L);
        couponMapperMock.returns(1).delAllCoupon(lists);
        couponNoMapperMock.returns(1).delAllCouponNo(lists);
        assertEquals(1, couponService.delAllCoupon(new Long[]{101L}));
    }

    /**
     * 查询优惠券详细信息
     */
    @Test
    public void testSearchCouponById(){
        couponMapperMock.returns(coupon).searchCouponById(101L);
        couponStraightDownMapperMock.returns(straightDown).selectCouponStraightDown(101L);
        couponFullReductionMapperMock.returns(fullReduction).selectCouponFullReduction(101L);
        assertNotNull(couponService.searchCouponById(101L));
    }


    /**
     *修改优惠券
     */
    public void testDoUpdateCouponById(){
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        request.setParameter("downPrice","100");
        request.setParameter("fullPrice","100");
        request.setParameter("reductionPrice","100");
        request.setParameter("goodsIdP",new String[]{"1"});

        couponMapperMock.returns(coupon).searchCouponById(101L);
        couponRangeMapperMock.returns(1).delAllCouponRange(101L);
        couponMapperMock.returns(1).doUpdateCouponById(coupon);
        couponLelevlServiceMock.returns(1).deleteCouponLelvel(101L);
        List<CouponLelvel> clist = new ArrayList<CouponLelvel>();
        CouponLelvel level = new CouponLelvel();
        level.setCouponId(101L);
        level.setLelvelId(1L);
        level.setLelvelDelFlag("0");
        clist.add(level);
        couponLelevlServiceMock.returns(1).addCouponLelvel(clist);
        couponStraightDownMapperMock.returns(1).insertStraightDown(straightDown);
        couponFullReductionMapperMock.returns(1).insertFullDuction(fullReduction);
        List<CouponRange> mslist = new ArrayList<CouponRange>();
        CouponRange cr = new CouponRange();
        cr.setCouponId(101L);
        cr.setCouponRangeFkId(1L);
        // 设置类型：0：sku
        cr.setCouponRangeType("2");
        cr.setDelFlag("0");
        mslist.add(cr);
        couponRangeMapperMock.returns(1).createCouponRange(mslist);
        assertEquals(1, couponService.doUpdateCouponById(coupon, request, new Long[]{1L}, "0"));
    }

    @InjectIntoByType
    Mock<GoodsCateMapper> goodsCateMapperMock;
    @InjectIntoByType
    Mock<GoodsBrandMapper> goodsBrandMapperMock;
    @InjectIntoByType
    Mock<GoodsMapper> goodsMapperrMock;
  /*

 public void testSelectCouponRange(){

    }
    */

    /**
     * 我的优惠券
     */
    public void testMyCouponList(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", 1);
        paramMap.put("codeStatus", "2");
        paramMap.put("start", 0);
        paramMap.put("number", 15);
        couponMapperMock.returns(3).myCouponListCount(paramMap);
        couponMapperMock.returns(list).myCouponList(paramMap);
        couponStraightDownMapperMock.returns(straightDown).selectCouponStraightDown(101L);
        couponFullReductionMapperMock.returns(fullReduction).selectCouponFullReduction(101L);
       assertEquals(3, couponService.myCouponList(new PageBean(), 1L, "2").getList().size());
    }

    /**
     * 查询我可以使用 未过期的所有优惠券总数
     */
    public void testMyCouponNoCount(){
         couponMapperMock.returns(1).myCouponNoCount(101L);
        assertEquals(1, couponService.myCouponNoCount(101L));
    }


    /**
     * 查询货品可以使用的优惠券
     */
    public void testSelectCouponListByIds(){
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        request.getSession().setAttribute("customerId", 1L);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<ParamIds> lists = new ArrayList<ParamIds>();
        ParamIds pids = new ParamIds();
        pids.setCouponRangeFkId(1L);
        pids.setCouponRangeType("2");
        lists.add(pids);
        paramMap.put("list", lists);
        paramMap.put("customerId", 1L);
        couponMapperMock.returns(list).selectCouponListByIds(paramMap);
        couponStraightDownMapperMock.returns(straightDown).selectCouponStraightDown(101L);
        couponFullReductionMapperMock.returns(fullReduction).selectCouponFullReduction(101L);
        assertEquals(3, couponService.selectCouponListByIds(lists, request).size());

    }

    /**
     * 根据卷码查询优惠券
     */
    public void testSelectCouponByCodeNo(){
        couponMapperMock.returns(coupon).selectCouponByCodeNo("QIANMI");
        couponStraightDownMapperMock.returns(straightDown).selectCouponStraightDown(101L);
        couponFullReductionMapperMock.returns(fullReduction).selectCouponFullReduction(101L);
        assertNotNull(couponService.selectCouponByCodeNo("QIANMI"));
    }

    /**
     * 返回一个卷卷码 并且修改此优惠券为已经领取
     */
    public void testSelectOneCouponNoByCouponIdAndUpdateNoIsGet(){
        couponMapperMock.returns(coupon).selectOneCouponNoByCouponId(101L);
        CouponNo cn = new CouponNo();
        cn.setCodeNo(coupon.getCodeNo());
        cn.setCodeStatus("1");
        couponNoMapperMock.returns(1).modifyNoStatus(cn);
        assertNotNull(couponService.selectOneCouponNoByCouponIdAndUpdateNoIsGet(101L, 1L));
    }

    /**
     * 赠送优惠劵
     */
    public void testGiveCusCoupon(){
        CouponNo cn = new CouponNo();
        cn.setCodeNo("QianMi");
        cn.setCustomerId(1L);
        // 赠送优惠劵
        couponNoMapperMock.returns(1).giveCusCoupon(cn);
        assertEquals(1, couponService.giveCusCoupon("QianMi", 1L));

    }

    /**
     * 修改优惠劵状态
     */
    public void testModifyNoStatus(){
        CouponNo no = new CouponNo();
        // 要修改的优惠劵劵码
        no.setCodeNo("QianMi");
        // 要修改的优惠劵
        no.setCodeStatus("2");
        couponNoMapperMock.returns(1).modifyNoStatus(no);
        assertEquals(1, couponService.modifyNoStatus("QianMi", "2"));
    }

    /**
     * 添加
     */
    public void testAddCouponC(){
        List<CouponNo> list = new ArrayList<CouponNo>();
            // 封装卷码
            CouponNo cn = new CouponNo();
            cn.setCodeStatus("0");
            cn.setCodeNo(RanddomMath.randomString(32));
            cn.setCouponId(101L);
            list.add(cn);
        // 创建优惠券券码
        couponNoMapperMock.returns(1).createCouponNo(list);
        couponService.addCouponC(101L);
    }

    /**
     * 根据codeId获取优惠券时间
     */
    public void testSelectCouponTimeByCodeId(){
        Date d = new Date();
        couponMapperMock.returns(d).selectCouponTimeByCodeId(1L);
        assertNotNull(couponService.selectCouponTimeByCodeId(1L));
    }

    /**
     * 查询优惠劵总数量
     */
    public void testSelectCouponCount(){
        couponMapperMock.returns(1).selectCouponListCount(null);
        assertEquals(1, couponService.selectCouponCount());
    }

    /**
     * 获取优惠券信息
     */
    public void testQueryCouponList(){
         List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add(2);
         couponMapperMock.returns(list).queryCouponList();
        assertEquals(2, couponService.queryCouponList().size());
    }

    /**
     * 查询可使用的优惠券
     */
    public void testSelectCouponListByAble(){
         couponMapperMock.returns(list).selectCouponListByAble();
         assertEquals(3, couponService.selectCouponListByAble().size());
    }


    /**
     * 删除优惠券
     */
    public void testNewdelCoupon() {
        couponMapperMock.returns(coupon).searchCouponById(101L);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("couponId", 101L);
        paramMap.put("business", 0L);
        couponMapperMock.returns(1).newDelCoupon(paramMap);
        couponNoMapperMock.returns(1).delCouponNo(101L);
        couponStraightDownMapperMock.returns(1).deleteStraightDown(101L);
        couponFullReductionMapperMock.returns(1).deleteFullReduction(101L);
        couponRangeMapperMock.returns(1).delAllCouponRange(101L);
        assertEquals(1, couponService.newdelCoupon(101L, 0L));
    }

    /**
     * 批量删除优惠券
     */
    public void testdelAllCoupon(){
        List<Long> list = new ArrayList<Long>();
        list.add(101L);
        couponMapperMock.returns(coupon).searchCouponById(101L);
        couponStraightDownMapperMock.returns(1).deleteStraightDown(101L);
        couponFullReductionMapperMock.returns(1).deleteFullReduction(101L);
        couponRangeMapperMock.returns(1).delAllCouponRange(101L);
        couponMapperMock.returns(1).delAllCoupon(list);
       assertEquals(1, couponService.delAllCoupon(new Long[]{101L}));
    }
}
