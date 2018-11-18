package com.junit.gift.coupon.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.dao.CouponRangeMapper;
import com.ningpai.coupon.service.CouponRangeService;
import com.ningpai.coupon.service.impl.CouponRangeServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * 优惠券范围接口
 * Created by Zhoux on 2015/9/15.
 */
public class CouponRangeServiceTest extends UnitilsJUnit3 {
    @TestedObject
    private CouponRangeService couponRangeService = new CouponRangeServiceImpl();

    /**
     * 准备CouponServiceImpl返回的模拟数据
     */
    @FileContent("couponRange.js")
    private String couponRangeJS;

    @InjectIntoByType
    Mock<CouponRangeMapper> couponRangeMapperMock;

    //各个测试用例共享的测试数据
    List<CouponRange> list;

    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        list = JSON.parseArray(couponRangeJS, CouponRange.class);
    }

    /**
     * 查询优惠券范围
     */
    @Test
    public void testSelectCouponRangeList() {
        couponRangeMapperMock.returns(list).selectCouponRangeList(list.get(0));
        assertEquals(1,couponRangeService.selectCouponRangeList(list.get(0)).size());
    }
}
