/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.order.controller;

import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.groupon.service.GrouponService;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 抢购订单控制器
 * 
 * @Description 抢购订单控制器
 * @author Songhl
 * @since 2015年8月28日 14:49:10
 */
@Controller
public class MarketOrderController {

    /** 记录日志对象 */
    public static final MyLogger LOGGER = new MyLogger(MarketOrderController.class);

    public static final String OTYPE = "oType";

    /**
     * 团购抢购订单表
     */
    @Resource(name = "OrderViceService")
    private OrderViceService service;

    /**
     * 团购功能接口
     */
    @Resource(name = "GrouponService")
    private GrouponService grouponservice;

    /** 模板设置业务类 */
    private TempService tempService;

    /**
     * SERVICE-页面说明
     */
    private MegawizardService megawizardSerivce;

    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * 抢购订单列表
     * 
     * @param pb
     *            分页参数
     * @param date
     *            查询参数
     * @return
     */
    @RequestMapping("marketorders")
    public ModelAndView orderViceListByRush(HttpServletRequest request, PageBean pb, String date, String type) {
        Map<String, Object> paramMap;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav;

        // 检查用户是否登录
        if (checkLoginStatus(request)) {
            paramMap = new HashMap<String, Object>();
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            paramMap.put(CustomerConstantStr.DATE, date);
            paramMap.put(CustomerConstantStr.TYPE, type);
            paramMap.put(OTYPE, "1");
            pb.setUrl("customer/marketorders");
            resultMap.put(CustomerConstantStr.TYPE, type);
            resultMap.put(OTYPE, "1");
            resultMap.put(CustomerConstantStr.DATE, date);
            resultMap.put(CustomerConstantStr.PB, grouponservice.selectOrderList(paramMap, pb));
            mav = new ModelAndView("customer/newmarketorder").addAllObjects(resultMap);
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/marketorders.html");
        }
        // 跳转订单页面
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 团购订单列表
     * 
     * @param pb
     *            分页参数
     * @param oType
     *            查询参数
     * @return
     */
    @RequestMapping("marketordergrounp")
    public ModelAndView orderViceListByGroup(HttpServletRequest request, PageBean pb, String date, String type, String oType) {
        Map<String, Object> paramMap;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav;

        // 检查用户是否登录
        if (checkLoginStatus(request)) {
            paramMap = new HashMap<String, Object>();
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            paramMap.put(CustomerConstantStr.DATE, date);
            paramMap.put(CustomerConstantStr.TYPE, type);
            paramMap.put(OTYPE, "0");
            pb.setUrl("customer/marketordergrounp");
            resultMap.put(CustomerConstantStr.TYPE, type);
            resultMap.put(OTYPE, "0");
            resultMap.put(CustomerConstantStr.DATE, date);
            resultMap.put(CustomerConstantStr.PB, grouponservice.selectOrderList(paramMap, pb));
            mav = new ModelAndView("customer/newgrouponorder").addAllObjects(resultMap);
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/marketordergrounp.html");
        }
        // 跳转订单页面
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return
     */
    @RequestMapping("selectrushdetails")
    public ModelAndView selectDetails(Long orderId, HttpServletRequest request) {
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (checkLoginStatus(request)) {
            // 根据主键 获取订单信息
            OrderVice orderVice = service.selectDetails(orderId);
            // 非空验证 订单 单号
            if (null != orderVice.getOrderCode()) {
                LOGGER.info("获取订单【" + orderVice.getOrderCode() + "】详情");
            }
            mav = new ModelAndView("customer/marketdetails");
            resultMap.put("order", orderVice);
            resultMap.put("unpay", megawizardSerivce.selectByType(5, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            resultMap.put("pay", megawizardSerivce.selectByType(0, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            // resultMap.put("relations",queryContainerRelations(request,orderId));
            mav.addAllObjects(resultMap);
            return topAndBottomService.getTopAndBottom(mav);
        } else {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=customer/selectrushdetails-" + orderId + ".html"));
        }
    }

    /**
     * 确认收货
     * 
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("/comfirmofmarkertorder")
    public ModelAndView comfirmomarket(HttpServletRequest request, Long orderId, String fromUrl) {
        ModelAndView mav = null;

        // 检查用户是否登录
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        if (customerId == null) {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/giftorder.html");
        } else {
            int result = service.updateOrderViceByOrderId(orderId);
            // 根据主键获取订单对象
            OrderVice orderVice = service.selectDetails(orderId);
            // 非空判断 订单号 状态是否更改成功
            if (null != orderVice.getOrderCode() && 1 == result) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "更改订单状态",
                        "改订单状态-->确认收货-->订单号【" + orderVice.getOrderCode() + "】-->用户名：" + customerAllInfo.getCustomerUsername());
            }
            // 控制跳转
            if ("index".equals(fromUrl)) {// 首页
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + "/index.html"));
            } else if ("marketorders".equals(fromUrl)) {// 抢购
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + "/marketorders.html"));
            } else if ("marketordergrounp".equals(fromUrl)) {// 团购
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + "/marketordergrounp.html"));
            }
        }
        // 跳转订单页面
        return mav;
    }

    /**
     * 查看登录状态
     * 
     * @param request
     * @return
     */
    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("cust") == null && request.getSession().getAttribute("user") == null) {
            return false;
        }
        return true;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    /**
     * 获取头尾
     * 
     * @param topAndBottomService
     */
    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    /**
     * SERVICE-页面说明
     * 
     * @return
     */
    public MegawizardService getMegawizardSerivce() {
        return megawizardSerivce;
    }

    /**
     * SERVICE-页面说明
     * 
     * @param megawizardSerivce
     */
    @Resource(name = "MegawizardService")
    public void setMegawizardSerivce(MegawizardService megawizardSerivce) {
        this.megawizardSerivce = megawizardSerivce;
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

}
