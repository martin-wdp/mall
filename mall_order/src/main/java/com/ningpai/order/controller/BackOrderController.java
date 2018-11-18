/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.controller;

import com.ningpai.order.bean.*;
import com.ningpai.order.service.*;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.salesman.bean.Salesman;
import com.ningpai.salesman.service.SalesmanService;
import com.ningpai.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/***
 * 
 * @author zhanghailong
 *
 */
@Controller
public class BackOrderController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(BackOrderController.class);

    private static final String BACKORDERLIST_HTM = "backorderlist.htm";
    private static final String BACKORDER = "backorder";
    private static final String ORDERLOGS = "orderLogs";
    private static final String ORDERID = "orderId";
    private static final String BACKORDERLOGS = "backOrderLogs";
    private static final String BACKORDERGENERAL = "backOrderGeneral";
    private static final String ADMIN = "admin";

    private BackOrderService backOrderService;

    private OrderService orderService;

    // 退单操作日志
    private BackOrderLogService backOrderLogService;

    // 订单操作记录
    private OrderLogService orderLogService;
    /**
     * 订单优惠劵
     */

    @Resource(name = "OrderCouponService")
    private OrderCouponService orderCouponService;

    @Resource(name = "salesmanService")
    private SalesmanService salesmanService;
    /**
     * boss -订单列表-退单列表
     * 
     * @param bkorder
     *            退单信息
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/backorderlist")
    public ModelAndView backOrderList(HttpServletRequest request, BackOrder bkorder, PageBean pageBean, String startTime, String endTime) {
        // 查询所有平台的订单
        bkorder.setBusinessId(0L);
        MenuSession.sessionMenu(request);
        // 查询退单列表
        pageBean.setUrl(OrderConstants.INITBACKORDER);
        //查询所有可以关联的业务员
        List<Salesman> salesmans = salesmanService.seletAllEnableSala();
        return new ModelAndView(OrderConstants.BACKORDERLIST, "pageBean", backOrderService.backOrderList(pageBean, bkorder, startTime, endTime)).addObject("businessId", 0).addObject("enableSalaList", salesmans);
    }

    /**
     * boss 商铺订单-退单列表
     * 
     * @param bkorder
     *            退单信息
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/backorderlististhird")
    public ModelAndView backOrderLististhird(HttpServletRequest request,String status, BackOrder bkorder, PageBean pageBean, String startTime, String endTime) {
        String statusNew = status;
        if (StringUtils.isEmpty(statusNew)) {
            statusNew = "1";
        }
        pageBean.setUrl(OrderConstants.INITBACKORDERISTHIRD);
        return new ModelAndView(OrderConstants.BACKORDERLIST, "pageBean", backOrderService.backOrderList(pageBean, bkorder, startTime, endTime)).addObject("businessId", 1);
    }

    /**
     * 修改退单信息的审核状态
     * 
     * @param backOrder
     * @return
     */
    @RequestMapping(value = "modifyBackOrder")
    public ModelAndView modifyBackOrderByCheck(BackOrder backOrder, HttpServletRequest request) {
        // 操作日志
        orderLogService.insertSelective(null, backOrder.getOrderId(), request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "5");
        // 修改订单状态
        int count = backOrderService.modifyBackBeanCheck(backOrder);
        if (count > 0) {
            return new ModelAndView(new RedirectView(BACKORDERLIST_HTM));
        } else {
            return null;
        }
    }

    /**
     * 修改退单信息的审核状态
     * 
     * @param backOrder
     * @return
     */
    @RequestMapping(value = "modifyBackOrderisthird")
    public ModelAndView modifyBackOrderByCheckisthird(BackOrder backOrder, HttpServletRequest request) {
        // 操作日志
        orderLogService.insertSelective(null, backOrder.getOrderId(), request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "5");
        // 修改状态
        int count = backOrderService.modifyBackBeanCheck(backOrder);
        if (count > 0) {
            return new ModelAndView(new RedirectView("backorderlististhird.htm"));
        } else {
            return null;
        }
    }

    /**
     * 新后台退单详情
     * 
     * @param backId
     * @param orderId
     * @return
     */
    @RequestMapping("/newbackdetail")
    public ModelAndView newBackDetail(String backId, String orderId) {
        // 获取退单日志信息
        List<BackOrderLog> backOrderLogs = backOrderLogService.queryByBackId(Long.valueOf(backId));
        // 查询退单金额
        // Order order = orderService.getPayOrder(Long.valueOf(orderId));
        // 查询物流
        BackOrderGeneral backOrderGeneral = backOrderService.queryBackOrderGeneral(Long.valueOf(backId));
        Order order = orderService.getPayOrder(Long.valueOf(orderId));
        return new ModelAndView("jsp/order/backorderdetail")
        // 查询明细
                .addObject(BACKORDER, backOrderService.backdetail(Long.valueOf(backId), order))
                // 查询日志
                .addObject(ORDERLOGS, orderLogService.selectOrderLogByOrderId(Long.valueOf(orderId))).addObject(ORDERID, orderId).addObject(BACKORDERLOGS, backOrderLogs)
                // .addObject("backPrice",order.getBackPrice())
                .addObject(BACKORDERGENERAL, backOrderGeneral);
    }

    /**
     * 新后台退款详情
     * 
     * @param backId
     * @param orderId
     * @return
     */
    @RequestMapping("/newbackprice")
    public ModelAndView newbackprice(String backId, String orderId) {
        // 获取退单日志信息
        List<BackOrderLog> backOrderLogs = backOrderLogService.queryByBackId(Long.valueOf(backId));
        // 查询退单金额
        // Order order = orderService.getPayOrder(Long.valueOf(orderId));
        // 查询物流
        BackOrderGeneral backOrderGeneral = backOrderService.queryBackOrderGeneral(Long.valueOf(backId));
        Order order = orderService.getPayOrder(Long.valueOf(orderId));
        return new ModelAndView("jsp/order/backorderprice")
        // 查询详细
                .addObject(BACKORDER, backOrderService.backdetail(Long.valueOf(backId), order))
                // 查询日志
                .addObject(ORDERLOGS, orderLogService.selectOrderLogByOrderId(Long.valueOf(orderId))).addObject(ORDERID, orderId).addObject(BACKORDERLOGS, backOrderLogs)
                // .addObject("backPrice",order.getBackPrice())
                .addObject(BACKORDERGENERAL, backOrderGeneral);
    }

    /**
     * 退单详细信息
     * 
     * @param backOrderId
     * @return ModelAndView
     */
    @RequestMapping("/backorderdetail")
    public ModelAndView backOrderDetail(Long backOrderId, Long orderId) {
        // 获取退单日志信息
        List<BackOrderLog> backOrderLogs = backOrderLogService.queryByBackId(backOrderId);
        // 查询退单金额
        Order order = orderService.getPayOrder(orderId);
        // 查询物流
        BackOrderGeneral backOrderGeneral = backOrderService.queryBackOrderGeneral(backOrderId);
        return new ModelAndView(OrderConstants.NEWBACKORDERDETAIL)
                // 查询详细
                .addObject(BACKORDER, backOrderService.backdetail(backOrderId, order))
                // 查询日志
                .addObject(ORDERLOGS, orderLogService.selectOrderLogByOrderId(orderId)).addObject(ORDERID, orderId).addObject(BACKORDERLOGS, backOrderLogs)
                .addObject("backPrice", order.getBackPrice()).addObject(BACKORDERGENERAL, backOrderGeneral);
    }

    /**
     * 退单详细信息
     *
     * @param backOrderId
     * @return ModelAndView
     */
    @RequestMapping("/backorderdetailajax")
    @ResponseBody
    public BackOrder backOrderDetailajax(Long backOrderId) {
        return backOrderService.detail(backOrderId);
    }

    /**
     * 异步查询退单日志
     *
     * @param backOrderId
     * @return ModelAndView
     */
    @RequestMapping("/backorderlog")
    @ResponseBody
    public List<BackOrderLog> backorderlog(Long backOrderId) {
        return backOrderLogService.queryByBackId(backOrderId);
    }

    /**
     * 申请退单时上传图片
     * 
     * @param request
     * @param resp
     */
    @RequestMapping("back/upload")
    public void uploadBackImg(MultipartHttpServletRequest request, HttpServletResponse resp, Long orderId) throws IOException {
        PrintWriter out = resp.getWriter();
        String msg = null;
        MultipartFile file = request.getFile("uploadDocument");
        file.getOriginalFilename();
        // 检查文件大小
        if (file.getSize() > CustomerConstantStr.FOUR * CustomerConstantStr.NUM1024 * CustomerConstantStr.NUM1024) {
            msg = "101";
        } else if (!checkExtendsName(file.getOriginalFilename())) {
            msg = "102";
        } else {
            msg = UploadUtil.uploadFileByWidth(request.getFile("uploadDocument"), request).get("oldimg") + "," + orderId;
        }
        out.append("<script>parent.callback('" + msg + "');</script>");
    }

    /**
     * 记录退款申请信息
     * 
     * @param backOrder
     * @return
     */
    @RequestMapping("back/keepBackOrderInfoprice")
    public ModelAndView keepBackOrderInfoprice(HttpServletRequest request, BackOrder backOrder) {
        // 记录退单信息到退单表
        Order order = orderService.getPayOrder(backOrder.getOrderId());
        backOrder.setBusinessId(order.getBusinessId());
        backOrder.setSalesmanId(order.getSalesmanId());
        backOrder.setBackOrderCode(this.createBackOrderNo());
        backOrder.setBackCheck("6");
        backOrder.setBackTime(new Date());
        int count = backOrderService.insertBackOrderInfo(backOrder);
        // 记录订单商品退货信息
        // 更新订单状态为15("已提交退款审核")
        orderService.updateStatusBackById(backOrder.getOrderId(), "15", null);
        // 记录退单操作日志
        if (count > 0) {
            BackOrderLog backOrderLog = new BackOrderLog();
            backOrderLog.setBackLogPerson("customer");
            backOrderLog.setBackLogTime(new Date());
            backOrderLog.setBackLogStatus("1");
            backOrderLog.setBackOrderId(backOrder.getBackOrderId());
            backOrderLogService.insert(backOrderLog);
        }
        return new ModelAndView(new RedirectView("../customer/myorder.html"));
    }

    /**
     * 记录退货申请信息
     * 
     * @param backOrder
     * @return
     */
    @RequestMapping("back/keepBackOrderInfo")
    public ModelAndView keepBackOrderInfo(HttpServletRequest request, BackOrder backOrder) {
        // 记录退单信息到退单表
        backOrder.setBusinessId(orderService.getPayOrder(backOrder.getOrderId()).getBusinessId());
        backOrder.setBackOrderCode(this.createBackOrderNo());
        backOrder.setBackCheck("0");
        backOrder.setBackTime(new Date());
        //获取订单信息
        Order order=orderService.getPayOrder(backOrder.getOrderId());
        if(!order.getOrderPrePrice().toString().equals("0.00")){
            //计算去除运费的金额
            order.setBackPrice(order.getOrderPrice().subtract(order.getExpressPrice()));
            //获取订单商品
            List<OrderGoods> og=orderService.queryOrderGoods(order.getOrderId());
            StringBuffer goodsInfo=new StringBuffer();
            //拼接商品信息
            for (int i = 0; i < og.size() ; i++) {
                if(i>0){
                    goodsInfo.append("-");
                }
                //记录订单商品id以及数量
                goodsInfo.append(og.get(i).getGoodsInfoId()+","+og.get(i).getGoodsInfoNum());
            }
            //设置退货的商品id和数量
            backOrder.setBackGoodsIdAndSum(goodsInfo.toString());
        }
        int count = backOrderService.insertBackOrderInfo(backOrder);
        // 记录订单商品退货信息

        /*
         * 这里更改退单商品的状态 改为平台同意退单之后才做更改 String[] goodsInfoIds =
         * request.getParameterValues("goodsInfoIds"); if(goodsInfoIds != null)
         * { for(int i = 0; i < goodsInfoIds.length; i++) { // 更新订单货品表 标记为退单货品
         * int num =
         * orderService.updateOrderGoodsBack(Long.valueOf(goodsInfoIds[
         * i]),backOrder.getOrderId(),backOrder.getBackOrderCode()); } }
         */
        // 更新订单状态为14("已提交退货审核")
        orderService.updateStatusBackById(backOrder.getOrderId(), "14", null);
        // 记录退单操作日志
        if (count > 0) {
            BackOrderLog backOrderLog = new BackOrderLog();
            backOrderLog.setBackLogPerson("customer");
            backOrderLog.setBackLogTime(new Date());
            backOrderLog.setBackLogStatus("1");
            backOrderLog.setBackOrderId(backOrder.getBackOrderId());
            // backOrderLog.setBackOrderId(backOrderService.queryBackOrderByOrderCode(orderService.orderDetail(backOrder.getOrderId()).getOrderCode()).getBackOrderId());
            backOrderLogService.insert(backOrderLog);
        }
        return new ModelAndView(new RedirectView("../customer/myorder.html"));
    }

    /**
     * 退单操作控制器
     * 
     * @return
     */
    @RequestMapping("savebackorderdetail")
    public ModelAndView saveBackOrderDetail(HttpServletRequest request, HttpServletResponse response, BackOrderLog backOrderLog, Long orderId, BigDecimal backPrice) {
        backOrderLog.setBackLogPerson(ADMIN);
        backOrderLog.setBackLogTime(new Date());
        backOrderLogService.insert(backOrderLog);
        Order order = orderService.getPayOrder(orderId);
        BackOrder backOrder = backOrderService.queryBackOrderByOrderCode(order,request);
        if (Long.valueOf(backOrderLog.getBackLogStatus()) == 2) {
            if (Long.valueOf(backOrder.getIsBack()) == 1) {
                // 如果退单操作日志为 admin 审核通过，则改变订单状态为 8 同意退货
                orderService.updateStatusBackById(orderId, "8", backPrice);
                // 改变退单列表状态为 9 待客户填写物流地址
                backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "9");
            }
            if (Long.valueOf(backOrder.getIsBack()) == 2) {
                // 如果退单操作日志为 admin 审核通过，则改变订单状态为 12 同意退款
                orderService.updateStatusBackById(orderId, "12", backPrice);
                // 改变退单列表状态为 3 确认收货
                backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "3");
            }
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 3) {
            // 如果退单操作日志为 admin 审核不通过，则改变订单状态为 9 拒绝退货
            orderService.updateStatusBackById(orderId, "9", backPrice);
            // 改变退单列表状态为 2 拒绝退货
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "2");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 5) {
            // 如果退单操作日志为 admin 确认收货,则改变订单状态为 10 确认收货
            orderService.updateStatusBackById(orderId, "10", backPrice);
            // 改变退单列表状态为 3 确认收货
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "3");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 6) {
            // 如果退单操作日志为 admin 收货失败，则改变订单状态为 16 收货失败
            orderService.updateStatusBackById(orderId, "16", backPrice);
            // 改变退单列表状态为 8 收货失败
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "8");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 7) {
            // 如果退单操作日志为 admin 退款，则改变订单状态为 17 已退款 并记录退款金额
            orderService.updateStatusBackById(orderId, "17", backPrice);
            // 改变退单列表状态为 4 退单结束
            int cont = backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "4");
            // 退单结束后把成功消费的积分奖励扣除掉,增加取消订单的积分记录,以及消费记录
            if (cont == 1) {
                // 退款成功后扣积分
                backOrderService.reducePointOrderBack(orderId);
                // 退款成功后退回库存
                backOrderService.addStockOrderBack(orderId, backOrderLog.getBackOrderId());
            }
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append("<script>parent.call_back();</script>");
        } catch (IOException e) {
            LOGGER.error("新增一条退货操作日志",e);
        }
        return null;
    }

    /**
     * 新后台保存退款详情信息
     * 
     * @param request
     * @param response
     * @param backOrderLog
     * @param orderId
     * @param backPrice
     * @return
     */
    @RequestMapping("/newsavebackorderprice")
    public ModelAndView newsaveBackOrderprice(HttpServletRequest request, HttpServletResponse response, BackOrderLog backOrderLog, Long orderId, BigDecimal backPrice) {
        backOrderLog.setBackLogPerson(ADMIN);
        backOrderLog.setBackLogTime(new Date());
        backOrderLogService.insert(backOrderLog);
        Order order = orderService.getPayOrder(orderId);
        BackOrder backOrder = backOrderService.queryBackOrderByOrderCode(order,request);
        if (Long.valueOf(backOrderLog.getBackLogStatus()) == 2) {
            if (Long.valueOf(backOrder.getIsBack()) == 1) {
                // 如果退单操作日志为 admin 审核通过，则改变订单状态为 8 同意退货
                orderService.updateStatusBackById(orderId, "8", backPrice);
                // 改变退单列表状态为 9 待客户填写物流地址
                backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "9");
            }
            if (Long.valueOf(backOrder.getIsBack()) == 2) {
                // 如果退单操作日志为 admin 审核通过，则改变订单状态为 12 同意退款
                orderService.updateStatusBackById(orderId, "12", backPrice);
                // 改变退单列表状态为 3 确认收货
                backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "3");
            }
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 3) {
            // 如果退单操作日志为 admin 审核不通过，则改变订单状态为 9 拒绝退货
            orderService.updateStatusBackById(orderId, "9", backPrice);
            // 改变退单列表状态为 2 拒绝退货
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "2");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 5) {
            // 如果退单操作日志为 admin 确认收货,则改变订单状态为 10 确认收货
            orderService.updateStatusBackById(orderId, "10", backPrice);
            // 改变退单列表状态为 3 确认收货
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "3");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 6) {
            // 如果退单操作日志为 admin 收货失败，则改变订单状态为 16 收货失败
            orderService.updateStatusBackById(orderId, "16", backPrice);
            // 改变退单列表状态为 8 收货失败
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "8");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 7 || Long.valueOf(backOrderLog.getBackLogStatus()) == 8) {
            // 如果退单操作日志为 admin 退款，则改变订单状态为 17 已退款 并记录退款金额
            orderService.updateStatusBackById(orderId, "17", backPrice);
            // 改变退单列表状态为 4 退单结束
            int cont = backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "4");
            // 退单结束后把成功消费的积分奖励扣除掉,增加取消订单的积分记录,以及消费记录
            if (cont == 1) {
                backOrderService.reducePointOrderBack(orderId);
            }
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 9) {
            // 如果退单操作日志为 admin 退款，则改变订单状态为 13 拒绝退款
            orderService.updateStatusBackById(orderId, "13", backPrice);
            // 改变退单列表状态为 4 退单结束
            int cont = backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "7");
            if (cont == 1) {
                backOrderService.reducePointOrderBack(orderId);
            }
        }
        return new ModelAndView(new RedirectView(BACKORDERLIST_HTM));
    }

    /**
     * 新后台保存退货详情信息
     * 
     * @param request
     * @param response
     * @param backOrderLog
     * @param orderId
     * @param backPrice
     * @return
     */
    @RequestMapping("/newsavebackorderdetail")
    public ModelAndView newsaveBackOrderDetail(HttpServletRequest request, HttpServletResponse response, BackOrderLog backOrderLog, Long orderId, BigDecimal backPrice) {
        BigDecimal backPriceNew = backPrice;
        backOrderLog.setBackLogPerson(ADMIN);
        backOrderLog.setBackLogTime(new Date());
        backOrderLogService.insert(backOrderLog);
        Order order = orderService.getPayOrder(orderId);
        if(backPriceNew==null){
            /**
             * add by 付陈林 2015年12月11日
             * 退款金额=订单金额-减去优惠金额。
             * */
//            backPriceNew=order.getOrderPrice();
              backPriceNew=order.getOrderPrice().subtract(order.getOrderPrePrice());
//            edit end

        }
        BackOrder backOrder = backOrderService.queryBackOrderByOrderCode(order,request);
        if (Long.valueOf(backOrderLog.getBackLogStatus()) == 2) {
            if (Long.valueOf(backOrder.getIsBack()) == 1) {
                // 如果退单操作日志为 admin 审核通过，则改变订单状态为 8 同意退货
                orderService.updateStatusBackById(orderId, "8", backPriceNew);
                // 改变退单列表状态为 9 待客户填写物流地址
                backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "9");
            }
            if (Long.valueOf(backOrder.getIsBack()) == 2) {
                // 如果退单操作日志为 admin 审核通过，则改变订单状态为 12 同意退款
                orderService.updateStatusBackById(orderId, "12", backPriceNew);
                // 改变退单列表状态为 3 确认收货
                backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "3");
            }
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 3) {
            // 如果退单操作日志为 admin 审核不通过，则改变订单状态为 9 拒绝退货
            orderService.updateStatusBackById(orderId, "9", backPriceNew);
            // 改变退单列表状态为 2 拒绝退货
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "2");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 5) {
            // 如果退单操作日志为 admin 确认收货,则改变订单状态为 10 确认收货
            orderService.updateStatusBackById(orderId, "10", backPriceNew);
            // 改变退单列表状态为 3 确认收货
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "3");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 6) {
            // 如果退单操作日志为 admin 收货失败，则改变订单状态为 16 收货失败
            orderService.updateStatusBackById(orderId, "16", backPriceNew);
            // 改变退单列表状态为 8 收货失败
            backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "8");
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 7) {
            // 如果退单操作日志为 admin 退款，则改变订单状态为 17 已退款 并记录退款金额
            orderService.updateStatusBackById(orderId, "17", backPriceNew);
            // 改变退单列表状态为 4 退单结束
            int cont = backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "4");
            // 退单结束后把成功消费的积分奖励扣除掉,增加取消订单的积分记录,以及消费记录
            if (cont == 1) {
                backOrderService.reducePointOrderBack(orderId);
            }
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 8) {
            // 如果退单操作日志为 admin 退款，则改变订单状态为 18 退款成功
            orderService.updateStatusBackById(orderId, "18", backPriceNew);
            // 改变退单列表状态为 10 退单成功
            int cont = backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "10");
            // 退单结束后把成功消费的积分奖励扣除掉,增加取消订单的积分记录,以及消费记录
            if (cont == 1) {
                backOrderService.reducePointOrderBack(orderId);
                // 根据订单id查询到优惠劵劵码，修改优惠劵  返还库存
                orderCouponService.modifyCouponStatus(orderId);
            }
        } else if (Long.valueOf(backOrderLog.getBackLogStatus()) == 9) {
            // 如果退单操作日志为 admin 退款，则改变订单状态为 13 拒绝退款
            orderService.updateStatusBackById(orderId, "13", backPriceNew);
            // 改变退单列表状态为 7 拒绝退款
            int cont = backOrderService.modifyBackOrderByCheck(backOrderLog.getBackOrderId(), "7");
            if (cont == 1) {
                backOrderService.reducePointOrderBack(orderId);
            }
        }
        return new ModelAndView(new RedirectView(BACKORDERLIST_HTM));
    }

    // 订单状态 0未付款 1已付款未发货 2已发货 3已经收货 4作废 7:申请退货 8：同意退货 9:拒绝退货 10:确认收货 11:订单结束
    // 12:同意退款13： 拒绝退款 14:已提交退货审核 15：已提交退款审核 16: 收货失败 17:已退款

    /**
     * 检查文件扩展名是否为图片
     *
     * @param fileName
     *            上传的文件的文件名
     * @return
     */
    private boolean checkExtendsName(String fileName) {
        if (fileName.indexOf(".") < 0) {
            return false;
        }
        String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] extendNames = { "jpg", "jpeg", "bmp", "png", "gif" };
        for (String extendName : extendNames) {
            if (extend.equals(extendName)) {
                return true;
            }
        }
        return false;
    }

    /***
     * 生成退单单号 系统时间+随机六位数字
     * 
     * @return
     */
    public String createBackOrderNo() {
        String d = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        Random r = new Random();
        Double d1 = r.nextDouble();
        String s = d1 + "";
        s = d + s.substring(3, 3 + 6);
        return s;
    }

    public BackOrderLogService getBackOrderLogService() {
        return backOrderLogService;
    }

    @Resource(name = "BackOrderLogService")
    public void setBackOrderLogService(BackOrderLogService backOrderLogService) {
        this.backOrderLogService = backOrderLogService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public BackOrderService getBackOrderService() {
        return backOrderService;
    }

    @Resource(name = "BackOrderService")
    public void setBackOrderService(BackOrderService backOrderService) {
        this.backOrderService = backOrderService;
    }

    public OrderLogService getOrderLogService() {
        return orderLogService;
    }

    @Resource(name = "OrderLogService")
    public void setOrderLogService(OrderLogService orderLogService) {
        this.orderLogService = orderLogService;
    }

}
