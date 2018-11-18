/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.service.WareHouseService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.order.bean.*;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.order.service.OrderLogService;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.system.service.LogisticsSingleService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.OrderConstants;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出库Controller
 *
 * @AUTHOR NINGPAI-LIH
 *
 */
@Controller
public class OrderCargoController {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(OrderCargoController.class);

    private static final String PAGEBEAN = "pageBean";
    private static final String ORDER = "order";
    private static final String YSTATUS = "yStatus";
    private static final String RELATIONS = "relations";

    private OrderService orderService;

    private OrderCouponService orderCouponService;

    // 订单操作记录
    private OrderLogService orderLogService;
    // 物流公司Spring注入
    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz iLogisticsCompanyBiz;
    // 物流单模板
    @Resource(name = "LogisticsSingleService")
    private LogisticsSingleService logisticsSingleService;
    // 站点
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    // 仓库信息
    @Resource(name = "WareHouseService")
    private WareHouseService wareHouseService;

    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz logisticsCompanyBiz;

    @Resource(name="customerServiceMapper")
    private CustomerServiceMapper customerService;



    /**
     * 查询拣货列表
     *
     *
     * @param order
     *            订单信息
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/orderpickinglist")
    public ModelAndView orderPickingList(Order order, PageBean pageBean, HttpServletRequest request) {
        MenuSession.sessionMenu(request);
        // 设置订单状态
        order.setOrderStatus("1");
        // 设置出库状态
        order.setOrderCargoStatus("0");
        // 设置商品为boss商品
        order.setBusinessId((long) 0);
        pageBean.setUrl(OrderConstants.INITORDERPICKING);
        return new ModelAndView(OrderConstants.ORDERPICKINGLIST, PAGEBEAN, orderService.searchOrderList(order, pageBean)).addObject(ORDER, order).addObject("sx",
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 打印
     *
     * @param str
     * @return
     */
    @RequestMapping("/printView")
    public ModelAndView printview(String str) {
        return new ModelAndView("jsp/order/printorderpickinglist").addObject("str", str);
    }

    /**
     * 出库单打印
     * @param orderId
     * @return
     */
    @RequestMapping("/deliveryPrintView")
    public ModelAndView deliveryPrintView(String orderId) {
        ModelAndView mv =new ModelAndView("jsp/order/deliveryOrderPrint");
        Long order_id = Long.valueOf(orderId);

        List<OrderContainerRelation> relations = orderService.querySimpleContainerRalation(order_id);
        // 查询订单信息
        Order order = orderService.getSimplePayOrder(order_id);
        Customer customer =customerService.getCustomerByCusId(order.getCustomerId());
        if(customer!=null){
            order.setIsEnterprise(customer.getIsEnterprise());
        }
        if(order.getOrderGoodsList()!=null){
            for(OrderGoods orderGood : order.getOrderGoodsList()){
                orderGood.setGoodsProductReleSpecVoList(orderService.queryAllSimpleByProductId(orderGood.getGoodsInfoId()));
            }
        }
        mv.addObject(ORDER, order);
        mv.addObject(RELATIONS, relations);
        return mv;
    }

    /**
     * 拣货
     *
     * @param orderId
     * @param status
     *            0拣货 1出库
     * @return ModelAndView
     */
    @RequestMapping("/orderpicking")
    public ModelAndView orderPicking(Long[] orderId, HttpServletRequest request, String status, String yStatus) {
        String pickingName = request.getSession().getAttribute("name").toString();
        Map<String, Object> map = orderService.queryByPincking(orderId, (long) 0, pickingName, status);
        return new ModelAndView(OrderConstants.PRINTTAKEPRO).addObject("map", map).addObject(YSTATUS, yStatus)
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 拣货ajax
     *
     * @param orderId
     * @param status
     *            0拣货 1出库
     * @return ModelAndView
     */
    @RequestMapping("/orderpickingajax")
    @ResponseBody
    public Map<String, Object> orderPickingajax(Long[] orderId, HttpServletRequest request, String status, String yStatus) {
        String pickingName = request.getSession().getAttribute("name").toString();
        return orderService.querySimpleByPincking(orderId, (long) 0, pickingName, status);
    }

    /**
     * 打印跳转
     *
     *
     * @return ModelAndView
     */
    @RequestMapping("/printorderpickinglist")
    public ModelAndView printorderpickinglist(String str, HttpServletRequest request) {
        String title = "";
        try {
            title = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("",e);
        }
        return new ModelAndView(OrderConstants.PRINTORDERPICKINGLIST).addObject("str", title).addObject("sx",
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 修改订单状态
     *
     * @param orderIds
     *            订单id数组
     * @param status
     *            要修改的状态
     * @param request
     * @return
     */
    @RequestMapping("changeorderbyfif")
    @ResponseBody
    public int changeOrderByFif(Long[] orderIds, String status, HttpServletRequest request, String token) {
        if (token == null || !"L".equals(token)) {
            return 0;
        } else {
            for (int i = 0; i < orderIds.length; i++) {
                // 判断上订单状态是否符合条件
                if (orderService.judgeStatus("0", orderIds[i]) == 0) {
                    return 0;
                }
            }
        }
        for (int i = 0; i < orderIds.length; i++) {
            // 操作记录 2:拣货
            orderLogService.insertSelective(null, orderIds[i], request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "2");
        }

        return orderService.updateOrderCargoStatusByOrderIds(orderIds, status);
    }

    /**
     * 查询出库列表
     *
     * @param order
     *            订单查询信息
     * @param pageBean
     *            分页参数
     * @param orderIds
     *            订单id数组
     * @param request
     * @return
     */
    @RequestMapping("/orderdeliverylist")
    public ModelAndView orderDeliveryList(Order order, PageBean pageBean, Long[] orderIds, HttpServletRequest request) {
        order.setOrderStatus("1");
        // 要查询的出库状态
        order.setOrderCargoStatus("1");
        order.setBusinessId((long) 0);
        pageBean.setUrl(OrderConstants.ORDERDELIVERYLIST);
        return new ModelAndView(OrderConstants.ORDERPICKINGLIST, PAGEBEAN, orderService.searchSimpleOrderList(order, pageBean, orderIds)).addObject(ORDER, order).addObject("sx",
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());

    }

    /**
     * 出库
     *
     * @param orderId
     * @param yStatus
     *            0拣货 1出库
     * @return ModelAndView
     */
    @RequestMapping("/ordercontainer")
    public ModelAndView orderContainer(Long orderId, HttpServletRequest request, String yStatus) {
        // 设置默认包裹
        orderService.initContainerRelation(orderId);
        // 设置订单包裹商品和赠品
        List<OrderContainerRelation> relations = orderService.queryContainerRalation(orderId);
        // 查询订单信息
        Order order = orderService.getPayOrder(orderId);
        return new ModelAndView(OrderConstants.PRINTOUTSTOCK).addObject(ORDER, order).addObject(RELATIONS, relations).addObject(YSTATUS, yStatus)
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());

    }

    /**
     * 出库ajax
     *
     * @param orderId
     * @param yStatus
     *            0拣货 1出库
     * @return ModelAndView
     */
    @RequestMapping("/ordercontainerajax")
    @ResponseBody
    public Map<String, Object> orderContainerajax(Long orderId, HttpServletRequest request, String yStatus) {
        // 设置默认包裹
        orderService.initContainerRelation(orderId);
        // 设置订单包裹商品和赠品
        //List<OrderContainerRelation> relations = orderService.queryContainerRalation(orderId);
            List<OrderContainerRelation> relations = orderService.querySimpleContainerRalation(orderId);
        // 查询订单信息
       // Order order = orderService.getPayOrder(orderId);
        Order order = orderService.getSimplePayOrder(orderId);
        if(order.getOrderGoodsList()!=null){
            for(OrderGoods orderGood : order.getOrderGoodsList()){
                orderGood.setGoodsProductReleSpecVoList(orderService.queryAllSimpleByProductId(orderGood.getGoodsInfoId()));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ORDER, order);
        map.put(RELATIONS, relations);
        map.put(YSTATUS, yStatus);
        return map;

    }

    /**
     * 修改订单状态
     *
     * @param orderId
     *            订单id
     * @param orderStatus
     *            订单状态
     * @return
     */
    @RequestMapping(value = "modifyorderbyparam")
    @ResponseBody
    public int modifyOrderByParam(Long orderId, String orderStatus, HttpServletRequest request, String token) {
        if (token == null || !"L".equals(token)) {
            return 0;
        } else {
            // 判断订单状态是否符合条件
            if (orderService.judgeStatus("1", orderId) == 0) {
                return 0;
            }
        }
        // 操作记录3：出库
        orderLogService.insertSelective(null, orderId, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "3");

        // 修改订单状态
        return orderService.updateSetCargoStatusByOrderId(orderId, orderStatus);
    }

    /**
     * 新增装箱单
     *
     * @param orderId
     *            订单id
     * @return ModelAndView
     */
    @RequestMapping("/addcontainer")
    public ModelAndView addContainer(Long orderId, HttpServletRequest request) {
        // 添加装箱单
        orderService.addContainerRalation(orderId);
        // 返回到出库页面
        return new ModelAndView(new RedirectView(OrderConstants.ORDERCONTAINER)).addObject(ConstantUtil.ORDERID, orderId).addObject(ConstantUtil.CSRFTOKEN,
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 新增装箱单
     *
     * @param orderId
     *            订单id
     * @return ModelAndView
     */
    @RequestMapping("/addcontainerajax")
    @ResponseBody
    public int addContainerajax(Long orderId, HttpServletRequest request) {
        // 添加装箱单
        orderService.addContainerRalation(orderId);
        // 返回到出库页面
        return 1;
    }

    /**
     * 更改包裹
     *
     * @param container
     *            参数
     * @param orderId
     *            订单id
     * @return ModelAndView
     */
    @RequestMapping("/updatecontainer")
    public ModelAndView updateContainer(OrderContainer container, Long orderId, HttpServletRequest request) {
        // 根据原包裹cId 获得此包裹中商品信息
        OrderContainer orderContainer = orderService.queryOrderContainerById(container.getContainerId());
        // 根据货品Id 和包裹关联rId 查询商品信息
        OrderContainer newCon = orderService.queryOrderContainerByGoodInfoId(container.getRelationId(), orderContainer.getGoodsInfoId());

        if (orderContainer.getGoodsNum() > container.getGoodsNum()) {
            // 修改原包裹内商品的数量
            // 修改数量
            orderContainer.setGoodsNum(orderContainer.getGoodsNum() - container.getGoodsNum());
            orderService.updateRelation(orderContainer);

            if (newCon != null) {
                // 添加数量
                newCon.setGoodsNum(newCon.getGoodsNum() + container.getGoodsNum());
                // 修改
                orderService.updateRelation(newCon);
            } else {
                // 插入新包裹中商品信息
                newCon = container;
                // 将cid设为null
                newCon.setContainerId(null);
                // 设置货品Id
                newCon.setGoodsInfoId(orderContainer.getGoodsInfoId());
                newCon.setContainerStatus("0");
                orderService.addRelation(newCon);
            }

        } else if (orderContainer.getGoodsNum().equals(container.getGoodsNum())) {
            if (newCon != null) {
                // 添加数量
                newCon.setGoodsNum(newCon.getGoodsNum() + container.getGoodsNum());
                // 修改
                orderService.updateRelation(newCon);
                // 删除原包装中的此商品
                orderService.delContainerByCId(container.getContainerId());
            } else {
                // 修改包裹
                orderService.updateRelation(container);
            }
        }

        return new ModelAndView(new RedirectView(OrderConstants.ORDERCONTAINER)).addObject(ConstantUtil.ORDERID, orderId).addObject(ConstantUtil.CSRFTOKEN,
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());

    }

    /**
     * 更改包裹ajax
     *
     * @param container
     *            参数
     * @param orderId
     *            订单id
     * @return ModelAndView
     */
    @RequestMapping("/updatecontainerajax")
    @ResponseBody
    public int updateContainerajax(OrderContainer container, Long orderId, HttpServletRequest request) {
        // 修改包裹
        orderService.updateRelation(container);
        return 1;
    }

    /**
     * 删除包裹
     *
     * @param relationId
     *            包裹id
     * @param orderId
     *            订单id
     * @return ModelAndView
     */
    @RequestMapping("/delrelationbyid")
    public ModelAndView delRelationById(Long relationId, Long orderId, HttpServletRequest request) {
        orderService.delRelationById(relationId);
        return new ModelAndView(new RedirectView(OrderConstants.ORDERCONTAINER)).addObject(ConstantUtil.ORDERID, orderId).addObject(ConstantUtil.CSRFTOKEN,
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());

    }

    /**
     * 删除包裹
     *
     * @param relationId
     *            包裹id
     * @param orderId
     *            订单id
     * @return ModelAndView
     */
    @RequestMapping("/delrelationbyidajax")
    @ResponseBody
    public int delRelationByIdajax(Long relationId, Long orderId, HttpServletRequest request) {
        orderService.delRelationById(relationId);
        return 1;

    }

    /**
     * 查询包裹下商品总数
     *
     * @param relationId
     *            包裹id
     * @return
     */
    @RequestMapping("/verifycount")
    @ResponseBody
    public Long verifyCount(Long relationId) {
        return orderService.verifyCount(relationId);
    }

    /**
     * 发货
     *
     * @param orderExpress
     * @return ModelAndView
     */
    @RequestMapping("/sendorder")
    public ModelAndView sendOrder(OrderExpress orderExpress) {
        // 修改订单状态为发货
        orderService.sendOrder(orderExpress);
        return new ModelAndView(new RedirectView(OrderConstants.INITORDER));
    }

    /**
     * 查询发货列表
     *
     * @param order
     * @param pageBean
     *            0拣货 1出库
     * @return ModelAndView
     */
    @RequestMapping("/ordersendgoods")
    public ModelAndView orderSendGoods(Order order, PageBean pageBean, HttpServletRequest request) {
        order.setOrderStatus("1");
        order.setOrderCargoStatus("2");
        order.setBusinessId((long) 0);
        pageBean.setUrl("ordersendgoods.htm");
        return new ModelAndView(OrderConstants.ORDERPICKINGLIST, PAGEBEAN, orderService.searchOrderList(order, pageBean)).addObject(ORDER, order).addObject("sx",
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 跳转到出库页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/toSendgoodsorder")
    public ModelAndView toSendGoodsOrder(Long orderId, HttpServletRequest request, String status, String yStatus) {

        return new ModelAndView(OrderConstants.PRINTSHIPMENTS).addObject("orderId", orderId).addObject("status", status).addObject(YSTATUS, yStatus);
    }

    /**
     * 准备发货
     *
     * @return ModelAndView
     */
    @RequestMapping("/sendgoodsorder")
    @ResponseBody
    public Map<String, Object> sendGoodsOrder(Long expressId, Long orderId, HttpServletRequest request, String status, String yStatus) {
        // 设置订单包裹商品和赠品
        List<OrderContainerRelation> relations = orderService.querySimpleContainerRalation(orderId);
        // 查询订单信息
        Order order = orderService.getSimplePayOrder(orderId);
        // 查询物流信息
        OrderExpress express = null;
        if (expressId != null) {
            express = new OrderExpress();
            express.setExpressId(expressId);
        } else {
            express = orderService.expressDetail(orderId);
        }
        //
        List<LogisticsCompany> logisticsCompanies = logisticsCompanyBiz.queryAllLogisticsCompany();
        order.setOrderExpress(express);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ORDER, order);
        map.put(RELATIONS, relations);
        map.put("status", status);
        map.put(YSTATUS, yStatus);
        map.put("logisticsCompanies",logisticsCompanies);
        map.put("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
        map.put("expressList", iLogisticsCompanyBiz.queryAllLogisticsCompany());
        // 查询Boss的站点信息
        map.put("basicSet", basicSetService.findBasicSet());
        // 查询仓库信息
        List<WareHouse> wareHouses = wareHouseService.queryAllWareHouse();
        if (wareHouses != null) {
            map.put("ware", wareHouses.get(0));
        }

        LogisticsSingle logisticsSingle = logisticsSingleService.selectLogisticsSingle(0L, express.getExpressId());
        map.put("logisticsSingle", logisticsSingle);
        return map;
    }

    /**
     * 准备发货
     *
     * @return ModelAndView
     */
    @RequestMapping("/loadLogisticsTemplate")
    @ResponseBody
    public LogisticsSingle loadLogisticsTemplate(Long expressId) {
        return logisticsSingleService.selectLogisticsSingle(0L, expressId);
    }

    /**
     * 发货
     *
     * @param orderId
     *            订单id
     * @param relationIds
     *            包裹id数组
     * @param expressNo
     *            运单数组
     * @param request
     * @return
     */
    @RequestMapping("/subsendgoodsorder")
    @ResponseBody
    public int subSendGoodsorder(String token, Long orderId, Long[] relationIds, String[] expressNo, HttpServletRequest request, int[] expressId) {
        if (token == null || !"L".equals(token)) {
            return 0;
        } else {
            if (orderService.judgeStatus("2", orderId) == 0) {
                return 0;
            }
        }


        // 更新运货单
        orderService.updateSendOrderGoods(relationIds, expressNo, expressId);
        String pickingName = request.getSession().getAttribute("name").toString();
        // 操作记录 4：发货
        orderLogService.insertSelective(null, orderId, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "4");
        // 修改订单状态
        orderService.sendOrderByP(orderId, "2", pickingName, "2", (long) 0);
        orderService.updateSetCargoStatusByOrderId(orderId, "3");
        return 0;
    }

    /**
     * 中断订单
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    @RequestMapping(value = "suspendorder")
    public ModelAndView suspendOrder(Long orderId, String orderStatus, String reason, HttpServletRequest request, String orderCodex) {
        MenuSession.sessionMenu(request);
        orderCouponService.modifyCouponStatus(orderId);
        int count = orderService.modifyOrderByKey(orderId, orderStatus, reason);

        String operaCode = "订单操作";
        String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + "  中断订单：" + orderCodex + " 中断原因:" + reason;
        OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        // 添加操作 0:修改价格
        orderLogService.insertSelective(reason, orderId, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "1");
        // 判断是否修改成功
        if (count > 0) {
            return new ModelAndView(new RedirectView(OrderConstants.INITORDER)).addObject(ConstantUtil.ORDERID, orderId);
        }
        return null;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderCouponService getOrderCouponService() {
        return orderCouponService;
    }

    @Resource(name = "OrderCouponService")
    public void setOrderCouponService(OrderCouponService orderCouponService) {
        this.orderCouponService = orderCouponService;
    }

    public OrderLogService getOrderLogService() {
        return orderLogService;
    }

    @Resource(name = "OrderLogService")
    public void setOrderLogService(OrderLogService orderLogService) {
        this.orderLogService = orderLogService;
    }

}
