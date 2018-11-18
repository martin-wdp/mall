/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.order.bean.BackOrder;
import com.ningpai.order.bean.BackOrderGeneral;
import com.ningpai.order.bean.BackOrderLog;
import com.ningpai.order.service.BackOrderLogService;
import com.ningpai.order.service.BackOrderService;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.order.service.OrderService;
import com.ningpai.third.order.util.OrderValueUtil;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * <p>退单Controller</p>
 * @author NINGPAI-LIH
 * @since 2014-5月20日
 * @version 2.0
 */
@Controller
public class ThirdBackOrderController {

    // 退单service
    private BackOrderService backOrderService;
    // 订单service
    private OrderService orderService;

    private static final String BACKORDERGENERAL = "backOrderGeneral";
    /**
     * 订单优惠劵
     */

    @Resource(name = "OrderCouponService")
    private OrderCouponService orderCouponService;
    // 退单操作日志
    @Resource(name = "BackOrderLogService")
    private BackOrderLogService backOrderLogService;
    /**
     * 查询换购单或者退单
     *
     * @param bkorder
     *            要查询的退单参数
     * @param pageBean
     *            分页参数
     * @param request
     * @param n
     *            所属位置
     * @param l
     *            所属位置
     * @param tabStatus
     *            5、全部退货单 0、已审核 1、审核通过 2、拒绝订单 3、已退款订单 4、退款完成
     * @return 退单页面
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/queryThirdBackOrderList")
    public ModelAndView queryBackOrderList(String startTime, String endTime, BackOrder bkorder,PageBean pageBean, HttpServletResponse response,HttpServletRequest request, String n, String l, String tabStatus) throws UnsupportedEncodingException {
        //post 提交中文转码
        if(null != bkorder.getShippingPerson()){
            String str = new String(bkorder.getShippingPerson().getBytes("ISO8859-1"), ConstantUtil.UTF);
            bkorder.setShippingPerson(str);
        }

        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果没有选中，则默认是全部退货单列表
        if (tabStatus != null && !"5".equals(tabStatus)) {
                bkorder.setBackCheck(tabStatus);
        }
        //设置商家ID
        bkorder.setBusinessId((Long) request.getSession().getAttribute("thirdId"));
        //退单信息列表
        map.put("pb", backOrderService.backOrderList(pageBean, bkorder, startTime, endTime));
        //装载退单信息
        map.put("bkorder", bkorder);
        // 设置目前选中的为 5、全部退货单 0、已审核 1、审核通过 2、拒绝订单 3、已退款订单 4、退款完成
        map.put("tabStatus", tabStatus);
        //设置跳转的路径
        pageBean.setUrl("queryThirdBackOrderList");
        try {
            return new ModelAndView(OrderValueUtil.THIRDBACKORDERLISTS, "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 更新退单状态
     * @param bkorder 退单信息
     * @return
     */
    @RequestMapping("/queryThirdBackOrderList2")
    public ModelAndView updateBackOrderList(BackOrder bkorder) {
        //设置要跳转的控制器
        return new ModelAndView("queryThirdBackOrderList.htm");
    }






    /**
     * 更新退单状态
     *
     * @param backOrder
     */
    @RequestMapping("/updateBackOrderStatus")
    public ModelAndView updateThirdBackOrderSta(BackOrder backOrder, HttpServletRequest request) {
        //操作人
        backOrder.setAuthorName(((Customer) request.getSession().getAttribute("cust")).getCustomerUsername());
        //店铺编号
        backOrder.setBusinessId((Long)request.getSession().getAttribute("thirdId"));

        //退款记录
        BackOrderLog backOrderLog=new BackOrderLog();
        //退款操作人
        backOrderLog.setBackLogPerson(backOrder.getAuthorName());
        //时间
        backOrderLog.setBackLogTime(new Date());
        //订单号
        backOrderLog.setBackOrderId(backOrder.getBackOrderId());
        //同意退款
        backOrderLog.setBackLogStatus("8");
        //记录退款记录
        backOrderLogService.insert(backOrderLog);
        //修改退单状态
        this.backOrderService.modifyThirdBackBeanCheck(backOrder);
        //查询退单详细
        BackOrder bOrder=backOrderService.selectBackOrderByBackOrderId(backOrder.getBackOrderId());
        if(bOrder.getOrderId()!=null){
            // 根据订单id查询到优惠劵劵码，修改优惠劵  返还库存
            orderCouponService.modifyCouponStatus(bOrder.getOrderId());
        }
        return new ModelAndView(new RedirectView("queryThirdBackOrderList.htm"));
    }

    /**
     * 查看退单详情
     *
     * @param backOrderId
     *            退单ID {@link Long}
     */
    @RequestMapping("/toExamBackOrder")
    public ModelAndView toExamBackOrder(Long backOrderId) {

        // 根据订单编号查找退单信息
        BackOrder bOrder =  this.backOrderService.detail(backOrderId);
        List imglist = new ArrayList();
        if(bOrder.getUploadDocuments() != null) {
            String[] imgs = bOrder.getUploadDocuments().split(",");
            for(int i = 0; i < imgs.length; i++) {
                imglist.add(imgs[i]);
            }
        }
        Long backOrderIdNew = Long.valueOf(backOrderId);
        // 查询物流
        BackOrderGeneral backOrderGeneral = backOrderService.queryBackOrderGeneral(backOrderIdNew);
        // 获取退单日志信息
        List<BackOrderLog> backOrderLogs = backOrderLogService.queryByBackId(bOrder.getBackOrderId());
        return new ModelAndView(OrderValueUtil.THIRDBACKORDERDETAIL)
                .addObject("backorder", bOrder)
                .addObject("imglist", imglist)
                .addObject("backOrderLogs",backOrderLogs)
                .addObject("order",orderService.payOrder(bOrder.getOrderId()))
                .addObject(BACKORDERGENERAL, backOrderGeneral);
    }

    public BackOrderService getBackOrderService() {
        return backOrderService;
    }

    @Resource(name = "BackOrderService")
    public void setBackOrderService(BackOrderService backOrderService) {
        this.backOrderService = backOrderService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


}
