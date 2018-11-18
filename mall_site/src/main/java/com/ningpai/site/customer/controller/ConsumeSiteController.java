/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.customer.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.service.CustomerConsumeService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器-我的消费记录
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月14日下午2:10:30
 */
@Controller
public class ConsumeSiteController {
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ConsumeSiteController.class);

    /**
     * service依赖
     */
    private CustomerConsumeService customerConsumeService;

    /**
     * 会员服务接口
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * 查询会员消费记录列表
     * 
     * @param pb
     *            分页工具类
     * @param date
     *            时间标记 1：近三个月记录 2：三个月前记录
     * @return
     */
    @RequestMapping(value = "/queryMyConsume")
    public ModelAndView queryMyConsume(HttpServletRequest request, PageBean pb, Integer date) {
        ModelAndView mav = new ModelAndView();
        try {
            // 检查用户是否登录
            if (checkLoginStatus(request)) {
                Long customerId = (Long) request.getSession().getAttribute("customerId");
                // 根据主键 获取会员详细信息
                Customer customer = customerServiceInterface.queryCustomerById(customerId);
                // 非空验证 用户名
                if (null != customer.getCustomerUsername()) {
                    LOGGER.info("查询会员【" + customer.getCustomerUsername() + "】的消费记录");
                }
                mav.addObject("date", date);
                mav.addObject("totalNum", customerConsumeService.selectTotalNumByCid(customerId));
                pb.setList(null);
                mav.addObject("pb", customerConsumeService.queryAllConsumeByCid(pb, customerId, date));
                pb.setUrl("customer/consume/" + date);
                mav.setViewName("customer/newpurchasehistory");
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView("/login/redirect").addObject("url", "/customer/consume/");
            }
            // 跳转消费记录
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 验证用户是否登录
     * 
     * @param request
     * @return
     */
    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("customerId") == null) {
            return false;
        }
        return true;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public CustomerConsumeService getCustomerConsumeService() {
        return customerConsumeService;
    }

    @Resource(name = "siteCustomerConsumeService")
    public void setCustomerConsumeService(CustomerConsumeService customerConsumeService) {
        this.customerConsumeService = customerConsumeService;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

}
