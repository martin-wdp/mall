/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.service.OrderService;

/**
 * 首页数据统计
 * 
 * @author NINGPAI-LIH
 *
 */
@Controller
public class SearchCountByIndexController {

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "GoodsGroupService")
    private GoodsGroupService goodsGroupService;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "OrderService")
    private OrderService orderService;

    /**
     * 返回订单统计数据
     * 
     * @return
     */
    @RequestMapping("getindexordercount")
    @ResponseBody
    public Map<String, Object> getIndexOrderCount() {
        return orderService.getIndexOrderCount();
    }

    /**
     * 查询促销总数量
     * 
     * @return
     */
    @RequestMapping("/selectMarketingCount")
    @ResponseBody
    public Map<String, Object> selectMarketingCount() {
        return marketingService.selectMarketCount();
    }

    /**
     * 查询商品组合和商品预警的数量
     * 
     * @return
     */
    @RequestMapping("selectgoodscountindex")
    @ResponseBody
    public Map<String, Object> selectGoodsCountIndex() {
        return goodsGroupService.getIndexCount();
    }

    /**
     * 查询会员数量
     * 
     * @return
     */
    @RequestMapping("selectcustomercountindex")
    @ResponseBody
    public Map<String, Object> selectCustomerCountIndex() {
        return customerServiceMapper.getCustomerCount();
    }
}
