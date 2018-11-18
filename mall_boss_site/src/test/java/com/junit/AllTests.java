package com.junit;


import com.junit.gift.coupon.service.CouponServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 单元测试模块打包类
 * @author ggn
 *
 */
@RunWith(Suite. class )
@Suite.SuiteClasses( {
        CouponServiceTest.class
        } )
public   class  AllTests  {
} 