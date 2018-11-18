/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.customer.bean.Customer;
import com.ningpai.order.bean.BarterOrder;
import com.ningpai.order.service.BarterService;
import com.ningpai.third.order.util.OrderValueUtil;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;

/**
 * 换货单Controller
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月22日 09:55:46
 */
@Controller
public class ThirdBarterOrderController {

    // 换货单服务类
    private BarterService barterService;

    /**
     * 退换单列表
     * 
     * @param n
     *            所属位置
     * @param l
     *            所属位置
     * @param pb
     *            分页参数实体类
     * @param barterOrder
     *            查询条件
     * @param request
     * @param tabStatus
     *            0、未审核 1、已审核 2、已拒绝 3、已发货 4、已完成
     * @return 换单页面
     */
    @RequestMapping("/queryThirdBarterLists")
    public ModelAndView queryBarterLists(String startTime, String endTime, String n, String l, PageBean pb, String tabStatus, BarterOrder barterOrder, HttpServletRequest request) {
        //填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Map<String, Object> map = new HashMap<String, Object>();
        //商家ID
        barterOrder.setBusinessId((Long) request.getSession().getAttribute("thirdId"));
        // 设置第三方店铺签约id
        barterOrder.setBusinessId((Long) request.getSession().getAttribute("thirdId"));
        // 判断是否是选中全部
        if (tabStatus != null && !"5".equals(tabStatus)) {
                // 如果不是选中，设置查询条件
                barterOrder.setBarterCheck(tabStatus);
        }
        //根据条件筛选退换单列表数据
        map.put("pb", barterService.queryBarterPageSize(pb, barterOrder, startTime, endTime));
        //换单对象
        map.put("barterOrder", barterOrder);
        //换单状态
        map.put("tabStatus", tabStatus);
        //开始时间
        map.put("startTime", startTime);
        //结束时间
        map.put("endTime", endTime);
        //重定到退换单列表
        return new ModelAndView(OrderValueUtil.QUERYBARTERLISTS, "map", map);
    }

    /**
     * 去审核换单信息
     * 
     * @param barkerOrderId
     *            换单ID {@link Long}
     * @return 换单页面
     */
    @RequestMapping("/toExamBarkerOrder")
    public ModelAndView toExamBarkerOrder(Long barkerOrderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("barterOrder", this.barterService.queryBarterDetails(barkerOrderId));
            return new ModelAndView(OrderValueUtil.TOEXAMBARKERORDER, "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 更新换单状态
     * 
     * @param barterOrder
     *            换单实体 {@link BarterOrder}
     * @return 换单页面
     */
    @RequestMapping("/updateBarkerOrderSta")
    public ModelAndView updateBarkerOrderSta(BarterOrder barterOrder, HttpServletRequest request) {
        barterOrder.setAuthorName(((Customer) request.getSession().getAttribute("cust")).getCustomerUsername());
        this.barterService.modifyBarterCheck(barterOrder);
        return new ModelAndView(new RedirectView(OrderValueUtil.QUERYTHIRDBARTERLISTS));
    }

    public BarterService getBarterService() {
        return barterService;
    }

    @Resource(name = "barterService")
    public void setBarterService(BarterService barterService) {
        this.barterService = barterService;
    }

}
