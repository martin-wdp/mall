/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.order.bean.BarterOrder;
import com.ningpai.order.service.BarterService;
import com.ningpai.util.OrderConstants;
import com.ningpai.util.PageBean;

/**
 * 换单控制层
 * 
 * @author YANhb
 * 
 */
@Controller
public class BarterOrderController {

    /**
     * 注入换单业务层
     */
    @Resource
    private BarterService barterService;

    /**
     * 换单分页查询
     * 
     * @param pageBean
     *            分页工具类
     * @param barterOrder
     *            换单实体类
     * @return ModelAndView
     */
    @RequestMapping("/barterPageSize")
    public ModelAndView barterOrderPageSize(PageBean pageBean,
            BarterOrder barterOrder, String startTime, String endTime) {
        // 查询换单列表
        return new ModelAndView("jsp/order/barterorderlist").addObject(
                "pageBean", barterService.queryBarterPageSize(pageBean,
                        barterOrder, startTime, endTime));
    }

    /**
     * 修改换货单的审核状态
     * 
     * @param barterOrder
     *            换单实体类
     * @return ModelAndView
     */
    @RequestMapping(value = "/modifyBarterOrder")
    public ModelAndView modifyBarterByKey(BarterOrder barterOrder) {
        // 修改状态
        barterService.modifyBarterCheck(barterOrder);
        return new ModelAndView(new RedirectView("barterPageSize.htm"));
    }

    /**
     * 
     * @param barterOrderId
     *            换货ID号
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping(value = "/queryBarterDetails")
    public ModelAndView queryBarterDetails(Long barterOrderId) {
        // 查询换单详细
        return new ModelAndView(OrderConstants.BARTERORDERDETAIL).addObject(
                "barter", barterService.queryBarterDetails(barterOrderId));
    }
}
