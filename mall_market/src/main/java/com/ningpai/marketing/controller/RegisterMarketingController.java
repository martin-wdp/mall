package com.ningpai.marketing.controller;

import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.marketing.bean.RegisterMarketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.util.MenuSession;
import com.qpmall.marketing.bean.RegisterCoupon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 注册营销控制器 Created by Zhoux on 2015/4/1.
 */
@Controller
public class RegisterMarketingController {
    // 促销service
    @Resource(name = "MarketingService")
    private MarketingService marketingService;
    // 优惠券接口
    @Resource(name = "CouponService")
    private CouponService couponService;
    // 会员积分接口
    @Resource(name = "customerPointServiceMapper")
    private CustomerPointServiceMapper customerPointServiceMapper;

    /**
     * 注册营销页
     * 
     * @param request
     * @param response
     * @param flag
     * @return
     */
    @RequestMapping("/registerMarketing")
    public ModelAndView registerMarketing(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "flag", required = false, defaultValue = "0") final String flag) {
        MenuSession.sessionMenu(request);
        return new ModelAndView("jsp/coupon/couponregister", "parameter",
                marketingService.findRegisterMarketing()).addObject("flag",
                flag).addObject("coupon", couponService.queryCouponListToBoss());

    }

    /**
     * 修改注册送积分信息
     * 
     * @param registerMarketing
     * @return
     */
    @RequestMapping("/updateRegisterCoupon")
    public ModelAndView updateRegisterCoupon(HttpServletRequest request,RegisterMarketing registerMarketing) {
//        edit by 付陈林 增加对商品的处理
        //增加多个优惠劵
        String[] couponIdP = request.getParameterValues("couponIdP");
        List<RegisterCoupon> rclist =new ArrayList<RegisterCoupon>();
        for(int i=0;i<couponIdP.length;i++){
            RegisterCoupon rc =new RegisterCoupon();
            rc.setId(Long.valueOf(i));
            rc.setCouponId(Long.valueOf(couponIdP[i]));
            if(registerMarketing.getId()==null){
                rc.setRegisterId(1L);
            }else {
                rc.setRegisterId(registerMarketing.getId());
            }
            rclist.add(rc);
        }
        registerMarketing.setRcList(rclist);
//        edit end 付陈林
        // 插入注册营销的信息
        marketingService.updateRegisterCoupon(registerMarketing);
        // 如果注册营销开关开启
        if (Integer.parseInt(registerMarketing.getIsUsed()) == 1) {
            // 判断积分是否为空
            if (registerMarketing.getRegisterIntegral() == null) {
                // 设置注册积分为0
                customerPointServiceMapper.updateIntegralById(0);
                // 换行
            } else {
                // 设置注册积分
                customerPointServiceMapper.updateIntegralById(registerMarketing
                        .getRegisterIntegral().intValue());
                // 换行
            }
            // 如果注册营销开关关闭
        } else if (Integer.parseInt(registerMarketing.getIsUsed()) == 0) {
            // 设置注册积分为0
            customerPointServiceMapper.updateIntegralById(0);
        }
        // 返回 重定向到“registerMarketing.htm”
        return new ModelAndView(new RedirectView("registerMarketing.htm"));
    }

}
