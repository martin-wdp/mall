/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logcore.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.gift.bean.GiftOrder;
import com.ningpai.gift.service.GiftOrderService;
import com.ningpai.gift.vo.GiftOrderVo;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.util.PageBean;

/**
 * 后台积分订单控制器
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class GiftOrderController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GiftOrderController.class);

    private GiftOrderService orderService;

    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz iLogisticsCompanyBiz;

    /**
     * 积分订单
     * 
     * @param pageBean
     * @param orderVo
     * @param request
     * @return
     */
    @RequestMapping("/giftorderlist")
    public ModelAndView giftOrderList(PageBean pageBean, GiftOrderVo orderVo, HttpServletRequest request) {
        pageBean.setUrl("giftorderlist.htm");

        return new ModelAndView("jsp/gift/giftorderlist").addObject("pageBean", orderService.queryGiftOrder(orderVo, pageBean)).addObject("order", orderVo)

        .addObject("expressList", iLogisticsCompanyBiz.queryAllLogisticsCompany())
                .addObject(ConstantUtil.CSRFTOKEN, request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * ajax得到商品订单详情
     * 
     * @param giftOrderId
     * @return
     */
    @RequestMapping("/ajaxSelectGiftDetail")
    @ResponseBody
    public GiftOrderVo ajaxSelectGiftDetail(Long giftOrderId) {
        return orderService.orderDetail(giftOrderId);
    }

    /**
     * 修改订单信息
     * 
     * @param orderVice
     *            要修改的订单信息
     * @param url
     *            跳转地址
     * @return
     */
    @RequestMapping("/updategiftorder")
    public ModelAndView updateGiftOrder(HttpServletRequest request, GiftOrder order, String url) {
        order.setGiftOrderStatus("1");
        // 修改积分兑换赠品的订单信息
        if (1 == orderService.updateOrderVice(order) && null != order.getGiftOrderCode()) {
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "修改订单信息",
                        (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + "-->订单号【" + order.getGiftOrderCode() + "】,用户名："
                                + (String) request.getSession().getAttribute(ValueUtil.NAME));
                LOGGER.info("修改单号为：" + order.getGiftOrderCode() + "积分兑换赠品的订单信息");
        }
        return new ModelAndView(new RedirectView(url));
    }

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return
     */
    @RequestMapping("/selectgiftdetails")
    public ModelAndView selectGiftDetails(Long giftOrderId) {
        GiftOrderVo giftOrderVo = orderService.orderDetail(giftOrderId);
        // 非空验证 订单名称
        if (null != giftOrderVo.getGiftName()) {
            LOGGER.info("查询单号为：" + giftOrderVo.getGiftName() + "积分兑换赠品订单详情");
        }
        return new ModelAndView("jsp/gift/giftorderdetail").addObject("order", giftOrderVo);
    }

    @Resource(name = "GiftOrderWebService")
    public void setOrderService(GiftOrderService orderService) {
        this.orderService = orderService;
    }

}
