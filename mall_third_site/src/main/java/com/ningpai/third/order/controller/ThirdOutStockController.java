/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.third.order.service.ThirdOrderService;
import com.ningpai.third.order.util.OrderValueUtil;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方出库Controller
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月10日14:32:28
 * 
 */
@Controller
public class ThirdOutStockController {
    //第三方订单接口
    @Resource(name = "ThirdOrderService")
    private ThirdOrderService orderService;

    //订单接口
    @Resource(name = "OrderService")
    private OrderService bossOrderService;

    /**
     * 查询出库列表
     * 
     * @param order
     *            订单信息
     * @param request
     * @param n
     *            所属位置
     * @param l
     *            所属位置
     * @param pb
     *            分页参数
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/querythirdoutstock")
    public ModelAndView queryThirdOutStock(Order order, HttpServletRequest request, String n, String l, PageBean pb) throws UnsupportedEncodingException {
        Order orderNew = order;
        PageBean pbNew = pb;
        //收货人
        if(null != orderNew.getShippingPerson()){
            //中文转码
            String str = new String(orderNew.getShippingPerson().getBytes("ISO8859-1"), ConstantUtil.UTF);
            //设置收货人
            orderNew.setShippingPerson(str);
        }
        
        Map<String, Object> map;
        try {
            //商家ID
            orderNew.setBusinessId((Long) request.getSession().getAttribute("thirdId"));
            //设置要跳转的路径
            pbNew.setUrl("querythirdoutstock.html");
            //填充导航/左侧菜单索引 用于页面控制css选中
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
            map = new HashMap<String, Object>();
            //订单对象
            map.put("order", orderNew);
            // 设置pageBean属性
            map.put("pb", orderService.searchOrderListByOrderCargo(pbNew, orderNew));
            // 按条件查询出库列表
            return new ModelAndView(OrderValueUtil.THIRDOUTSTOCK).addObject("map", map);
        } finally {
            map = null;
            orderNew = null;
            pbNew = null;
        }
    }


    /**
     * 开始拣货
     *
     * @param orderId
     *            所有要拣货的商品id
     * @return ModelAndView
     */
    @RequestMapping("/thirdorderpicking")
    public ModelAndView thirdOrderPicking(Long[] orderId, HttpServletRequest request) {
        //装载返回到页面的数据  拣货准备
        Map<String, Object> map= orderService.orderPicking(orderId, request);
        return new ModelAndView("order/printtakepro", "map",map );
    }

     /**
     * 拣货结束
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("/thirdchangeorderpicking")
    @ResponseBody
    public int thirdChangeOrderPicking(Long[] orderId, HttpServletRequest request) {
        //捡货
        return orderService.goOrderPicking(orderId, request);
    }

    /**
     * 装箱
     * 
     * @param orderId
     *            订单id
     * @param orderStatus
     *            订单状态
     * @return
     */
    @RequestMapping(value = "thirdmodifyorderbyparam")
    @ResponseBody
    public int thirdModifyOrderByParam(Long orderId, String orderStatus, HttpServletRequest request, String token) {
        if (token == null || !"L".equals(token)) {
            return 0;
        } else {
            //判断订单的出库状态是否符合
            if (bossOrderService.judgeStatus("1", orderId) == 0) {
                return 0;
            }
        }
        // 修改订单状态
        return bossOrderService.updateSetCargoStatusByThirdOrderId(orderId, orderStatus, (Long)request.getSession().getAttribute("thirdId"));
    }

}
