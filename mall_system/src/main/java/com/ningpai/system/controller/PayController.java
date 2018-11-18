/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.service.PayService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.PrintWriter;
import java.util.List;

/**
 * 支付方式设置控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月19日 上午9:43:10
 * @version 1.0
 */
@Controller
public class PayController {

    private static final MyLogger LOGGER = new MyLogger(PayController.class);

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private String page = "payset.htm";

    private String wxpage = "paywx.htm";

    private static final String NETLOGO = "netLogo";

    private static final String LOGGERINFO1 = ",用户名:";
    
    private static final String LOGGERINFO2 = "修改支付问题描述错误：=>";

    @Resource(name = "payService")
    private PayService payService;

    /**
     * 分页查询支付方式信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/payset")
    public ModelAndView paySet(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/system/payset", "pb", payService.findByPageBean(pb, selectBean));
    }

    /**
     * 分页查询支付方式信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/paywx")
    public ModelAndView paywx(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/system/wxset", "pb", payService.findPayByPayType(pb, selectBean));
    }

    /**
     * 添加支付方式信息
     * 
     * @param request
     * @param response
     * @param pay
     * @return ModelAndView
     */
    @RequestMapping("/addpay")
    public ModelAndView addPay(MultipartHttpServletRequest request, HttpServletResponse response, @Valid Pay pay, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(page));
        }
        try {
            // 待文件上传区
            MultipartFile file = request.getFile(NETLOGO);
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                pay.setPayImage(UploadUtil.uploadFileOne(file, request));
            }
            payService.insertPay(pay);

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "添加支付接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("添加支付接口设置错误：=>", e);
        }

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除支付方式信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/delpay")
    public ModelAndView deletePay(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pr = null;

        try {

            pr = response.getWriter();
            pr.print(payService.deletePay(request.getParameterValues("payIds[]")));

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "删除支付接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("删除支付接口设置错误：=>", e);
            return new ModelAndView(new RedirectView(page));
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
        return null;
    }

    /**
     * ajax查询单条支付信息
     * 
     * @param payid
     * @return Pay
     */
    @RequestMapping("/findpayone")
    @ResponseBody
    public Pay findPayOne(Long payid) {

        return payService.findByPayId(payid);
    }

    /**
     * ajax查询所有支付接口信息
     *
     * @return List<Object>
     */
    @RequestMapping("/findpayAll")
    @ResponseBody
    public List<Object> findpayAll() {

        return payService.queryAllPaySet();
    }

    /**
     * 修改支付信息
     * 
     * @param request
     * @param response
     * @param pay
     * @return ModelAndView
     */
    @RequestMapping("/updatepay")
    public ModelAndView updatePay(MultipartHttpServletRequest request, HttpServletResponse response, @Valid Pay pay, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(page));
        }
        try {
            // 待文件上传区
            MultipartFile file = request.getFile(NETLOGO);
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                pay.setPayImage(UploadUtil.uploadFileOne(file, request));
            }
            payService.updatePay(pay);

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改支付接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("修改支付接口设置错误：=>", e);
        }
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 修改支付信息(移动版微信支付)
     * 
     * @param request
     * @param response
     * @param pay
     * @return ModelAndView
     */
    @RequestMapping("/updatePayForwx")
    public ModelAndView updatePayForwx(MultipartHttpServletRequest request, HttpServletResponse response, @Valid Pay pay, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(wxpage));
        }
        try {
            // 待文件上传区
            MultipartFile file = request.getFile(NETLOGO);
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                pay.setPayImage(UploadUtil.uploadFileOne(file, request));
            }
            payService.updatePay(pay);

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改支付接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("修改支付接口设置错误：=>", e);
        }
        return new ModelAndView(new RedirectView(wxpage));
    }

    /**
     * 修改支付接口启用状态
     * 
     * @param payId
     * @param request
     * @return
     */
    @RequestMapping("/updateUserdStatusForPay")
    public ModelAndView updateUserdStatusForPay(Long payId, HttpServletRequest request) {
        try {
            this.payService.updateUserdStatus(payId);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改支付接口启用状态", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            LOGGER.error("修改支付接口启用状态错误：=>", e);
        }
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 修改支付接口启用状态(移动版微信支付接口)
     * 
     * @param payId
     * @param request
     * @return
     */
    @RequestMapping("/upUserdStatusForwxPay")
    public ModelAndView upUserdStatusForwxPay(Long payId, HttpServletRequest request) {
        try {
            this.payService.updateUserdStatus(payId);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改支付接口启用状态", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            LOGGER.error("修改支付接口启用状态错误：=>", e);
        }
        return new ModelAndView(new RedirectView(wxpage));
    }

    /**
     * 修改默认支付接口
     * 
     * @param payId
     * @param request
     * @return
     */
    @RequestMapping("/changeDefaultForPay")
    public ModelAndView changeDefaultForPay(Long payId, HttpServletRequest request) {
        try {
            this.payService.changeDefault(payId);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改默认支付接口", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            LOGGER.error("修改默认支付接口错误：=>", e);
        }
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 根据主键删除单个支付接口
     * 
     * @param payId
     *            支付接口主键id
     * @return 支付接口列表页面
     */
    @RequestMapping("/deletePaySetOne")
    public ModelAndView deletePaySetOne(Long payId) {
        payService.deletePaySetById(payId);
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 批量删除支付接口
     * 
     * @param payIds
     *            支付接口主键id
     * @return 支付接口列表页面
     */
    @RequestMapping("/deletePaySetBatch")
    public ModelAndView deletePaySetBatch(Long[] payIds) {
        for (Long payId : payIds) {
            payService.deletePaySetById(payId);
        }
        return new ModelAndView(new RedirectView(page));
    }
    
    
    /**
     * 修改支付问题描述信息
     * @param request 请求
     * @param pay 支付对象
     * @param bindingResult
     * @return int
     */
    @RequestMapping("/updatepayhelp")
    @ResponseBody
    public int updatePayHelp(HttpServletRequest request, Pay pay){
        try {
            // 提交数据
            payService.updatePayHelp(pay);
            // 获得session中的用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加操作日志
            OperaLogUtil.addOperaLog(request, customerName, "修改支付问题描述", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("",e);
        }
        return 1;
    }
}
