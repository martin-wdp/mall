package com.junit.gift.coupon.dao;

import com.alibaba.fastjson.JSON;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.dao.CouponMapper;
import com.ningpai.coupon.dao.CouponNoMapper;
import com.ningpai.coupon.dao.impl.CouponMapperImpl;
import com.ningpai.coupon.dao.impl.CouponNoMapperImpl;
import com.ningpai.util.MapUtil;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit3;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.io.annotation.FileContent;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.HashMap;
import java.util.Map;

/**
 * 优惠券Dao单元测试
 * Created by guoguangnan on 2015/9/6.
 */
public class CouponMapperImplTest extends UnitilsJUnit3 {

    @SpringApplicationContext({ "classpath:testapplication/appContext-common.xml",
            "classpath:testapplication/testDatasource.xml",})
    protected ApplicationContext applicationContext;

    @SpringBeanByType
    private CouponMapper couponMapper;

    @FileContent("COUPON.js")
    private String couponJS;

    private Coupon coupon;
    @Override
    public void setUp(){
        //参数转换
        coupon = JSON.parseObject(couponJS, Coupon.class);
    }

    /**
     * 查询优惠券列表总数
     */
    @DataSet("COUPON.xml")
    public void testSelectCouponListCount(){
        //设置查询参数
        Map<String,Object> paramMap = MapUtil.getParamsMap(coupon);
        int count =couponMapper.selectCouponListCount(paramMap);
        assertTrue(count>0);
    }
}
