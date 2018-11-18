/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.service.GoodsLackRegisterService;
import com.ningpai.goods.util.LackRegisterSearchBean;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 商品通知控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午11:52:33
 * @version 1.0
 */
@Controller
public class GoodsLackRegisterController {
    // 商品通知Service
    private GoodsLackRegisterService goodsLackRegisterService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsLackRegisterController.class);

    public GoodsLackRegisterService getGoodsLackRegisterService() {
        return goodsLackRegisterService;
    }

    @Resource(name = "GoodsLackRegisterService")
    public void setGoodsLackRegisterService(
            GoodsLackRegisterService goodsLackRegisterService) {
        this.goodsLackRegisterService = goodsLackRegisterService;
    }

    /**
     * 根据分页参数查询通知列表的控制器
     * 
     * @param pb
     *            {@link com.ningpai.goods.util.PageBean}
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/findAllLackRegister")
    public ModelAndView findAllLackRegister(PageBean pb) {
        // 打印日志
        LOGGER.info(ValueUtil.FINDALLLACKREGISTERINFO);
        // 设置PageBean的url路径
        pb.setUrl(PathUtil.ALLLACKREGISTERCONTROLLER);
        // 返回结果
        return new ModelAndView(PathUtil.GOODSLACKREGISTER, "pb",
                this.goodsLackRegisterService.queryByPageBean(pb));
    }

    /**
     * 批量发送到货通知
     * 
     * @param lackCheck
     *            到货通知的ID数组 {@link java.lang.Long}
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchNotice")
    public ModelAndView batchNotice(Long[] lackCheck, HttpServletRequest request) {
        // 执行批量进行到货通知方法
        this.goodsLackRegisterService.updateLackRegisterStatus(lackCheck);
        // 打印日志
        LOGGER.info(ValueUtil.BATCHNOTICEINFO);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.BATCHNOTICEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                        + ",用户名："
                        + (String) request.getSession().getAttribute(
                                ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(
                PathUtil.ALLLACKREGISTERCONTROLLER + ValueUtil.TOKENPARAM
                        + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据参数查询
     * 
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/queryLackRegisterByParam")
    public ModelAndView queryByParam(PageBean pb,
            LackRegisterSearchBean searchBean) {
        // 设置PageBean的url路径
        pb.setUrl("queryLackRegisterByParam.htm");
        // 打印日志
        LOGGER.info(ValueUtil.QUERYLACKREGISTERBYPARAMINFO);
        // 参数设置到作用域中
        return new ModelAndView(PathUtil.GOODSLACKREGISTER, "pb",
                this.goodsLackRegisterService.queryByPageBeanAndSearchBean(pb,
                        searchBean)).addObject("searchBean", searchBean);
    }

    /**
     * 批量删除到货通知
     */
    @RequestMapping("/batchDelLack")
    public ModelAndView batchDelLack(Long[] lackCheck,
            HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELLACKINFO);
        // 调用批量删除到货通知方法
        this.goodsLackRegisterService.batchDel(lackCheck);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.BATCHDELLACKINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                        + ",用户名："
                        + (String) request.getSession().getAttribute(
                                ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(
                PathUtil.ALLLACKREGISTERCONTROLLER + ValueUtil.TOKENPARAM
                        + CSRFTokenManager.getTokenFromRequest(request)));
    }

}
