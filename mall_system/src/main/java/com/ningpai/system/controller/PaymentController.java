/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ningpai.util.MenuSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.Payment;
import com.ningpai.system.service.PaymentService;
import com.ningpai.util.PageBean;

/**
 * @ClassName: PaymentController
 * @Description: 控制器-支付方式
 * @author Wanghy
 * @date 2014年10月13日 上午10:41:58
 */
@Controller
public class PaymentController {

    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);

    private static final String REDIRECT_PAYMENT = "queryPaymentByPb.htm";

    /** 用户名称 */
    public static final String NAME = "name";

    /** "operaPath" */
    public static final String OPERAPATH = "operaPath";

    @Resource(name = "PaymentService")
    private PaymentService paymentService;

    /**
     * 分页查看支付方式列表
     * 
     * @Title: queryPaymentByPb
     * @Description: 分页查看支付方式列表
     * @param pb
     * @return
     */
    @RequestMapping("/queryPaymentByPb")
    public ModelAndView queryPaymentByPb(PageBean pb, HttpServletRequest request) {
        MenuSession.sessionMenu(request);
        LOGGER.debug("==========分页查看支付方式列表==========");
        return new ModelAndView("jsp/system/payment/payment_list", "pb", paymentService.selectAllByPb(pb));
    }

    /**
     * 添加支付方式
     * 
     * @Title: createPayment
     * @Description: 添加支付方式
     * @param payment
     * @param request
     * @return
     */
    @RequestMapping("/createPayment")
    public ModelAndView createPayment(@Valid Payment payment, BindingResult bindingResult, HttpServletRequest request) {
        LOGGER.debug("==========添加支付方式==========");
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("支付方式字段错误！");
        }
        paymentService.createPayment(payment);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加支付方式", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT_PAYMENT));
    }

    /**
     * Ajax获取支付方式
     * 
     * @Title: ajaxGetPayment
     * @Description: Ajax获取支付方式
     * @param paymentId
     * @return
     */
    @RequestMapping("/ajaxGetPayment")
    @ResponseBody
    public Payment ajaxGetPayment(Long paymentId) {
        LOGGER.debug("==========ajax查看支付方式==========");
        return paymentService.getPayment(paymentId);
    }

    /**
     * 修改支付方式
     * 
     * @Title: updatePayment
     * @Description: 修改支付方式
     * @param payment
     * @param request
     * @return
     */
    @RequestMapping("/updatePayment")
    public ModelAndView updatePayment(Payment payment, HttpServletRequest request) {
        LOGGER.debug("==========修改支付方式==========");
        /*
         * if (bindingResult.hasErrors()) { throw new
         * RuntimeException("支付方式字段错误！"); }
         */
        paymentService.updatePayment(payment);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改支付方式", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT_PAYMENT));
    }

    /**
     * 删除支付方式
     * 
     * @Title: deletePayment
     * @Description: 删除支付方式
     * @param paymentId
     * @param request
     * @return
     */
    @RequestMapping("/deletePayment")
    public ModelAndView deletePayment(Long paymentId, HttpServletRequest request) {
        LOGGER.debug("==========删除支付方式==========");
        paymentService.deletePayment(paymentId);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除支付方式", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT_PAYMENT));
    }

    /**
     * 修改支付方式启用状态
     * 
     * @Title: updateOpenStatus
     * @Description: 修改支付方式启用状态
     * @param paymentId
     * @return
     */
    @RequestMapping("/updateOpenStatus")
    public ModelAndView updateOpenStatus(Long paymentId) {
        LOGGER.debug("==========ajax修改支付方式启用状态==========");
        paymentService.setPaymentOpenStatus(paymentId);
        return new ModelAndView(new RedirectView(REDIRECT_PAYMENT));
    }

    /**
     * ajax检查启用的支付方式数量
     * 
     * @Title: ajaxCheckOpenCount
     * @Description: ajax检查启用的支付方式数量
     * @return
     */
    @RequestMapping("/ajaxCheckOpenCount")
    @ResponseBody
    public boolean ajaxCheckOpenCount(Long paymentId) {
        LOGGER.debug("==========ajax检查启用的支付方式数量==========");
        return paymentService.checkOpenCount(paymentId);
    }

    /**
     * ajax检查支付方式能否删除
     * 
     * @Title: ajaxCheckDelete
     * @Description: ajax检查支付方式能否删除
     * @param paymentId
     * @return
     */
    @RequestMapping("/ajaxCheckDelete")
    @ResponseBody
    public boolean ajaxCheckDelete(Long paymentId) {
        LOGGER.debug("==========ajax检查支付方式能否删除==========");
        return paymentService.checkDelete(paymentId);
    }

}
